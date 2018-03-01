package com.javastar920905.voice.baidu;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import org.json.JSONObject;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author ouzhx on 2018/3/1.
 * 百度语音合成demo   sdk文档http://ai.baidu.com/docs#/TTS-Online-Java-SDK/top (当前版本 4.1.1)
 */
public class VoiceCombine {

    //设置APPID/AK/SK
    public static final String APP_ID = "10868285";
    public static final String API_KEY = "";
    public static final String SECRET_KEY = "";

    /**
     * 新建AipSpeech
     * AipSpeech是语音识别的Java客户端，为使用语音识别的开发人员提供了一系列的交互方法。
     * <p>
     * 用户可以参考如下代码新建一个AipSpeech,初始化完成后建议单例使用,避免重复获取access_token：
     */
    public static AipSpeech getAipSpeech() {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

       /* // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理*/

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        // System.setProperty("aip.log4j.conf", "log4j.properties");

        return client;
    }


    public static void hecheng1(AipSpeech client) {
        // 调用接口
        TtsResponse res = client.synthesis("这是百度语音合成demo", "zh", 1, null);
        byte[] data = res.getData();
        JSONObject res1 = res.getResult();
        if (data != null) {
            try {
                Util.writeBytesToFileSystem(data, "output.mp3");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (res1 != null) {
            System.out.println(res1.toString(2));
        }

    }

    private static void bytes2File(byte[] data, String fileName) {
        Assert.notNull(data, "声音文件不能为空!");
        try {
            Util.writeBytesToFileSystem(data, fileName);
            System.out.println("语音合成结束! 文件生成在当前项目根目录下");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 语音合成
     * 接口描述
     * 基于该接口，开发者可以轻松的获取语音合成能力
     * <p>
     * 请求说明
     * 合成文本长度必须小于1024字节，如果本文长度较长，可以采用多次请求的方式。文本长度不可超过限制
     *
     * @param client
     */
    public static void synthesis(AipSpeech client) {
        TtsResponse res = client.synthesis("这是百度语音合成demo", "zh", 1, null);
        //服务器返回的内容，合成成功时为null,失败时包含error_no等信息
        if (res.getResult() == null) {
            System.out.println("合成成功!");

            //生成的音频数据
            byte[] voiceData = res.getData();
            if (voiceData != null) {
                bytes2File(voiceData, "baiduVoiceDemo.mp3");
            }
        }



        // 设置可选参数
        HashMap<String, Object> options = new HashMap<>();
        options.put("spd", "5");
        options.put("pit", "5");
        options.put("per", "4");
        TtsResponse res2 = client.synthesis("这是百度语音合成demo  女生 ", "zh", 1, options);
        //服务器返回的内容，合成成功时为null,失败时包含error_no等信息
        if (res2.getResult() == null) {
            System.out.println("合成成功!");

            //生成的音频数据
            byte[] voiceData = res2.getData();
            if (voiceData != null) {
                bytes2File(voiceData, "baiduVoiceDemo2.mp3");
            }
        }

    }

    public static void main(String[] args) {
        try {
            synthesis(getAipSpeech());
            hecheng1(getAipSpeech());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
