package com.javastar920905.service.msg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author ouzhx
 */
public interface MessageService {

  /**
   * 发送短信验证码
   *
   * @param code 验证码
   * @param phoneNumber 接收验证码的手机号码
   * @authro chenjun
   */
  void sendVerificationCode(String code, String phoneNumber);

  /**
   * 通过腾讯云发送短信
   *
   * @param receiver 接收人手机号
   * @param sign 短信签名，如果是默认签名,则传入Null或者空字符串
   * @param tpl_id 业务在控制台审核通过的模板ID
   * @param params 参数列表，将依次替换模板中的参数,如：["验证码", "1234", "4"]
   * @param extend 在短信回复场景中，腾讯server会原样返回，开发者可依此区分是哪种类型的回复
   * @param ext 可选字段，不需要就填空。用户的session内容，腾讯server回包中会原样返回
   * @return { "result": "0", //0表示成功(计费依据)，非0表示失败 "errmsg": "", //result非0时的具体错误信息 "ext": "some
   *         msg", //用户的session内容，腾讯server回包中会原样返回 "sid": "xxxxxxx", //标识本次发送id "fee": 1 //短信计费的条数 }
   * @throws IOException
   * @author chenjun
   */
  String sendQcloudMessage(String receiver, String sign, int tpl_id, ArrayList<String> params,
      String extend, String ext) throws Exception;



  /**
   * 通过submail发送短信消息
   *
   * @param project submail中项目编号
   * @param receiver 接收人手机号
   * @param params key为参数名，value为参数值
   * @author chenjun
   */
  void sendSubMessage(String project, String receiver, Map<String, String> params);



  String toString();
}
