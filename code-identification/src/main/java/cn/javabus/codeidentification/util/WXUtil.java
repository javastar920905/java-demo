package cn.javabus.codeidentification.util;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author ouzhx on 2018/8/17.
 * 微信小程序工具类
 */
@Log4j2
@Component
public class WXUtil {
    private WXUtil() {
    }

    /**
     * 登陆校验url
     * 参考文档: https://developers.weixin.qq.com/miniprogram/dev/api/api-login.html#wxloginobject
     * 会话密钥session_key 是对用户数据进行加密签名的密钥。为了应用自身的数据安全，开发者服务器不应该把会话密钥下发到小程序，也不应该对外提供这个密钥。
     */
    private static final String login_verify_url = "https://api.weixin.qq.com/sns/jscode2session?appid={APPID}&secret={SECRET}&js_code={JSCODE}&grant_type=authorization_code";

    /**
     * 获取小程序 access_token
     * 参考文档 https://developers.weixin.qq.com/miniprogram/dev/api/open-api/access-token/getAccessToken.html
     */
    private static String get_access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}";
    //存储用户登录信息
    private static final Map<String, String> tax_UserMap = new HashMap<>();


    @Component
    public static class Tax implements CommandLineRunner {
        public static String appid;
        private static String secret;

        @Override
        public void run(String... args) throws Exception {
            initAppId();
        }

        private static void initAppId() {
            appid = SpringContextUtil.getProperty("mp.appid");
            secret = SpringContextUtil.getProperty("mp.secret");
            log.info("appid:{} ,secret:{}", appid, secret);
            if (appid != null) {
                get_access_token_url = get_access_token_url.replace("{APPID}", appid).replace("{APPSECRET}", secret);
            } else {
                log.error("appid:{} ,secret:{}", appid, secret);
            }
        }

        static String buildLoginVeriifyUrl(String code) {
            return login_verify_url.replace("{APPID}", appid).replace("{SECRET}", secret).replace("{JSCODE}", code);
        }


        /**
         * 小程序端调用登录方法前一定要 调用wx.checkSession(OBJECT),校验用户当前session_key是否有效:
         * 1 避免反复调用商户login接口
         * 2  wx.login()调用时，用户的session_key会被更新而致使旧session_key失效,导致后期接口数据的解密失败。
         *
         * @param code
         * @return
         */
        public static JSONObject doLogin(String code) {
            String content = HttpUtil.get(buildLoginVeriifyUrl(code));
            JSONObject json = JSONUtil.parseObj(content);
            String openId = json.getStr("openid");
            if (openId != null) {
                String session_key = json.getStr("session_key");
                tax_UserMap.put(openId, session_key);
                //session_key 不暴露给小程序
                json.remove("session_key");
            }
            return json;
        }

        /**
         * 获取小程序access token access_token的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取的access_token失效。
         * access_token	string	获取到的凭证
         * expires_in	number	凭证有效时间，单位：秒
         * errcode	number	错误码
         * errmsg	string	错误信息
         *
         * @return
         */
        public static JSONObject getAccessToken() {
            if (appid == null) {
                initAppId();
            }
            //
            String content = HttpUtil.get(get_access_token_url);
            JSONObject json = JSONUtil.parseObj(content);
            return json;
        }


        /**
         * 数据解密
         *
         * @return
         */
        public static JSONObject decodeData(String openId, String encryptedData, String iv) {
            return decrypt(encryptedData, tax_UserMap.get(openId), iv);
        }

    }

    /**
     * 小程序数据解密算法封装
     *
     * @param encryptedData
     * @param session_key
     * @param iv
     */
    public static JSONObject decrypt(String encryptedData, String session_key, String iv) {
        Map map = new HashMap();
        try {
            byte[] resultByte = AES.decrypt(Base64.decodeBase64(encryptedData),
                    Base64.decodeBase64(session_key),
                    Base64.decodeBase64(iv));
            if (null != resultByte && resultByte.length > 0) {
                String userInfo = new String(resultByte, "UTF-8");
                map.put("status", "1");
                map.put("msg", "解密成功");
                map.put("userInfo", userInfo);
            } else {
                map.put("status", "0");
                map.put("msg", "解密失败");
            }
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return JSONUtil.parseObj(map);
    }

    private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final Random RANDOM = new SecureRandom();

    /**
     * 获取随机字符串 Nonce Str
     *
     * @return String 随机字符串
     */
    public static String generateNonceStr() {
        char[] nonceChars = new char[32];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }





    /**
     * 获取当前时间戳，单位秒
     *
     * @return
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取当前时间戳，单位毫秒
     *
     * @return
     */
    public static long getCurrentTimestampMs() {
        return System.currentTimeMillis();
    }


}
