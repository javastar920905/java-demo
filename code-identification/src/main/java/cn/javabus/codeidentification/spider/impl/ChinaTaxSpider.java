package cn.javabus.codeidentification.spider.impl;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.javabus.codeidentification.spider.design.TicketSpiderTemplate;
import cn.javabus.codeidentification.enums.EnumTicketPlateform;
import cn.javabus.codeidentification.util.Base64Utils;
import cn.javabus.codeidentification.util.HTTPUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author ouzhx on 2019/4/24.
 * <p>
 * 全国增值税发票查验
 */
@Log4j2
public class ChinaTaxSpider extends TicketSpiderTemplate {
    public ChinaTaxSpider(EnumTicketPlateform ticketPlateform) {
        super(ticketPlateform);
    }

    @Override
    public HttpRequest installSpiderHeader(HttpRequest httpRequest) {
        HttpRequest request = HTTPUtil.installCommonHeader(httpRequest)
                .header(Header.HOST, "fpcy.shenzhen.chinatax.gov.cn")//fpcy.shenzhen.chinatax.gov.cn
                .header(Header.REFERER, "https://inv-veri.chinatax.gov.cn/");

        if (USER_COOKIE.get(userId) != null) {
            request.header(Header.COOKIE, "JSESSIONID="+USER_COOKIE.get(userId).getValue());
        }
        return request;
    }

    /**
     * 要求的正确验证颜色
     * <p>
     * //如何区分彩色部分字体呢
     * 从返回结果得知: key4 是要求的正确验证码颜色
     * "key2":"2019-04-28 10:12:50","key3":"11b1008d8c0f22da92cfa700570b8fe6","key4":"03","key5":"2"} 蓝色
     * "key2":"2019-04-28 11:10:46","key3":"5a5ef0b67465e5c9f5df799dfee8fb4f","key4":"03","key5":"2"} 蓝色
     * "key2":"2019-04-28 11:12:11","key3":"d392585b65438cdc237b504be9ff4d71","key4":"02","key5":"2"} 黄色
     * "key2":"2019-04-28 11:12:45","key3":"6d909fd88e460c85544e6be9f5541bad","key4":"03","key5":"2"} 蓝色
     * "key2":"2019-04-28 11:13:14","key3":"fc874a51ed539d463f7317143900138a","key4":"02","key5":"2"  黄色
     * "key2":"2019-04-28 11:13:50","key3":"55e3eba1c239d80bb99176d826296c6a","key4":"03","key5":"2"} 蓝色
     * "key2":"2019-04-28 11:13:53","key3":"73d93c3c7e09d9a627ccadda7941d61e","key4":"01","key5":"2"  红色
     * "key2":"2019-04-28 11:14:42","key3":"583d970c977eef5c0a5a824002f3565d","key4":"01","key5":"2"  红色
     * <p>
     * [{"00":"所有"},{"01":"红色"},{"03":"蓝色"},{"02":"黄色"}]
     **/
    private static JSONObject COLORMAP = JSONUtil.parseObj("{\"00\":\"所有\",\"01\":\"红色\",\"03\":\"蓝色\",\"02\":\"黄色\"}");
    private static String RIGHT_CODE_TIP = "请输入验证码图片中%s文字";
    private static String userId = "123456";//todo

    @Override
    public JSONObject getCode(JSONObject param) {

        String url = "https://fpcy.shenzhen.chinatax.gov.cn/WebQuery/yzmQuery?callback=jQuery110204262281891753827_1556423327838";

        param.put("_", System.currentTimeMillis());
        param.put("r", "0.09152192074629295");
        param.put("v", "V1.0.07_001");
        param.put("nowtime", System.currentTimeMillis());
        param.put("publickey", "0A8410A5B5D7526B1CBFC51962CB703A");
//        param.put("fpdm", "044031800104");//发票代码
//        param.put("fphm", "28527159");//发票号码
        url = url + genGetUrlParam(param);

        HttpResponse httpResponse = doGet(url);
        //设置返回cookie
        USER_COOKIE.put(userId, httpResponse.getCookie("JSESSIONID"));
        String result = httpResponse.body();
        result = getJsonStrFromResult(result);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        if (result == null) {
            log.error(result);
        } else {
            Assert.hasText(jsonObject.getStr("key1"), "验证码图片 base64 不能为空");
            //html 显示 需要 data:image/png;base64, +jsonObject.getStr("key1")
            Base64Utils.Base64ToImage(jsonObject.getStr("key1"), "/" + userId + ".jpg");
            jsonObject.put("codeTip", String.format(RIGHT_CODE_TIP, COLORMAP.getStr(jsonObject.getStr("key4"))));
            log.info(jsonObject.getStr("codeTip"));
        }
        return jsonObject;
    }

    @Override
    public JSONObject refreshCode() {
        return null;
    }

