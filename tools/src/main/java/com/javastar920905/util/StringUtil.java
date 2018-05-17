package com.javastar920905.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;
import java.util.UUID;

/**
 * @author ouzhx on 2018/3/3.
 */
public class StringUtil {

  public static String generateUUID() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }

  /*** 定义一个字符串（A-Z，a-z，0-9）即62位； **/
  private static final String str =
      "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

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

  /**
   * 获取精确到秒的时间戳 (java的时间戳是精确到毫秒的,精确到秒只需将最后3位去掉)
   */
  public static String getTimeStamp(Throwable e) {
    String string = String.valueOf(System.currentTimeMillis());
    string = string.substring(0, string.length() - 3);
    return string;
  }

  /**
   * 
   * @param length 产生的随机字符串位数
   * @return
   */
  public static String getRandomString(int length) {
    // 由Random生成随机数
    Random random = new Random();
    StringBuilder sb = new StringBuilder();
    // 长度为几就循环几次
    for (int i = 0; i < length; ++i) {
      // 产生0-61的数字
      int number = random.nextInt(62);
      // 将产生的数字通过length次承载到sb中
      sb.append(str.charAt(number));
    }
    // 将承载的字符转换成字符串
    return sb.toString();
  }

  /**
   * 生成uuid, UUID的唯一缺陷在于生成的结果串会比较长
   * 
   * UUID是由一个十六位的数字组成,表现出来的形式例如:550E8400-E29B-11D4-A716-446655440000
   *
   * UUID 是指在一台机器上生成的数字，它保证对在同一时空中的所有机器都是唯一的。通常平台会提供生成的API
   * 
   * UUID由以下几部分的组合：
   * 
   * （1）当前日期和时间，UUID的第一个部分与时间有关，如果你在生成一个UUID之后，过几秒又生成一个UUID，则第一个部分不同，其余相同。
   * 
   * （2）时钟序列
   * 
   * （3）全局唯一的IEEE机器识别号，如果有网卡，从网卡MAC地址获得，没有网卡以其他方式获得。
   * 
   * @return
   */
  public static String getUUId() {
    UUID uuid = UUID.randomUUID();
    return uuid.toString().replaceAll("-", "");
  }

}
