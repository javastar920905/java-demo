package com.javastar920905.concurent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.junit.Test;

import com.javastar920905.concurent.threadentity.MyConsumer;
import com.javastar920905.concurent.threadentity.MyProcedure;

/**
 * @author ouzhx on 2018/4/2.
 * 
 *         BlockingQueue 接口 表示一个线程安放入和提取实例的队列
 * 
 *         相关实现:
 * 
 *         ArrayBlockingQueue(有界队列,一旦初始化，大小就无法修改.内部以 FIFO(先进先出)的顺序对元素进行存储)
 * 
 *          DelayQueue (对元素进行持有直到一个特定的延迟到期。注入其中的元素必须实现 java.util.concurrent.Delayed 接口,
 *         会在每个元素的getDelay() 方法返回的值的时间段之后才释放掉该元素。如果返回的是 0 或者负值，延迟将被认为过期， 该元素将会在 DelayQueue 的下一次
 *         take被调用的时候被释放掉。)
 * 
 *          LinkedBlockingQueue (内部以一个链式结构(链接节点)对其元素进行存储。如果需要的话， 这一链式结构可以选择一个上限。如果没有定义上限，将使用
 *         Integer.MAX_VALUE 作为 上限。内部以 FIFO(先进先出)的顺序对元素进行存储。)
 *
 *          PriorityBlockingQueue (是一个无界的并发队列。它使用了和类 java.util.PriorityQueue 一样的 排序规则。你无法向这个队列中插入
 *         null 值。 所有插入到 PriorityBlockingQueue 的元素必须实现 java.lang.Comparable 接口。因此该队 列中元素的排序就取决于你自己的
 *         Comparable 实现。)
 * 
 *          SynchronousQueue (是一个特殊的队列，它的内部同时只能够容纳单个元素。如果该队列已
 *         有一元素的话，试图向队列中插入一个新元素的线程将会阻塞，直到另一个线程将该元素从 队列中抽走。同样，如果该队列为空，试图向队列中抽取一个元素的线程将会阻塞，直到另
 *         一个线程向队列中插入了一条新的元素。)
 * 
 *         https://www.cnblogs.com/KingIceMou/p/8075343.html
 *         先进先出（FIFO）：先插入的队列的元素也最先出队列，类似于排队的功能。从某种程度上来说这种队列也体现了一种公平性。
 *         后进先出（LIFO）：后插入队列的元素最先出队列，这种队列优先处理最近发生的事件。
 */
public class BlockingQueueDemo {

  @Test
  public void arrayblockingQueue() {
    // 队列使用泛型,是对String 元素的放入和提取
    BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(1024);

    MyProcedure producer = new MyProcedure(blockingQueue);
    MyConsumer consumer = new MyConsumer(blockingQueue);
    Thread t1 = new Thread(producer);
    Thread t2 = new Thread(consumer);


    try {
      t1.start();
      t2.start();

      t1.join();
      t2.join();


    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }
}
