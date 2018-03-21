package com.javastar920905.thread;

/**
 * @author ouzhx on 2018/3/21.
 */
public class CustomerRunnable implements Runnable {
  @Override
  public void run() {
    System.out.println(Thread.currentThread().getState());
    for (int i = 0; i < 30; i++) {
      System.out.println(Thread.currentThread().getName() + "  实现runnable 接口 在运行!");
    }
  }
}
