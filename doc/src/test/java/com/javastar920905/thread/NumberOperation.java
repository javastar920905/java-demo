package com.javastar920905.thread;

/**
 * 数值操作(提供了线程安装的和不安全的方法)
 * 
 * @author ouzhx on 2018/3/21.
 */
public class NumberOperation {
  private int num = 0;

  public void addNumUnSafe(String userName) {
    try {
      if ("a".equals(userName)) {
        num = 100;
        System.out.println("a set over!");
        Thread.sleep(2000);
      } else {
        num = 200;
        System.out.println("b set over!");
      }
      System.out.println(userName + " num = " + num);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public synchronized void addNumSafe(String userName) {
    addNumUnSafe(userName);
  }


}
