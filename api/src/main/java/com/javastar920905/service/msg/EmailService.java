package com.javastar920905.service.msg;

/**
 *  @author ouzhx
 */
public interface EmailService {

  /**
   * 将验证码发送至邮件
   *
   * @param email 接收验证码的邮箱
   * @param code 验证码
   * @return
   * @author liuwb
   * @date 2017/1/10
   */
  void sendCodeToEmail(String email, String code);

  String toString();
}
