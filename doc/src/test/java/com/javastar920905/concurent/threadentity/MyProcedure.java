package com.javastar920905.concurent.threadentity;

import java.util.concurrent.BlockingQueue;

/**
 * @author ouzhx on 2018/4/2.
 */
public class MyProcedure implements Runnable {
  protected BlockingQueue blockingQueue;

  public MyProcedure(BlockingQueue blockingQueue) {
    this.blockingQueue = blockingQueue;
  }

  @Override
  public void run() {
    try {
      blockingQueue.put("1");
      Thread.sleep(1000);
      System.out.println(Thread.currentThread().getName() + "  I am sleeping");
      blockingQueue.put("2");
      Thread.sleep(1000);
      System.out.println(Thread.currentThread().getName() + "  I am sleeping");
      blockingQueue.put("3");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
