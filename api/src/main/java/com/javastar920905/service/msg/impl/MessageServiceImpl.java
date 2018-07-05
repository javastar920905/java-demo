package com.javastar920905.service.msg.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.javastar920905.outer.StringUtil;
import com.javastar920905.qcloud.sms.SmsSingleSender;
import com.javastar920905.qcloud.sms.SmsSingleSenderResult;
import com.javastar920905.service.msg.MessageService;
import com.javastar920905.submail.config.AppConfig;
import com.javastar920905.submail.lib.MAILXSend;
import com.javastar920905.submail.lib.MESSAGEXsend;
import com.javastar920905.submail.lib.base.SenderWapper;
import com.javastar920905.submail.utils.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author ouzhx on 16-5-3.
 */
@Service
public class MessageServiceImpl implements MessageService {

  private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

  @Autowired
  private SmsSingleSender qcloudSmsSender;


  /**
   * 生成随机数,一般是运行商(现在只是腾讯云短信)需要的参数
   *
   * @return 随机数
   * @author chenjun
   */
  private long getRandomNumber() {
    return new Random().nextInt(999999) % (999999 - 100000 + 1) + 100000;
  }

  /**
   * 获取submail发送器
   *
   * @return MESSAGEXsend
   * @author chenjun
   */
  private SenderWapper getSend(ConfigLoader.ConfigType type) {
    // submail配置
    AppConfig config = ConfigLoader.load(type);

    switch (type) {
      case Message:
      case AuthCode:
        return new MESSAGEXsend(config);
      case Mail:
        return new MAILXSend(config);
    }

    return null;
  }

  @Override
  public String sendQcloudMessage(String receiver, String sign, int tpl_id,
      ArrayList<String> params, String extend, String ext) throws Exception {
    SmsSingleSenderResult result = qcloudSmsSender.sendWithParam("86", receiver, tpl_id, params,
        sign != null ? sign : StringUtil.EMPTY, extend, ext);
    return result != null ? result.toString() : "腾讯云短信出错";
  }

  /**
   * 发送(可退订)短信
   * 
   * @param
   * @return
   * @author ouzhx on 2017/5/8
   */
  @Override
  public void sendSubMessage(String project, String receiver, Map<String, String> params) {
    MESSAGEXsend submail = (MESSAGEXsend) getSend(ConfigLoader.ConfigType.Message);
    submail.setProject(project);
    submail.addTo(receiver);

    if (params != null) {
      for (Map.Entry<String, String> param : params.entrySet()) {
        submail.addVar(param.getKey(), param.getValue());
      }
    }

    submail.xsend();
  }

  /**
   * 发送(不可退订)短信 验证码
   * 
   * @param
   * @return
   * @author ouzhx on 2017/5/8
   */
  public void sendSubMessage2(String project, String receiver, Map<String, String> params) {
    MESSAGEXsend submail = (MESSAGEXsend) getSend(ConfigLoader.ConfigType.AuthCode);
    submail.setProject(project);
    submail.addTo(receiver);

    if (params != null) {
      for (Map.Entry<String, String> param : params.entrySet()) {
        submail.addVar(param.getKey(), param.getValue());
      }
    }

    submail.xsend();
  }

  @Override
  public void sendVerificationCode(String code, String phoneNumber) {
    Map<String, String> params = new HashMap<>();
    String templateId = "uUsHX2";
    params.put("code", code);
    sendSubMessage2(templateId, phoneNumber, params);

    // 记录发送短信历史
    //JSONObject paramJSON = (JSONObject) JSONObject.toJSON(params);
  }


}
