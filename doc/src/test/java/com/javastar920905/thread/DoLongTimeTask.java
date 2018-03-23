package com.javastar920905.thread;

/**
 * @author ouzhx on 2018/3/23.
 */
public class DoLongTimeTask {
  // 全局对象
  private Integer j = 0;

  public void doLongTimeTask() {
    for (int i = 0; i < 100; i++) {
      System.out.println("doLongTimeTask()未被同步的代码块 threadName = " + Thread.currentThread().getName()
          + ", i = " + (i + 1));
    }
    System.out.println();

    // 同一时间只有一个线程可以执行synchronized(this)同步代码块中的代码
    synchronized (this) {
      for (int i = 0; i < 100; i++) {
        System.out.println("doLongTimeTask() synchronized代码块 threadName = " + ""
            + Thread.currentThread().getName() + ", i = " + (i + 1));
      }
    }

    // 除了使用synchronized(this)的格式来同步代码块，其实Java还支持对"任意对象"作为对象监视器来实现同步的功能。

    // TODO 这里j必须是全局对象,否则就不是同步调用了
    // 锁非this对象具有一定的优点：如果在一个类中有很多synchronized方法，这时虽然能实现同步，但会受到阻塞，从而影响效率。
    // 但如果同步代码块锁的是非this对象，则synchronized(非this对象x)代码块中的程序与同步方法是异步的，不与其他锁this同步方法争抢this锁，大大提高了运行效率。
    synchronized (j) {
      for (; j < 100; j++) {
        System.out.println("doLongTimeTask() synchronized代码块 threadName = " + ""
            + Thread.currentThread().getName() + ", i = " + (j + 1));
      }
    }
  }
}
