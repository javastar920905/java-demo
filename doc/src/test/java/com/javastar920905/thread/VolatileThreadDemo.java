package com.javastar920905.thread;

/**
 * @author ouzhx on 2018/3/23. 说明volatile 关键字的作用
 */
public class VolatileThreadDemo extends Thread {

  public static class VolatileThread extends Thread {
    /** 每次读取isRunning的值的时候，都先从主内存中把isRunning同步到线程的工作内存中，再当前时刻最新的isRunning **/
    private volatile boolean isRunning = true;

    public boolean isRunning() {
      return isRunning;
    }

    public void setRunning(boolean isRunning) {
      this.isRunning = isRunning;
    }

    @Override
    public void run() {
      System.out.println(Thread.currentThread().getName() + "进入run了");
      while (isRunning == true) {
      }
      System.out.println(Thread.currentThread().getName() + "线程被停止了");
    }
  }

  public static class UnsafeThread extends Thread {
    private boolean isRunning = true;

    public boolean isRunning() {
      return isRunning;
    }

    public void setRunning(boolean isRunning) {
      this.isRunning = isRunning;
    }

    @Override
    public void run() {
      System.out.println(Thread.currentThread().getName() + "进入run了");
      while (isRunning == true) {
      }
      System.out.println(Thread.currentThread().getName() + "线程被停止了");
    }
  }


}
