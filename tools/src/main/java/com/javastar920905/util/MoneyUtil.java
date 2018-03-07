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
}
