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
  public static boolean matches(String regex, String input) {
    return Pattern.compile(regex).matcher(input).matches();
  }

  /**
   * 验证是否是手机号
   *
   * @param phone
   * @return
   */
  public static boolean verifyPhone(String phone) {
    return matches("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$", phone);
  }

  /**
   * 验证是否是邮箱
   *
   * //p{Alpha}:内容是必选的，和字母字符[\p{Lower}\p{Upper}]等价。如：200896@163.com不是合法的。
   *
   * //w{2,15}: 2~15个[a-zA-Z_0-9]字符；w{}内容是必选的。 如：dyh@152.com是合法的。
   *
   * //[a-z0-9]{3,}：至少三个[a-z0-9]字符,[]内的是必选的；如：dyh200896@16.com是不合法的。
   *
   * //[.]:'.'号时必选的； 如：dyh200896@163com是不合法的。
   *
   * //p{Lower}{2,}小写字母，两个以上。如：dyh200896@163.c是不合法的。
   *
   * @param email
   * @return
   */
  public static boolean verifyEmail(String email) {
    return matches("\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}", email);
  }

  /**
   * 清除所有标签
   *
   * @param str 参数如: <a href="xx">跳转到</a>
   * @return "跳转到" 去除了标签 常用组合: .replace("到","")
   */
  public static String trimTag(String str) {
    return StringUtil.trim(str.replaceAll("<.+?>", ""));
  }

  /** 清除标签属性 **/
  public static String trimAttr(String str) {
    return str.replaceAll("\\w+?=\".+?\"", "");
  }
}
