package com.javastar920905.service;

import com.alibaba.fastjson.JSONObject;
import com.javastar920905.constant.CommonConstants;

/**
 * @author ouzhx on 2018/3/7.
 */
public interface IBaseService {
  String MSG = CommonConstants.key.msg.name();
  String RESULT = CommonConstants.key.result.name();
  String DATA = CommonConstants.key.data.name();

  /**
   * 返回成功消息
   * 
   * @param msg
   * @return
   */
  default JSONObject buildSuccessResult(String msg) {
    JSONObject json = new JSONObject();
    json.put(MSG, msg);
    json.put(RESULT, true);
    return json;
  }

  /**
   * 返回成功消息,不覆盖原json
   * 
   * @param msg
   * @param json
   * @return
   */
  default JSONObject buildSuccessResult(String msg, JSONObject json) {
    json.put(MSG, msg);
    json.put(RESULT, true);
    return json;
  }

  /**
   * 返回失败消息
   * 
   * @param msg
   * @return
   */
  default JSONObject buildfailResult(String msg) {
    JSONObject json = new JSONObject();
    json.put(MSG, msg);
    json.put(RESULT, false);
    return json;
  }

  default JSONObject buildfailResult(String msg, JSONObject json) {
    json.put(MSG, msg);
    json.put(RESULT, false);
    return json;
  }

  default JSONObject buildResult(int result) {
    if (result > 0) {
      return buildSuccessResult("操作成功!");
    } else {
      return buildfailResult("操作失败!");
    }
  }

  default JSONObject buildResult(int result, String successMsg, String failedMsg) {
    if (result > 0) {
      return buildSuccessResult(successMsg);
    } else {
      return buildfailResult(failedMsg);
    }
  }
}
