package com.javastar920905.concurent;

/**
 * @author ouzhx on 2018/4/2.
 * 
 *         "栅栏" 是一种同步机制，它能够对处理一些算法的线程实现同步。
 * 
 *         换句话讲，它就是一个所有线程必须等待的一个栅栏，直到所有线程都到达这里，然后 所有线程才可以继续做其他事情。
 * 
 *         通过调用 CyclicBarrier 对象的 await() 方法，两个线程可以实现互相等待。一旦 N 个线程 在等待 CyclicBarrier
 *         达成，所有线程将被释放掉去继续运行。
 * 
 *         创建一个 CyclicBarrier 的时候你需要定义有多少线程在被释放之前等待栅栏。 CyclicBarrier barrier = new CyclicBarrier(2);
 * 
 *         让一个线程等待一个 CyclicBarrier： barrier.await();
 *
 *         TODO 示例待完善
 */
public class CyclicBarrierDemo {
}
