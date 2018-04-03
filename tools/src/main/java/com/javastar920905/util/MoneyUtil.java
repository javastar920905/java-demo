package com.javastar920905.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Formatter;
import java.util.Random;

/**
 * @author ouzhx on 2018/3/7.
 *
 *         金额处理
 */
public class MoneyUtil {
  private static final Formatter FORMATTER = new Formatter();
  private static final Random RANDOM = new Random();
  private static BigDecimal decimal100 = new BigDecimal(100);

  /**
   * 生成min到max范围的浮点数
   */
  public static double nextDouble(final double min, final double max) {
    return min + ((max - min) * RANDOM.nextDouble());
  }

  /**
   * 金额保留精度两位(向上取整) 可以参考文档 http://www.jb51.net/article/98049.htm
   * 
   * @param number (4.269480)
   * @return (4.27)
   */
  public static String numberUp(Number number) {
    /*
     * BigDecimal bd = new BigDecimal(number); bd = bd.setScale(2, RoundingMode.HALF_UP); return
     * bd.toString();
     */

    // String.format("%.2f", number).toString()
    return FORMATTER.format("%.2f", number).toString();
  }

  /**
   * 金额保留精度两位(向下取整)
   * 
   * @param number 4.269480
   * @return (4.26)
   */
  public static String numberDown(Number number) {
    String value = String.format("%.3f", number).toString();
    value = value.substring(0, value.indexOf(".") + 3);
    return value;
  }

  /**
   * 
   * @param format 指定精度("%.2f")
   * @param number 要格式化的数值
   * @return
   */
  public static String numberFormat(String format, Number number) {
    // String.format("%.2f", value).toString()
    return FORMATTER.format(format, number).toString();
  }

  /**
   * 将分为单位的转换为元 （除100） 保留两位小数 150->1.50
   *
   * @param amount
   * @return
   * @throws Exception
   */
  public static String changeF2Y(Integer amount) {
    return String.format("%.2f", BigDecimal.valueOf(amount).divide(decimal100));
  }

  /**
   * 保留2位小数但不会自动补0 150->1.;5 151->1.51
   * 
   * @param amount
   * @return
   */
  public static String changeF2Y1(Integer amount) {
    return amount / 100.00 + "";
  }

  /**
   * 将元为单位的转换为分 （乘100）
   *
   * @param amount
   * @return
   */
  public static Integer changeY2F(Long amount) {
    return BigDecimal.valueOf(amount).multiply(decimal100).intValue();
  }

}
