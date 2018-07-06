package com.javastar920905.constant;

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

  /**
   * 常量表缓存名称
   */
  String CACHE_CONSTANTS = "cache:constants";

  /**
   * 返回结果通用key (作为一种规范)
   */
  enum key {
    msg, result, data
  }


  /**
   * 使用方式 Charset.utf8.value (输出:utf-8); Charset.utf8.name() (输出:utf8)
   */
  @Getter
  enum Charset {
    utf8("utf-8"), gbk("gbk");
    public String value;

    Charset(String value) {
      this.value = value;
    }
  }


  @Getter
  enum DateForm {
    yyyyMMdd_("", "yyyy-MM-dd"), MMddyyyy_("", "MM-dd-yyyy"), yyyyMMddHHmmss_("",
        "yyyy-MM-dd hh:mm:ss"), yyyyMMdd("",
            "yyyy/MM/dd"), MMddyyyy("", "MM/dd/yyyy"), yyyyMMddHHmmss("", "yyyy/MM/dd hh:mm:ss");
    private String name;
    private String value;

    DateForm(String name, String value) {
      this.name = name;
      this.value = value;
    }
  }

}
