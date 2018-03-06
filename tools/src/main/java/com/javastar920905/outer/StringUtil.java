package com.javastar920905.outer;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.*;

/**
 * 字符串处理工具类
 */
public class StringUtil extends StringUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(StringUtil.class);


  /**
   * 一次性判断多个或单个字符串为空。(注:所有对象都为空时返回true)
   * 
   * @param css
   * @return
   * @author huangzhk 2016/12/16
   */
  public static boolean isAllBlank(CharSequence... css) {
    Boolean result = true;
    for (CharSequence cs : css) {
      boolean notBlank = isNotBlank(cs);
      if (notBlank) {
        result = false;
        break;
      }
    }
    return result;
  }

  /**
   * 一次性判断多个或单个字符串不为空。
   * 
   * @param css
   * @return 只要有一个元素不为Blank，则返回true
   */
  public static boolean hasNotBlank(CharSequence... css) {
    return !isAllBlank(css);
  }

  /**
   * 判断一个字符串在数组中存在几个
   * 
   * @param baseStr
   * @param strings
   * @return
   */
  public static int indexOf(String baseStr, String[] strings) {

    if (null == baseStr || baseStr.length() == 0 || null == strings)
      return 0;

    int i = 0;
    for (String string : strings) {
      boolean result = baseStr.equals(string);
      i = result ? ++i : i;
    }
    return i;
  }


  /** 将 BASE64 编码的字符串 s 进行解码 **/
  public static String getStrByBASE64(String s) {
    if (isBlank(s)) {
      return "";
    }
    Base64.Decoder decoder = Base64.getDecoder();
    byte[] b = decoder.decode(s);
    return new String(b);
  }

  /**
   * 把Map转换成get请求参数类型,如 {"name"=20,"age"=30} 转换后变成 name=20&age=30
   * 
   * @param map
   * @return
   */
  public static String mapToGet(Map<? extends Object, ? extends Object> map) {
    StringBuilder result = new StringBuilder();
    if (map == null || map.size() == 0) {
      return "";
    }
    Set<? extends Object> keys = map.keySet();
    for (Object key : keys) {
      result.append(key.toString()).append("=").append(key).append("&");
    }

    return isBlank(result) ? "" : result.substring(0, result.length() - 1);
  }

  /**
   * 把一串参数字符串,转换成Map 如"?a=3&b=4" 转换为Map{a=3,b=4}
   * 
   * @param args
   * @return
   */
  public static Map<String, ? extends Object> getToMap(String args) {
    if (isBlank(args)) {
      return null;
    }
    String newArgs = args.trim();
    // 如果是?开头,把?去掉
    if (newArgs.startsWith("?")) {
      newArgs = newArgs.substring(1, newArgs.length());
    }
    String[] argsArray = newArgs.split("&");

    Map<String, Object> result = new HashMap<>();
    for (String ag : argsArray) {
      if (!isBlank(ag) && ag.indexOf("=") > 0) {

        String[] keyValue = ag.split("=");
        // 如果value或者key值里包含 "="号,以第一个"="号为主 ,如 name=0=3 转换后,{"name":"0=3"}, 如果不满足需求,请勿修改,自行解决.

        String key = keyValue[0];
        StringBuilder value = new StringBuilder();
        for (int i = 1; i < keyValue.length; i++) {
          value.append(keyValue[i]).append("=");
        }
        result.put(key, value.length() > 0 ? value.substring(0, value.length() - 1) : "");

      }
    }

    return result;
  }

  /**
   * 转换成Unicode
   * 
   * @param str
   * @return
   */
  public static String toUnicode(String str) {
    String[] as = new String[str.length()];
    String s1 = "";
    for (int i = 0; i < str.length(); i++) {
      as[i] = Integer.toHexString(str.charAt(i) & 0xffff);
      s1 = s1 + "\\u" + as[i];
    }
    return s1;
  }

  /**
   * 把数组转换成Set 方便判断
   * 
   * @param objs
   * @return
   */
  public static TreeSet<String> arrayToSet(String[] objs) {
    TreeSet<String> result = new TreeSet<>();
    if (null == objs) {
      return result;
    }
    for (String obj : objs) {
      result.add(obj);
    }
    return result;
  }

  /**
   * 字符串转urlcode
   * 
   * @param value
   * @return
   */
  public static String strToUrlcode(String value) {
    try {
      return java.net.URLEncoder.encode(value, "utf-8");
    } catch (UnsupportedEncodingException e) {
      LOGGER.error("字符串转换为URLCode失败,value:" + value, value, e, e.getMessage());
      return null;
    }
  }

  /**
   * urlcode转字符串
   * 
   * @param value
   * @return
   */
  public static String urlcodeToStr(String value) {
    try {
      return java.net.URLDecoder.decode(value, "utf-8");
    } catch (UnsupportedEncodingException e) {
      LOGGER.error("URLCode转换为字符串失败;value:" + value, value, e, e.getMessage());
      return null;
    }
  }

  /**
   * 设置JSON字符串返回
   *
   * @param key
   * @param value
   * @return
   */
  public static String setJsonString(String key, String value) {
    if (isNoneBlank(key, value)) {
      JSONObject goodsJson = new JSONObject();
      goodsJson.put(key, value);
      return goodsJson.toString();
    }
    return "";
  }

  /**
   * 替换字符串
   *
   * @param str
   * @param nowStr
   * @param replaceStr
   * @return
   */
  public static String replaceString(String str, String nowStr, String replaceStr) {

    nowStr = isBlank(nowStr) ? "" : nowStr;
    replaceStr = isBlank(replaceStr) ? "" : replaceStr;
    if (isNotBlank(str)) {
      return str.replaceAll(nowStr, replaceStr);
    }
    return "";
  }

  /**
   *
   * 转换编码
   *
   * @param source 源字符串
   * @param sourceCode 源编码格式
   * @param targetCode 目标编码格式
   * @return 目标编码
   */
  public static String changCoding(String source, String sourceCode, String targetCode) {
    try {
      return new String(source.getBytes(sourceCode), targetCode);
    } catch (UnsupportedEncodingException e) {
      LOGGER.error("转换编码失败", source + "-" + sourceCode + "-" + targetCode, e, e.getMessage());
      return source;
    }
  }

  /**
   * 通过源字符串重复生成N次组成新的字符串。
   * 
   * @param source 源字符串
   * @param num 重复生成次数
   * @return
   */
  public static String repeat(String source, int num) {
    StringBuilder buffer = new StringBuilder();
    for (int i = 0; i < num; i++)
      buffer.append(source);
    return buffer.toString();
  }

  /**
   * 将double类型数据转换为百分比格式，并保留小数点前IntegerDigits位和小数点后FractionDigits位
   * 
   * @param value
   * @param integerDigits 小数点前IntegerDigits位
   * @param fractionDigits 小数点后FractionDigits位
   * @return
   */
  public static String getPercentFormat(double value, int integerDigits, int fractionDigits) {

    if (value >= 1.0) {
      return (value * 100) + "%";
    }

    NumberFormat nf = NumberFormat.getPercentInstance();
    nf.setMaximumIntegerDigits(integerDigits);// 小数点前保留几位
    nf.setMinimumFractionDigits(fractionDigits);// 小数点后保留几位
    String str = nf.format(value);
    return str;
  }

  /**
   * 将str首字母大写返回
   * 
   * @param str
   * @return
   * @author huangzhk 2017/02/27
   */
  public static String upperCaseFirstChar(String str) {
    if (StringUtil.isNotBlank(str)) {
      String upperCaseStr = upperCase(str);
      StringBuilder builder = new StringBuilder();
      StringBuilder appendBuilder =
          builder.append(substring(upperCaseStr, 0, 1)).append(substring(str, 1));
      return appendBuilder.toString();
    }

    return StringUtil.EMPTY;
  }

  /**
   * 生成length位的随机数字符串
   * 
   * @param length
   * @return
   * @author huangzhk 2017/03/14
   */
  public static String generateRandomNumericString(int length) {
    Random rm = new Random();
    // 获得随机数
    double pross = (1 + rm.nextDouble()) * Math.pow(10, length);

    // 将获得的获得随机数转化为字符串
    String fixLengthString = String.valueOf(pross);

    // 返回固定的长度的随机数
    return fixLengthString.substring(1, length + 1);
  }

}
