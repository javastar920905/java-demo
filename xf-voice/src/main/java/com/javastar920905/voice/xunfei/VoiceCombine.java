package com.javastar920905.voice.xunfei;


import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechSynthesizer;
import com.iflytek.cloud.speech.SpeechUtility;
import com.iflytek.cloud.speech.SynthesizeToUriListener;
import com.iflytek.cloud.speech.SynthesizerListener;

import java.util.concurrent.TimeUnit;

/**
 * @author ouzhx on 2018/2/28.
 * <p>
 * 语音合成示例  http://doc.xfyun.cn/msc_java/299247
 * <p>
 * TODO 改示例还未测试通过
 * 合成出现问题.错误代码：20021
 * 错误原因：引擎错误.
 */
public class VoiceCombine {
    public static void main(String[] args) {
        // 将“XXXXXXXX”替换成您申请的APPID
        //SpeechUtility.createUtility(SpeechConstant.APPID + "=5a964e39");
        SpeechUtility.createUtility(String.format("%s= %s ", SpeechConstant.APPID, "5a964e39"));

        try {
            System.out.println("睡眠2s....");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        hecheng2();
    }

    public static void hecheng2() {

        //1.创建SpeechSynthesizer对象
        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer();
        //2.合成参数设置，详见《MSC Reference Manual》SpeechSynthesizer 类
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");//设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速，范围0~100
        mTts.setParameter(SpeechConstant.PITCH, "50");//设置语调，范围0~100
        mTts.setParameter(SpeechConstant.VOLUME, "50");//设置音量，范围0~100
        //3.开始合成
        //设置合成音频保存位置（可自定义保存位置），默认保存在“./tts_test.pcm”
        mTts.synthesizeToUri("语音合成测试程序", "./tts_test.pcm", synthesizeToUriListener);
    }


    public static void hecheng() {
        //1.创建 SpeechSynthesizer 对象
        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer();
        //2.合成参数设置，详见《MSC Reference Manual》SpeechSynthesizer 类
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");//设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围 0~100
        //设置合成音频保存位置（可自定义保存位置），保存在“./tts_test.pcm”
        //如果不需要保存合成音频，注释该行代码
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./tts_test.pcm");

        //3.开始合成
        mTts.startSpeaking("语音合成测试程序", mSynListener);
    }


    /**
     * 合成监听器2
     **/
    static SynthesizeToUriListener synthesizeToUriListener = new SynthesizeToUriListener() {
        //progress为合成进度0~100
        @Override public void onBufferProgress(int progress) {
            System.out.println("合成进度..." + progress);
        }

        //会话合成完成回调接口
        //uri为合成保存地址，error为错误信息，为null时表示合成会话成功
        @Override public void onSynthesizeCompleted(String uri, SpeechError error) {
            if (error == null) {
                System.out.println("合成成功");
            } else {
                System.out.println("合成出现问题." + error.toString());
            }
            System.out.println("会话合成完成 保存地址" + uri);
        }

        @Override public void onEvent(int i, int i1, int i2, int i3, Object o, Object o1) {

        }
    };


    /**
     * 合成监听器1
     **/
    private static SynthesizerListener mSynListener = new SynthesizerListener() {
        //会话结束回调接口，没有错误时，error为null
        @Override public void onCompleted(SpeechError error) {
        }

        //缓冲进度回调
        //percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在 文本中结束位置，info为附加信息。
        @Override public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
        }

        //开始播放
        @Override public void onSpeakBegin() {
            System.out.println("开始播放...");
        }

        //暂停播放
        @Override public void onSpeakPaused() {
            System.out.println("暂停播放...");
        }

        //播放进度回调
        //percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文本中结束位置.
        @Override public void onSpeakProgress(int percent, int beginPos, int endPos) {
        }

        //恢复播放回调接口
        @Override public void onSpeakResumed() {
        }

        @Override public void onEvent(int i, int i1, int i2, int i3, Object o, Object o1) {
        }
    };
}
