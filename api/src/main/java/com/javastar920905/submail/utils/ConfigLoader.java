package com.javastar920905.submail.utils;

import static com.javastar920905.constants.SubmailConstants.submail_mail_appid_auth_code;
import static com.javastar920905.constants.SubmailConstants.submail_mail_appid_common;
import static com.javastar920905.constants.SubmailConstants.submail_mail_appkey_auth_code;
import static com.javastar920905.constants.SubmailConstants.submail_mail_appkey_common;
import static com.javastar920905.constants.SubmailConstants.submail_sms_appid_common;
import static com.javastar920905.constants.SubmailConstants.submail_sms_appkey_common;

import java.util.Properties;

import com.javastar920905.submail.config.AppConfig;
import com.javastar920905.submail.config.MailConfig;
import com.javastar920905.submail.config.MessageConfig;



/**
 * A class obtain the value and create configuration object by loading file
 * app_config.properties,including creating <strong>MailConfig<strong> and creating
 * <strong>MessageConfig<strong>.
 * 
 * @see AppConfig
 * @see MailConfig
 * @see MessageConfig
 * @version 1.0 at 2014/10/28
 */
public class ConfigLoader {

  private static Properties pros = null;
  /**
   * Loading file while class loading.The operation inside of static block will be run once.
   */
  static {
    pros = new Properties();
    pros.setProperty("msg_appid", submail_sms_appid_common);
    pros.setProperty("msg_appkey", submail_sms_appkey_common);
    pros.setProperty("mail_appid", submail_mail_appkey_common);
    pros.setProperty("mail_appkey", submail_mail_appid_common);
  }

  /**
   * A static method for outer class to create configuration by loading file.
   *
   * @param type ConfigType
   * @return If the type is ConfigType#Mail,return the instance of {@link MailConfig}. And,if the
   *         type is ConfigType#Message,return the instance of {@link MessageConfig}.
   */
  public static AppConfig load(ConfigType type) {
    switch (type) {
      case Mail:
        return createMailConfig();
      case Message:
        return createMessageConfig();
      case AuthCode:
        return createAuthcodeConfig();
      default:
        return null;
    }
  }

  private static AppConfig createMailConfig() {
    AppConfig config = new MailConfig();
    config.setAppId(pros.getProperty(MailConfig.APP_ID));
    config.setAppKey(pros.getProperty(MailConfig.APP_KEY));
    config.setSignType(pros.getProperty(MailConfig.APP_SIGNTYPE));
    return config;
  }

  private static AppConfig createMessageConfig() {
    AppConfig config = new MessageConfig();
    config.setAppId(pros.getProperty(MessageConfig.APP_ID));
    config.setAppKey(pros.getProperty(MessageConfig.APP_KEY));
    config.setSignType(pros.getProperty(MessageConfig.APP_SIGNTYPE));
    return config;
  }

  private static AppConfig createAuthcodeConfig() {
    AppConfig config = new MessageConfig();
    config.setAppId(submail_mail_appid_auth_code);
    config.setAppKey(submail_mail_appkey_auth_code);
    config.setSignType(pros.getProperty(MessageConfig.APP_SIGNTYPE));
    return config;
  }

  /**
   * enum define two kinds of configuration. Message 2 是用于短信验证码用的,避免被用户退订
   */
  public enum ConfigType {
    Mail, Message, AuthCode
  }

}
