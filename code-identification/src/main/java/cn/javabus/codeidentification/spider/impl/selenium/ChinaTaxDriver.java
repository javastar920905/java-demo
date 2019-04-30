package cn.javabus.codeidentification.spider.impl.selenium;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.javabus.codeidentification.enums.EnumTicketPlateform;
import cn.javabus.codeidentification.spider.design.TicketSpiderTemplate;
import cn.javabus.codeidentification.util.Base64Utils;
import cn.javabus.codeidentification.util.FileUtil;
import cn.javabus.codeidentification.util.RunProcess;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ouzhx on 2019/4/29.
 * <p>
 * 报错总结:
 * 1 Browser zoom level was set to 150%. It should be set to 100%
 * 解决: IE 设置,缩放比例设置为100%即可
 * <p>
 * 2 浏览器->安全选项->4个设置需要保持一致,要么全部关闭"启用保护模式",要么全部开启
 * <p>
 * 3 提示https安全性问题:
 * 解决: 安装证书
 * 解决2: 关闭https提示  https://www.cnblogs.com/leeboke/p/8108680.html
 */
@Log4j2
public class ChinaTaxDriver extends TicketSpiderTemplate {
    static String path = ChinaTaxDriver.class.getClassLoader().getResource("").getPath();
    static String root = path.substring(0, path.indexOf("code-identification/"));
    static String selenium_dir = root + "code-identification\\src\\main\\java\\cn\\javabus\\codeidentification\\spider\\impl\\selenium\\";
    /**
     * wkhtmltopdf html转pdf 工具安装地址
     **/
    static String wkhtmltopdfPath = "C:\\Program Files\\wkhtmltopdf\\bin\\wkhtmltopdf.exe";
    static WebDriver driver;
    private static String userId = "seleniumtest";
    private ReentrantLock lock = new ReentrantLock();

    public ChinaTaxDriver(EnumTicketPlateform ticketPlateform) {
        super(ticketPlateform);
        System.setProperty("webdriver.ie.driver", selenium_dir + "IEDriverServer.exe");
        driveInstance();
        //System.setProperty("webdriver.chrome.driver",selenium_dir+"chromedriver.exe" );
    }

