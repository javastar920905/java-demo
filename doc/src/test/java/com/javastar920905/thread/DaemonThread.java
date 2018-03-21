package com.javastar920905.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author ouzhx on 2018/3/21.
 */
public class DaemonThread implements Runnable {
  private int i = 0;

  @Override
  public void run() {
    try {
      while (true) {
        // 每隔1秒打印一次i的值
        i++;
        System.out.println("i = " + i);
        TimeUnit.SECONDS.sleep(1);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
