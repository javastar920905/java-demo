package com.javastar920905.thread;

/**
 * @author ouzhx on 2018/3/21. 理解Thread类中的Thread.currentThread()
 */
public class CurrentThreadDemo extends Thread {
  static {
    System.out.println("运行当前静态块的线程名：" + Thread.currentThread().getName());
  }

  public CurrentThreadDemo() {
    System.out.println("运行当前构造方法的线程名：" + Thread.currentThread().getName());
    System.out.println("this.getName()----->" + this.getName());
  }

  @Override
  public void run() {
    System.out.println("运行当前run()方法的线程名：" + Thread.currentThread().getName());
    System.out.println("this.getName()----->" + this.getName());
  }
}
