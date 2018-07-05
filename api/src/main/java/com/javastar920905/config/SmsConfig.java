package com.javastar920905.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.javastar920905.constants.SubmailConstants;
import com.javastar920905.qcloud.sms.SmsSingleSender;



/**
 *
 * @author ouzhx on 2018/7/4.
 * @Link https://www.mysubmail.com/chs/documents/developer/yQZyA
 */
@Configuration
public class SmsConfig {
  /**
   * Spring JavaMailSender配置
   * 
   * @return JavaMailSender
   * @author huangzhk 2017/04/18
   */
  @Bean
  public JavaMailSenderImpl javaMailSender() {
    JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
    javaMailSenderImpl.setHost(SubmailConstants.email_smtp_host);
    javaMailSenderImpl.setUsername(SubmailConstants.email_username_noreply);
    javaMailSenderImpl.setPassword(SubmailConstants.email_password_noreply);
    return javaMailSenderImpl;
  }

  @Bean
  public SmsSingleSender smsSingleSender() throws Exception {
    return new SmsSingleSender(SubmailConstants.qcloud_sms_appid, SubmailConstants.qcloud_sms_appkey);
  }

}
