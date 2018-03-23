package com.javastar920905.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ouzhx on 2018/3/21.
 */
public class ThreadLocalThread extends Thread {
  private static AtomicInteger ai = new AtomicInteger();

  public ThreadLocalThread(String name) {
    super(name);
  }

  @Override
  public void run() {
    try {
      // 线程往ThreadLocal里面塞值
      for (int i = 0; i < 3; i++) {
        // ThreadLocal不是集合，它不存储任何内容，真正存储数据的集合在Thread中。ThreadLocal只是一个工具，一个往各个线程的ThreadLocal.ThreadLocalMap中table的某一位置set一个值的工具而已
        ThreadLocalTools.threadLocal.set(ai.addAndGet(1) + "");
        System.out.println(this.getName() + " get value--->" + ThreadLocalTools.threadLocal.get());
        Thread.sleep(200);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
