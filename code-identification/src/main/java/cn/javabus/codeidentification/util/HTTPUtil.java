package cn.javabus.codeidentification.util;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;

import java.util.HashMap;

/**
 * @author ouzhx on 2019/4/28.
 * <p>
 * hutool 参考文档: https://hutool.cn/docs/#/http/Http%E8%AF%B7%E6%B1%82-HttpRequest
 */
public class HTTPUtil {

//    public static String doPost(String url, HashMap<String, Object> paramMap) {
//        //文件上传只需将参数中的键指定（默认file），值设为文件对象即可，对于使用者来说，文件上传与普通表单提交并无区别
//        //paramMap.put("file", FileUtil.file("D:\\face.jpg"));
//        String result = HttpUtil.post(url, paramMap);
//        return result;
//    }
//
//    public static String doGet(String url) {
//        //文件上传只需将参数中的键指定（默认file），值设为文件对象即可，对于使用者来说，文件上传与普通表单提交并无区别
//        //paramMap.put("file", FileUtil.file("D:\\face.jpg"));
//        String result = HttpUtil.get(url);
//        return result;
//    }

    public static HttpRequest installCommonHeader(HttpRequest httpRequest) {
        return httpRequest.header(Header.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko")
                .header(Header.ACCEPT, "application/javascript, */*; q=0.8")
                .header(Header.ACCEPT_LANGUAGE, "zh-CN")
                .header(Header.CONNECTION, "Keep-Alive")
                .header(Header.ACCEPT_ENCODING,"gzip, deflate" )
                ;
    }

    public static HttpRequest installSpiderHeader(HttpRequest httpRequest) {
        return installCommonHeader(httpRequest)
                .header(Header.HOST, "fpcy.beijing.chinatax.gov.cn")
                .header(Header.REFERER, "https://inv-veri.chinatax.gov.cn/")
                ;
    }

    public static String doPost(String url, HashMap<String, Object> paramMap) {
        HttpRequest httpRequest = HttpRequest.post(url)
                .form(paramMap)//表单内容
                .timeout(20000);//超时，毫秒

        String result = installSpiderHeader(httpRequest).execute().body();
        return result;
    }

    public static String doGet(String url) {
        //文件上传只需将参数中的键指定（默认file），值设为文件对象即可，对于使用者来说，文件上传与普通表单提交并无区别
        //paramMap.put("file", FileUtil.file("D:\\face.jpg"));
        HttpRequest httpRequest = HttpRequest.get(url);
        httpRequest = installSpiderHeader(httpRequest);
        String result = httpRequest.execute().body();
        return result;
    }

}
