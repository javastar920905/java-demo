package com.javastar920905.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author ouzhx on 2018/3/23.
 *
 *         异常时,自动释放对象锁, 当一个线程执行的代码出现异常时，其所持有的锁会自动释放。
 */
public class ThreadWhenExceptionDemo {
  public synchronized void testMethod() {
    // 模拟的是把一个long型数作为除数，从MAX_VALUE开始递减，直至减为0，从而产生ArithmeticException
    try {
      System.out.println(
          "成功调用对象 testMethod,当前线程已经获取锁, currentThread = " + Thread.currentThread().getName());

      TimeUnit.SECONDS.sleep(2);
      long l = Integer.MAX_VALUE;
      while (true) {
        long lo = 2 / l;
        l--;
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(Thread.currentThread().getName() + " 运行异常,释放对象锁");
    }
  }
}
