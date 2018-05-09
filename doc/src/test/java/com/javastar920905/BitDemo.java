package com.javastar920905;

import org.junit.Test;

/**
 * @author ouzhx on 2018/5/9.
 *
 *         参考文档 http://xxgblog.com/2013/09/15/java-bitmask/
 * 
 *         总结文档: https://mubu.com/doc/1PRqm1K64o
 *
 *         位运算
 */
public class BitDemo {
  /**
   * 两个数字 x,y 交换值
   */
  @Test
  public void exchangeTest() {
    int x = 5, y = 6;
    x ^= y;
    y ^= x;
    x ^= y;
    System.out.println("x=" + x + "  y=" + y);// 输出 x=6 y=5
  }

  /**
   * 判断是奇数还是偶数
   */
  @Test
  public void isOddTest() {
    int a = 4;
    // 奇数
    System.out.println((a & 1) == 1);// 输出 false
    // 偶数
    System.out.println((a & 1) == 0);// 输出 true
  }

}
