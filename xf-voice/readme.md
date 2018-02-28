# 说明
科大讯飞语音项目接入示例
TODO 该示例还未测试通过


# 相关参考文档:
http://note.youdao.com/noteshare?id=63d125c75dfe82f07629f4fe6becc757 

# api使用
3 http://doc.xfyun.cn/msc_java/299246  api使用
与语音听写相反，语音合成（SpeechSynthesizer）是将文字信息转化为可听的声音信息，让机器像人一样开口说话。


创建用户语音配置对象后才可以使用语音服务，建议在程序入口处调用。
关于初始化时指定库名，或报加载库失败的解决办法，请参考《MSC Reference Manual》中，关于SpeechUtility类，以及SpeechConstant类的说明。
// 将“XXXXXXXX”替换成您申请的APPID 
SpeechUtility.createUtility( SpeechConstant.APPID +"=XXXXXXXX ");


查看api 文档  http://mscdoc.xfyun.cn/java/api/
合成到文件
//1.创建SpeechSynthesizer对象
SpeechSynthesizer mTts= SpeechSynthesizer.createSynthesizer( );
//2.合成参数设置，详见《MSC Reference Manual》SpeechSynthesizer 类
mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");//设置发音人
mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速，范围0~100
mTts.setParameter(SpeechConstant.PITCH, "50");//设置语调，范围0~100
mTts.setParameter(SpeechConstant.VOLUME, "50");//设置音量，范围0~100
//3.开始合成
//设置合成音频保存位置（可自定义保存位置），默认保存在“./tts_test.pcm”
mTts.synthesizeToUri("语音合成测试程序", "./tts_test.pcm",synthesizeToUriListener);

//合成监听器
SynthesizeToUriListener synthesizeToUriListener = new SynthesizeToUriListener() {
	//progress为合成进度0~100 
	public void onBufferProgress(int progress) {}
    //会话合成完成回调接口
	//uri为合成保存地址，error为错误信息，为null时表示合成会话成功
	public void onSynthesizeCompleted(String uri, SpeechError error) {}
};
