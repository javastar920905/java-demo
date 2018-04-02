package com.javastar920905.concurent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author ouzhx on 2018/4/2.
 * 
 *         并发包 时间工具类介绍 https://www.cnblogs.com/shamo89/p/7055094.html
 */
public class TimeUtilApiDemo {
  @Test
  public void timeUtilApi() {
    try {
      // 增加可读写,一眼看出休息 多少秒/分/时/天...
      System.out.println("睡眠1秒");
      TimeUnit.SECONDS.sleep(1);
      /*
       * TimeUnit.MINUTES.sleep(4); TimeUnit.HOURS.sleep(1); TimeUnit.DAYS.sleep(1);
       */

      // 关于秒的常用方法
      System.out.println("1秒转换为毫秒数 " + TimeUnit.SECONDS.toMillis(1));
      System.out.println("60秒转换为分钟数 " + TimeUnit.SECONDS.toMinutes(60));
      System.out.println("1分钟转换为秒数 " + TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES));

      // TimeUnit.DAYS 日的工具类
      // TimeUnit.HOURS 时的工具类
      // TimeUnit.MINUTES 分的工具类
      // TimeUnit.SECONDS 秒的工具类
      // TimeUnit.MILLISECONDS 毫秒的工具类


      TimeUnit timeUnit = TimeUnit.MINUTES;// TimeUnit.SECONDS/TimeUnit.MINUTES 这里替换任何枚举值都能正确转换
      System.out.println(timeUnit.name());
      // 把 timeUnit 值n 转换为对应的 ToXXX的值
      System.out.println(timeUnit.toDays(1));
      System.out.println(timeUnit.toHours(1));
      System.out.println(timeUnit.toMinutes(1));
      System.out.println(timeUnit.toMicros(1));
      System.out.println(timeUnit.toMillis(1));
      System.out.println(timeUnit.toNanos(1));
      System.out.println(timeUnit.toSeconds(1));
      // 把 convert(60, TimeUnit.SECONDS) 的值,转换为timeUnit的值
      System.out.println("1天有 " + (timeUnit.convert(1, TimeUnit.DAYS)) + timeUnit.name());
      System.out.println("12小时有 " + (timeUnit.convert(12, TimeUnit.HOURS)) + timeUnit.name());
      System.out.println("1分钟有 " + (timeUnit.convert(1, TimeUnit.MINUTES)) + timeUnit.name());
      System.out.println("60秒 有 " + (timeUnit.convert(60, TimeUnit.SECONDS)) + timeUnit.name());
      System.out.println("-------------------");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }


  }
}
