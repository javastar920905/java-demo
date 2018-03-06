package com.javastar920905.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ouzhx on 2017/12/26.
 *
 *         通用常量 (编码格式,时间格式)
 */
public interface CommonConstants {
  /**
   * 多个子系统通用的cookie 名称
   */
  String SSOSESSION = "SSO_SESSION";

  @AllArgsConstructor
  @Getter
  enum Charset {
    utf8("utf-8"), gbk("gbk");
    private String value;
  }

  @AllArgsConstructor
  @Getter
  enum DateForm {
    yyyyMMdd_("yyyy-MM-dd"), MMddyyyy_("MM-dd-yyyy"), yyyyMMddHHmmss_(
        "yyyy-MM-dd hh:mm:ss"), yyyyMMdd(
            "yyyy/MM/dd"), MMddyyyy("MM/dd/yyyy"), yyyyMMddHHmmss("yyyy/MM/dd hh:mm:ss");
    private String value;
  }

}
