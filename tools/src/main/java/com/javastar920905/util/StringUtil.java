package com.javastar920905.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author ouzhx on 2018/3/3.
 */
public class StringUtil {

  /**
   * 数组转String 用,隔开
   **/
  public static String arrayToString(String[] arrays) {
    if (arrays != null && arrays.length > 0) {
      StringBuilder stringBuilder = new StringBuilder();
      for (String s : arrays) {
        stringBuilder.append(s).append(",");
      }
      String str = stringBuilder.toString();
      return str.substring(0, str.length() - 1);
    } else {
      return "";
    }
  }

  /**
   * 打印异常信息
   */
  public static String stackTraceToString(Throwable e) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw, true);
    e.printStackTrace(pw);
    pw.flush();
    sw.flush();
    return sw.toString();
  }

}
