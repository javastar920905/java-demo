package com.javastar920905.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author ouzhx on 2018/3/21.
 */
public class JoinThread implements Runnable {
  @Override
  public void run() {
    // 线程随机沉睡几秒
    try {
      int secondValue = (int) (Math.random() * 10);
      System.out.println(secondValue);

      TimeUnit.SECONDS.sleep(secondValue);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
