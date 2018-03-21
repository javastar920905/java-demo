package com.javastar920905.thread;

/**
 * 自定义线程测试
 * 
 * @author ouzhx on 2018/3/21.
 */
public class CustomerThread extends Thread {
  @Override
  public void run() {
    for (int i = 0; i < 20; i++) {
      System.out.println(Thread.currentThread().getName() + "在运行!");
    }
  }
}
