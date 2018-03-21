package com.javastar920905.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ouzhx on 2018/3/21.
 */
public class ReentrantLockDemo {
  private Lock lock = new ReentrantLock();

  public void testMethod() {
    try {
      lock.lock();
      // 每个进来的线程 打印两个数字
      for (int i = 0; i < 2; i++) {
        System.out.println("ThreadName = " + Thread.currentThread().getName() + ", i  = " + i);
      }
    } finally {
      lock.unlock();
    }
  }
}
