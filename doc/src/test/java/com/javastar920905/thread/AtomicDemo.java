package com.javastar920905.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ouzhx on 2018/3/23.
 * 
 *         原子类也无法保证线程安全
 * 
 *         原子操作表示一段操作是不可分割的，没有其他线程能够中断或检查正在原子操作中的变量。 一个原子类就是一个原子操作可用的类，它可以在没有锁的情况下保证线程安全。
 */
public class AtomicDemo {
  // 单个操作是原子的,但是多个并列逻辑操作还是不具备线程安全(cpu调用无序性)
  public static AtomicInteger aiRef = new AtomicInteger();

  public void addNumUnSafe() {
    // aiRef.addAndGet(100) aiRef.getAndAdd(1); 这两个操作在cpu执行过程中是可分割的
    System.out.println(Thread.currentThread().getName() + "加了100之后的结果：" + aiRef.addAndGet(100));

    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    aiRef.getAndAdd(1);
  }

  public void addNumSafe() {
    // 保证多个操作的原子性
    synchronized (aiRef) {
      System.out.println(Thread.currentThread().getName() + "加了100之后的结果：" + aiRef.addAndGet(100));
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      aiRef.getAndAdd(1);
    }
  }

}
