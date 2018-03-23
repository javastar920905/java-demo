package com.javastar920905.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ouzhx on 2018/3/21.
 *
 *         参考文档 http://www.cnblogs.com/xrq730/p/4855155.html
 * 
 *         Condition
 * 
 *         synchronized与wait()和nitofy()/notifyAll()方法相结合可以实现等待/通知模型，ReentrantLock同样可以，但是需要借助Condition，且Condition有更好的灵活性，具体体现在：
 * 
 *         1、一个Lock里面可以创建多个Condition实例，实现多路通知
 * 
 *         2、notify()方法进行通知时，被通知的线程时Java虚拟机随机选择的，但是ReentrantLock结合Condition可以实现有选择性地通知，这是非常重要的
 * 
 */
public class ReentrantLockDemo {
  // ReentrantLock默认的是非公平锁 http://www.cnblogs.com/xrq730/p/4855538.html
  private Lock lock = new ReentrantLock();

  /**
   * tryLock()和tryLock(long timeout, TimeUnit unit)
   * 
   * tryLock()方法的作用是，在调用try()方法的时候，如果锁没有被另外一个线程持有，那么就返回true，否则返回false
   * 
   * tryLock(long timeout, TimeUnit unit)是tryLock()另一个重要的重载方法，表示如果在指定等待时间内获得了锁，则返回true，否则返回false
   * 
   * TODO 注意一下，tryLock()只探测锁是否，并没有lock()的功能，要获取锁，还得调用lock()方法，看一下tryLock()的例子：
   */
  public void testMethod() {
    try {
      lock.lock();
      // 每个进来的线程 打印两个数字
      for (int i = 0; i < 2; i++) {
        System.out.println("ThreadName = " + Thread.currentThread().getName() + ", i  = " + i);
      }
    } finally {
      // ReentrantLock持有的锁是需要手动去unlock()的
      lock.unlock();
    }
  }
}
