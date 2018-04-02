package com.javastar920905.concurent.threadentity;

import java.util.concurrent.BlockingQueue;

/**
 * @author ouzhx on 2018/4/2.
 */
public class MyConsumer implements Runnable {
  protected BlockingQueue queue;

  public MyConsumer(BlockingQueue queue) {
    this.queue = queue;
  }

  public void run() {
    try {
      System.out.println("正在从队列消费:" + queue.take());
      System.out.println("正在从队列消费:" + queue.take());
      System.out.println("正在从队列消费:" + queue.take());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
