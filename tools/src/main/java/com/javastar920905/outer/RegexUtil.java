package com.javastar920905.outer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenjun on 2016/12/8.
 */
public class RegexUtil {

  public static final String REGEX_EMAIL = "[a-z_0-9.-]{1,64}@([a-z0-9-]{1,200}.){1,5}[a-z]{1,6}";

  public static final String REGEX_CHINESE = "[\\u4E00-\\u9FFF]";

  public static final String REGEX_PHONE = "[\\d]+([\\s-]*[\\d]*)*";

  /**
   * 提取汉字
   *
   * @param input 输入的字符串
   * @param condition 优先条件(* 0或更多次,+ 1次或更多次)
   * @param global 是否全局搜索
   * @return 符合表达式的汉字
   * @author chenjun
   */
  public static String extractChinese(String input, String condition, boolean global) {
    if (StringUtil.isNotBlank(input)) {
      StringBuilder builder = new StringBuilder();
      Matcher matcher =
          Pattern.compile(REGEX_CHINESE + (condition == null ? "" : condition)).matcher(input);
      while (matcher.find()) {
        if (global) {
          builder.append(matcher.group());
        } else {
          return matcher.group();
        }
      }

      return builder.toString();
    }
    return null;
  }

  /**
   * 判断输入的字符串是否包含中文
   *
   * @param input 输入字符串
   * @return true or false
   * @author chenjun
   */
  public static boolean containChinese(String input) {
    String result = extractChinese(input, "+", false);

    return result == null ? false : true;
  }

  /**
   * 提取emial地址
   *
   * @param input 输入字符串
   * @param global 是否全局搜索
   * @return email格式字符串,如果有多个,采用逗号分隔
   * @author chenjun
   */
  public static String extractEmail(String input, boolean global) {
    return extract(REGEX_EMAIL, input, global);
  }

  /**
   * 提取数字
   *
   * @param input 输入的字符串
   * @param condition 优先条件(* 0或更多次,+ 1次或更多次等)
   * @param global 是否全局搜索
   * @return 匹配的数字字符串
   */
  public static String extractNumber(String input, String condition, boolean global) {
    if (StringUtil.isNotBlank(input)) {
      StringBuilder builder = new StringBuilder();
      Matcher matcher =
          Pattern.compile("[\\d]" + (condition == null ? "" : condition)).matcher(input);
      while (matcher.find()) {
        if (global) {
          builder.append(matcher.group());
        } else {
          return matcher.group();
        }
      }

      return builder.toString();
    }

    return null;
  }

  /**
   * 提取电话号码
   *
   * @param input 输入字符串
   * @param global 是否全局搜索
   * @return 电话号码,如果有多个,采用逗号分隔
   * @author chenjun
   */
  public static String extractPhone(String input, boolean global) {
    return extract(REGEX_PHONE, input, global);
  }

  /**
   * 根据表达式提取
   *
   * @param regex 正则表达式
   * @param input 输入字符串
   * @param global 是否全局搜索
   * @return 符合条件的结果,如果有多个,采用逗号分隔
   * @author chenjun
   */
  public static String extract(String regex, String input, boolean global) {
    if (StringUtil.isNotBlank(input)) {
      StringBuilder builder = new StringBuilder();
      Matcher matcher = Pattern.compile(regex).matcher(input);
      while (matcher.find()) {
        if (global) {
          if (builder.length() > 0) {
            builder.append(",");
          }
          builder.append(matcher.group());
        } else {
          return matcher.group();
        }
      }

      return builder.toString();
    }
    return null;
  }

  /**
   * 是否符合正则表达式
   *
   * @param regex 正则表达式
   * @param input 匹配的字符串
   * @return 是否匹配
   * @author chenjun
   */
  public static boolean matches(String regex,String input){
    return Pattern.compile(regex).matcher(input).matches();
  }
}
