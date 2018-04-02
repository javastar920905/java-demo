package com.javastar920905.concurent;

/**
 * @author ouzhx on 2018/4/2.
 * 
 *         阻塞双端队列 BlockingDeque (deque(双端队列) 是 "Double Ended Queue" 的缩写。)
 * 
 *         是一个双端队列，在不能够插入元素时，它将阻塞住试图插入元素的线程； 在不能够抽取元素时，它将阻塞住试图抽取的线程。
 *
 *         因此，双端队列是一个你可以从任意一 端插入或者抽取元素的队列
 * 
 *         在线程既是一个队列的生产者又是这个队列的消费者的时候可以使用到 BlockingDeque。
 * 
 *         BlockingDeque 接口继承自 BlockingQueue 接口。这就意味着你可以像使用一个 BlockingQueue 那样使用 BlockingDeque。
 * 
 *         实现类: LinkedBlockingDeque
 */
public class BlockingDequeDemo {
}