    private void driveInstance() {

        if (driver == null) {
            lock.lock();
            if (driver == null) {
                driver = new InternetExplorerDriver();
                driver.get("https://inv-veri.chinatax.gov.cn/");
            }
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
    }


    @Override
    public HttpRequest installSpiderHeader(HttpRequest httpRequest) {
        return httpRequest;
    }

    @Override
    public JSONObject getCode(JSONObject param) {
        userRetryCount = new ConcurrentHashMap<>();
        driveInstance();

        WebElement fpdm = driver.findElement(By.id("fpdm"));
        sendKeys("fpdm", fpdm, param.getStr("fpdm"));
        WebElement fphm = driver.findElement(By.id("fphm"));
        sendKeys("fphm", fphm, param.getStr("fphm"));
        WebElement kprq = driver.findElement(By.id("kprq"));
        sendKeys("kprq", kprq, param.getStr("kprq"));
        WebElement kjje = driver.findElement(By.id("kjje"));
        sendKeys("kjje", kjje, param.getStr("kjje"));

        //获取验证码属,返回验证码提示
        JSONObject json = getCode();
        return json;
    }

    static ConcurrentHashMap<String, Integer> userRetryCount = new ConcurrentHashMap<>();
    static int maxRetry = 3;

    public void sendKeys(String eleId, WebElement ele, String val) {
        String key = userId + eleId;
        Integer retryCount = userRetryCount.get(key);
        if (retryCount == null) {
            retryCount = 0;
            userRetryCount.put(key, retryCount);
        } else {
            retryCount += 1;
            userRetryCount.put(key, retryCount);
        }
        ele.sendKeys(val);

        //输入值不一致,重试
        String value = ele.getAttribute("value");
        try {
            if (!val.equals(value) && retryCount < maxRetry) {
                ele.clear();
                TimeUnit.MICROSECONDS.sleep(500);
                sendKeys(eleId, ele, val);
                log.info("用户输入val{} 与 输入框值 {}不一致,重试:{} 次", val, value, retryCount + 1);
            }
        } catch (Exception e) {
            log.error("key={},val={},value={},retryCount={}", key, val, value, retryCount);
        }
    }

    /**
     * 点击刷新验证码
     */
    private JSONObject getCode() {
        //获取验证码属,返回验证码提示
        WebElement yzmImg = driver.findElement(By.id("yzm_img"));
        String src = yzmImg.getAttribute("src");
        try {
            //返回默认图片时,重新触发点击事件,获取验证码
            if (!src.contains(imageBase64Prefix)) {
                yzmImg.click();
            }
        } catch (Exception e) {
            log.info(e.getMessage() + "  验证码图片属性值:display= " + yzmImg.getCssValue("display"));
        }

        WebElement yzminfo = null;
        try {
            //00时, font标签是不存在的
            yzminfo = driver.findElement(By.id("yzminfo")).findElement(By.tagName("font"));
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        String rightCodeTip = yzminfo == null ? "所有" : yzminfo.getText();
        String yzminfoStr = "请输入验证图片中" + rightCodeTip + "文字";
        log.info(yzminfoStr);

        src = yzmImg.getAttribute("src");
        if (!StringUtils.isEmpty(src) && src.contains(imageBase64Prefix)) {
            String imageBase64FromSrc = getImageBase64FromSrc(src);
            log.info("生成验证码图片 长度:" + imageBase64FromSrc.length());
            Base64Utils.Base64ToImage(imageBase64FromSrc, "./" + userId + ".jpg");
        }

        JSONObject json = new JSONObject();
        json.put("codeTip", yzminfoStr);
        json.put("yzm", src);
        return json;
    }

    static final String imageBase64Prefix = "data:image/png;base64,";
    static final int imageBase64Prefix_len = imageBase64Prefix.length();

    private String getImageBase64FromSrc(String src) {
        return src.substring(imageBase64Prefix_len);
    }

    @Override
    public JSONObject refreshCode(JSONObject param) {
        try {
            //方式1 触发验证码点击事件图片
            WebElement yzmImg = driver.findElement(By.id("yzm_img"));
            yzmImg.click();// display none 的不能click()

//            //双击demo
//            Actions action = new Actions(driver);
//            action.doubleClick(yzmImg);

            //方式2 点击重置按鈕,重新填写表单
//            WebElement reset = driver.findElement(By.id("reset"));
//            reset.click();
//            return getCode(param);
        } catch (Exception e) {
            log.error("刷新验证失败");
        }
        return getCode();
    }

    @Override
    public JSONObject codeIdentification(JSONObject param) {
        WebElement yzm = driver.findElement(By.id("yzm"));
        sendKeys("yzm", yzm, param.getStr("yzm"));

        //提交查验
        WebElement checkfp = driver.findElement(By.id("checkfp"));
        checkfp.click();

        StringBuffer sb = new StringBuffer();
        WebElement floatwin = driver.findElement(By.id("floatwin"));

        if (!floatwin.isDisplayed()) {
            //返回出错提示
            WebElement popup_message = null;
            try {
                popup_message = driver.findElement(By.id("popup_message"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONObject jsonObject = refreshCode(param);
            jsonObject.put("msg", popup_message != null ? popup_message.getText() : "");
            return jsonObject;
        } else {
            //返回查验成功结果
            //table 表头区域
            WebElement cycs = driver.findElement(By.id("cycs"));//查验次数
            WebElement cysj = driver.findElement(By.id("cysj"));//查验时间

            //发票地区
            WebElement fpcc_zp = driver.findElement(By.id("fpcc_zp"));//发票地区

            //发票信息
            WebElement fpdm_zp = driver.findElement(By.id("fpdm_zp"));//发票代码
            WebElement fphm_zp = driver.findElement(By.id("fphm_zp"));//发票号码
            WebElement kprq_zp = driver.findElement(By.id("kprq_zp"));//开票日期
            WebElement jym_zp = driver.findElement(By.id("jym_zp"));//校验码
            WebElement jqbh_zp = driver.findElement(By.id("jqbh_zp"));//机器编码


            //公司名称
            WebElement gfmc_zp = driver.findElement(By.id("gfmc_zp"));//公司名称
            WebElement gfsbh_zp = driver.findElement(By.id("gfsbh_zp"));//纳税人识别号
            WebElement gfdzdh_zp = driver.findElement(By.id("gfdzdh_zp"));//地址电话 深圳市南山区粤海街道高新区社区高新南七道016号德维森大厦601电话0755-86961830
            WebElement gfyhzh_zp = driver.findElement(By.id("gfyhzh_zp"));//开户行及账号：中国建设银行深圳华侨城支行44201518300052522561

            //发票内容...todo


            WebElement je_zp = driver.findElement(By.id("je_zp"));//合计
            WebElement se_zp = driver.findElement(By.id("se_zp"));//税额
            WebElement jshjdx_zp = driver.findElement(By.id("jshjdx_zp"));//价税合计大写
            WebElement jshjxx_zp = driver.findElement(By.id("jshjxx_zp"));//价税合计小写


            // 销售方: 开票提供者信息
            WebElement xfmc_zp = driver.findElement(By.id("xfmc_zp"));//销售方名称
            WebElement xfsbh_zp = driver.findElement(By.id("xfsbh_zp"));//销售方 纳税识别号
            WebElement xfdzdh_zp = driver.findElement(By.id("xfdzdh_zp"));//地址电话
            WebElement xfyhzh_zp = driver.findElement(By.id("xfyhzh_zp"));//开户行以及账号

            sb.append("查验次数:   ").append(cycs.getText()).append("\r\n");
            sb.append("查验时间:   ").append(cysj.getText()).append("\r\n");
            sb.append("发票地区:   ").append(fpcc_zp.getText()).append("\r\n");
            sb.append("发票代码:   ").append(fpdm_zp.getText()).append("\r\n");
            sb.append("发票号码:   ").append(fphm_zp.getText()).append("\r\n");
            sb.append("开票日期:   ").append(kprq_zp.getText()).append("\r\n");
//        sb.append("校验码:   ").append(jym_zp).append("\r\n");
//        sb.append("机器编码:   ").append(jqbh_zp).append("\r\n");
//        sb.append("机器编码:   ").append(jqbh_zp).append("\r\n");
//        sb.append("机器编码:   ").append(jqbh_zp).append("\r\n");
        }

        //获取 id=tabPage2 区域,转成pdf
        String result = sb.toString();
        FileUtil.writeFile(selenium_dir + "result.txt", result);


        try {
            //网页转pdf
            String pageSource = jsoupParse(driver.getPageSource());
            String outputFile = selenium_dir + "pageSource.html";

            FileUtil.writeFile(outputFile, pageSource);

            //wkhtmltopdf toc test.html test.pdf
            String command = wkhtmltopdfPath + " toc " + outputFile + " result.pdf";
            RunProcess.doExec(command);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject resultJson = new JSONObject();
        resultJson.put("result", result);
        return resultJson;
    }

    private static String jsoupParse(String pageSource) {
        Document document = Jsoup.parse(pageSource);
        Element floatwin = document.getElementById("floatwin");
        //解决显示乱码问题
        return " <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">" + floatwin.html();
    }


}