    @Override
    public JSONObject codeIdentification(JSONObject param) {
        log.info(JSONUtil.toJsonPrettyStr(param));

        String url = "https://fpcy.shenzhen.chinatax.gov.cn/WebQuery/vatQuery?callback=jQuery110204262281891753827_1556423327838";
        param.put("_", System.currentTimeMillis());
        param.put("area", "4403");//地区
        param.put("fplx", "04");//发票类型
        param.put("publickey", "951202F401BD7A67261C9E115C6F1C2D");
        try {
            param.put("yzm", URLEncoder.encode(param.getStr("yzm"), "UTF-8"));
            param.put("yzmSj", URLEncoder.encode(param.getStr("yzmSj", "UTF-8")));//获取验证码生成时间 进行url encode
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        param.put("index", "1b620f7b5cae12a2b370e9b38dfadbba");
//        param.put("key1", "044031800104");//发票代码
//        param.put("key2", "28527159");//发票号码
//        param.put("key3", "20190225");//开票日期
//        param.put("key4", "170515");//校验码后6位
        url = url + genGetUrlParam(param);

        HttpResponse httpResponse = doGet(url);
        String result = httpResponse.body();
        result = getJsonStrFromResult(result);
        if (StringUtils.isEmpty(result)){
            throw new IllegalArgumentException("请求参数有误,没有返回结果");
        }

      /*
         正确返回 {"key1":"001","key2":"图片base64", "key4":" ","key5":"1"}
         验证码失效 jQuery110202746766900045345_1556423183817({"key1":"007","key2":"","key3":""})
         验证码错误 jQuery110204262281891753827_1556423327838({"key1":"008","key2":"","key3":""})
         查无此票 jQuery110202746766900045345_1556423183817({"key1":"009","key2":"","key3":""})
      */
        JSONObject jsonObject = JSONUtil.parseObj(result);
        if (!StringUtils.isEmpty(jsonObject.getStr("key2"))) {
            //发票图片保存到本地
            Base64Utils.Base64ToImage(jsonObject.getStr("key2"), "/" + userId + "_result.jpg");
        }

        //html 显示 需要 data:image/png;base64, +jsonObject.getStr("key1")
        return jsonObject;
    }


    /**
     * 生成get请求参数
     *
     * @param param
     * @return
     */
    private String genGetUrlParam(JSONObject param) {
        StringBuffer reqParam = new StringBuffer();
        for (String key : param.keySet()) {
            reqParam.append("&").append(key).append("=").append(param.getStr(key));
        }
        return reqParam.toString();
    }


    /**
     * 从返回结果中解决json字符串 jQuery110204262281891753827_1556423327838({...})
     *
     * @param result
     * @return
     */
    private String getJsonStrFromResult(String result) {
        result = result.replace(")", "");
        result = result.substring(result.indexOf("(") + 1);
        return result;
    }


//    public static void main(String[] args) {
//        //ConfigurableApplicationContext context = SpringApplication.run(TaxArticleApplication.class, args);
//
//        try {
//            System.out.println(System.currentTimeMillis());
//            System.out.println(URLEncoder.encode("2019-04-28 16:25:09", "utf-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//    }

public static void main(String[] args) {
    //ConfigurableApplicationContext context = SpringApplication.run(TaxArticleApplication.class, args);
    TreeMap<String,String> param=new TreeMap<>();
    param.put("_", "1556454131803");
    param.put("area", "4403");//地区
    param.put("callback" , "jQuery110209225644205900412_1556454131795");
    param.put("fpdm", "044031800104");//发票代码
    param.put("fphm", "28527159");//发票号码
    param.put("nowtime", "1556454284882");
    param.put("r", "0.5016109841711769");
    param.put("v", "V1.0.07_001");//
    param.put("_Jo0OQK", "793BF52173F0030F3AD9412441EBBD0395FB8D65A56C2DA66DD6FB7D3C302C845F2A0A25087EA0374CFGJ1Z1Ug==");//
    String sign = SignUtil.getSign(param);
    System.out.println(sign);
    param.put("publickey", "2D1CED5EBF2F6E9A5685201286DB535F");
}

    /**
     * https://www.cnblogs.com/ealenxie/p/9179215.html
     * 常见的签名方式实现一般分为以下几个步骤 :
     *
     * 　　1 . 将所有(或者特殊)请求参数按特定规则排序；
     * 　　2 . 将请求参数按特定规则拼装为加密字符串；  (app_key?多少)
     * 　　3 . 加密算法对加密字符串进行加密，得到签名。 (加密算法 MD5)
     */
    public static class SignUtil{
        /**
         * @param params 所有的请求参数都会在这里进行排序加密
         * @return 得到签名
         */
        public static String getSign(SortedMap<String, String> params) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry entry : params.entrySet()) {
                if (!entry.getKey().equals("sign")) { //拼装参数,排除sign
                    if (!StringUtils.isEmpty(entry.getKey()) && !StringUtils.isEmpty(entry.getValue())){
                        sb.append(entry.getKey()).append(entry.getValue());
                    }
                }
            }
            return DigestUtils.md5DigestAsHex(sb.toString().getBytes()).toUpperCase();
        }

        /**
         * @param params 所有的请求参数都会在这里进行排序加密
         * @return 验证签名结果
         */
        public static boolean verifySign(SortedMap<String, String> params) {
            if (params == null || StringUtils.isEmpty(params.get("sign"))){ return false;}
            String sign = getSign(params);
            return !StringUtils.isEmpty(sign) && params.get("sign").equals(sign);
        }
    }

}

