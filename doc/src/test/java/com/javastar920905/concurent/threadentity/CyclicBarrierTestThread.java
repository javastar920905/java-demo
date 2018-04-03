package com.javastar920905.concurent.threadentity;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author ouzhx on 2018/4/2.
 */
public class CyclicBarrierTestThread implements Runnable {
  CyclicBarrier cyclicBarrier;

  public CyclicBarrierTestThread(CyclicBarrier cyclicBarrier) {
    this.cyclicBarrier = cyclicBarrier;
  }

  @Override
  public void run() {
    try {
      // 等待其他线程,知道达到cyclicBarrier.getNumberWaiting() 都准备就绪,所有线程可以同时执行
      // 让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门
      System.out
          .println(Thread.currentThread().getName() + " 线程已经就绪" + cyclicBarrier.getNumberWaiting());
      cyclicBarrier.await();
      System.out
          .println(Thread.currentThread().getName() + " 线程开始执行" + cyclicBarrier.getNumberWaiting());

    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (BrokenBarrierException e) {
      e.printStackTrace();
    }
  }
}
