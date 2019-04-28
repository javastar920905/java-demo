package cn.javabus.codeidentification.spider.design;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ouzhx on 2019/4/28.
 * <p>
 * 实现该接口的实现方法,具备自定义请求头的能力
 */
public interface CustomHTTPUtil {
    Map<String, HttpCookie> USER_COOKIE = new HashMap<>();


    /**
     * @param httpRequest
     * @return
     */
    //    return httpRequest.header(Header.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko")
//            .header(Header.ACCEPT, "application/javascript, */*; q=0.8")
//                .header(Header.ACCEPT_LANGUAGE, "zh-CN")
//                .header(Header.CONNECTION, "Keep-Alive")
//                .header(Header.HOST, "fpcy.beijing.chinatax.gov.cn")
//                .header(Header.REFERER, "https://inv-veri.chinatax.gov.cn/")
//            ;
    HttpRequest installSpiderHeader(HttpRequest httpRequest);

    default HttpResponse doPost(String url, HashMap<String, Object> paramMap) {
        HttpRequest httpRequest = HttpRequest.post(url)
                .form(paramMap)
                .timeout(20000);

        return installSpiderHeader(httpRequest).execute();
    }

    default HttpResponse doGet(String url) {
        HttpRequest httpRequest = HttpRequest.get(url);
        httpRequest = installSpiderHeader(httpRequest);
        HttpResponse httpResponse = httpRequest.execute();
//        List<HttpCookie> cookies = httpResponse.getCookies();
//        for (HttpCookie cookie : cookies) {
//            System.out.println(cookie.getName()+" " +cookie.getValue());
////            JSESSIONID r7ljJr92r1T2xCH6v810epq8gDcbPPgSmxieCD4LMFS-0HaCDWly!363682313
////            usersession r7ljJr92r1T2xCH6v810epq8gDcbPPgSmxieCD4LMFS-0HaCDWly!363682313!1556441644918
//        }

//        System.out.println(cookieStr);

        return httpResponse;
    }

}
