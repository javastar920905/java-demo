package com.javastar920905.submail.lib;

import com.javastar920905.submail.config.AppConfig;
import com.javastar920905.submail.lib.base.ISender;
import com.javastar920905.submail.lib.base.Sender;

import java.util.Map;



/**
 * A Sender class define the mail mode to send HTTP request.
 * 
 * @see ISender
 * @see Sender
 * @version 1.0 at 2014/10/28
 */
public class Mail extends Sender {

  private static final String API_SEND = API_HOST + "/mail/send.json";
  private static final String API_XSEND = API_HOST + "/mail/xsend.json";
  private static final String API_SUBSCRIBE = API_HOST + "/addressbook/mail/subscribe.json";
  private static final String API_UNSUBSCRIBE = API_HOST + "/addressbook/mail/unsubscribe.json";

  public Mail(AppConfig config) {
    this.config = config;
  }

  /**
   * Send request data to server.The data consists of two parts. One of them is original data,and
   * another is signature.For example,the original data is
   * <p>
   * address=jam@submail.cn?name=jam
   * </p>
   * .Then,the final request data is
   * <p>
   * address=jam@submail.cn?name=jam?appid=xxx?appkey=xxx?timestamp=xxx?signature=xxx?
   * <p>
   * The algorithm of signature is {@link #createSignature(String)}
   * 
   * @param data is the original data only contains content.
   */
  @Override
  public boolean send(Map<String, Object> data) {
    return request(API_SEND, data);
  }

  @Override
  public boolean xsend(Map<String, Object> data) {
    return request(API_XSEND, data);
  }

  @Override
  public boolean subscribe(Map<String, Object> data) {
    // TODO Auto-generated method stub
    return request(API_SUBSCRIBE, data);
  }

  @Override
  public boolean unsubscribe(Map<String, Object> data) {
    // TODO Auto-generated method stub
    return request(API_UNSUBSCRIBE, data);
  }

}
