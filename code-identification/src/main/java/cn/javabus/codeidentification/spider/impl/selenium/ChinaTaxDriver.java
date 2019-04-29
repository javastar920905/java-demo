package cn.javabus.codeidentification.spider.impl.selenium;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.javabus.codeidentification.enums.EnumTicketPlateform;
import cn.javabus.codeidentification.spider.design.TicketSpiderTemplate;
import cn.javabus.codeidentification.util.Base64Utils;
import cn.javabus.codeidentification.util.FileUtil;
import cn.javabus.codeidentification.util.RunProcess;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.springframework.util.StringUtils;

/**
 * @author ouzhx on 2019/4/29.
 */
@Log4j2
public class ChinaTaxDriver extends TicketSpiderTemplate {
    static String path = ChinaTaxDriver.class.getClassLoader().getResource("").getPath();
    static String root = path.substring(0, path.indexOf("code-identification/"));
    static String selenium_dir = root + "code-identification\\src\\main\\java\\cn\\javabus\\codeidentification\\spider\\impl\\selenium\\";
    static WebDriver driver;
    private static String userId = "seleniumtest";//todo

    public ChinaTaxDriver(EnumTicketPlateform ticketPlateform) {
        super(ticketPlateform);
        System.setProperty("webdriver.ie.driver", selenium_dir + "IEDriverServer.exe");
        if (driver == null) {
            driver = new InternetExplorerDriver();
        }
        //System.setProperty("webdriver.chrome.driver",selenium_dir+"chromedriver.exe" );
    }


    @Override
    public HttpRequest installSpiderHeader(HttpRequest httpRequest) {
        return httpRequest;
    }

    @Override
    public JSONObject getCode(JSONObject param) {
        driver.get("https://inv-veri.chinatax.gov.cn/");
        WebElement fpdm = driver.findElement(By.id("fpdm"));
        fpdm.sendKeys(param.getStr("fpdm"));
        WebElement fphm = driver.findElement(By.id("fphm"));
        fphm.sendKeys(param.getStr("fphm"));
        WebElement kprq = driver.findElement(By.id("kprq"));
        kprq.sendKeys(param.getStr("kprq"));
        WebElement kjje = driver.findElement(By.id("kjje"));
        kjje.sendKeys(param.getStr("kjje"));

        //获取验证码属,返回验证码提示
        JSONObject json = getCode();
        return json;
    }

    /**
     * 点击刷新验证码
     */
    private JSONObject getCode() {
        //获取验证码属,返回验证码提示
        WebElement yzmImg = driver.findElement(By.id("yzm_img"));
        yzmImg.click();
        String src = yzmImg.getAttribute("src");

        if (StringUtils.isEmpty(src)) {
            String imageBase64FromSrc = getImageBase64FromSrc(src);
            log.info(imageBase64FromSrc);
            Base64Utils.Base64ToImage(imageBase64FromSrc, "/" + userId + ".jpg");
        }

        WebElement yzminfo = driver.findElement(By.id("yzminfo")).findElement(By.tagName("font"));
        String yzminfoStr = "请输入验证图片中:" + yzminfo.getText() + "文字";
        log.info(yzminfoStr);

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
    public JSONObject refreshCode() {
        return getCode();
    }

    @Override
    public JSONObject codeIdentification(JSONObject param) {
        WebElement yzm = driver.findElement(By.id("yzm"));
        yzm.sendKeys(param.getStr("yzm"));

        //提交查验
        WebElement checkfp = driver.findElement(By.id("checkfp"));
        checkfp.click();

        //todo 返回查验结果
        StringBuffer sb = new StringBuffer();

        WebElement floatwin = driver.findElement(By.id("floatwin"));
        System.out.println(floatwin.isDisplayed());
        //if (floatwin.isDisplayed()){
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

        sb.append("查验次数:   ").append(cycs).append("\r\n");
        sb.append("查验时间:   ").append(cysj).append("\r\n");
        sb.append("发票地区:   ").append(fpcc_zp).append("\r\n");
        sb.append("发票代码:   ").append(fpdm_zp).append("\r\n");
        sb.append("发票号码:   ").append(fphm_zp).append("\r\n");
        sb.append("开票日期:   ").append(kprq_zp).append("\r\n");
//        sb.append("校验码:   ").append(jym_zp).append("\r\n");
//        sb.append("机器编码:   ").append(jqbh_zp).append("\r\n");
//        sb.append("机器编码:   ").append(jqbh_zp).append("\r\n");
//        sb.append("机器编码:   ").append(jqbh_zp).append("\r\n");

        String result = sb.toString();
        FileUtil.writeFile(selenium_dir+"result.txt", result);

        String pageSource = driver.getPageSource();
        //获取 id=tabPage2 区域,转成pdf
        //网页转pdf
        log.info(pageSource);
        try {
            //wkhtmltopdf toc test.html test.pdf
            String command = "C:\\Program Files\\wkhtmltopdf\\bin\\wkhtmltopdf.exe toc test.html test.pdf";
            RunProcess.doExec(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //}
        JSONObject resultJson = new JSONObject();
        resultJson.put("result", result);
        return resultJson;
    }

}
