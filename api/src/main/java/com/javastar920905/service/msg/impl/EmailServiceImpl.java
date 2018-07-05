package com.javastar920905.service.msg.impl;

import com.javastar920905.service.msg.EmailService;
import com.javastar920905.submail.config.AppConfig;
import com.javastar920905.submail.lib.MAILXSend;
import com.javastar920905.submail.lib.MESSAGEXsend;
import com.javastar920905.submail.lib.base.SenderWapper;
import com.javastar920905.submail.utils.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * @author ouzhx on 16-5-3.
 */
@Service
public class EmailServiceImpl implements EmailService {

  private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

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
  public void sendCodeToEmail(String email, String code) {
    MAILXSend submail = (MAILXSend) getSend(ConfigLoader.ConfigType.Mail);
    submail.setProject("HirdH1");
    submail.addTo(email, email);
    submail.addVar("code", code);

    // 记录发送验证码邮件历史
    LOGGER.info("发送验证码邮件参数值为:email:{},code:{}", email, code);

    submail.xsend();
  }

}
