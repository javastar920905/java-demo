package com.javastar920905.concurent;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ouzhx on 2018/4/2.
 * 
 *         "执行器服务 ExecutorService" 接口表示一个异步执行机制,使我们能够在后台执行任务。
 * 
 *         存在于 java.util.concurrent 包 里的 ExecutorService 实现就是一个线程池实现。
 * 
 *         实现类: ThreadPoolExecutor  ScheduledThreadPoolExecutor
 * 
 *         线程池的作用: 线程池作用就是限制系统中执行线程的数量。 根据系统的环境情况，可以自动或手动设置线程数量，达到运行的最佳效果；
 *         少了浪费了系统资源，多了造成系统拥挤效率不高。用线程池控制线程数量，其他线程排队等候。一个任务执行完毕，再从队列的中取最前面的任务开始执行
 * 
 *         为什么要用线程池: 减少了创建和销毁线程的次数，每个工作线程都可以被重复利用，可执行多个任务
 */
public class ExecutorServiceDemo {

  @Test
  public void executorServiceTest() {
    // 创建线程池对象,容纳10个线程
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    // 一个线程将一个任务委派给一个 ExecutorService 去异步执行
    // execute(Runnable) 方法要求一个 java.lang.Runnable 对象，然后对它进行异步执行。
    executorService.execute(new Thread(() -> {
      // 线程池execute()会异步执行,不需要手动调用线程start()方法
      System.out.println("Asynchronous task");
    }));
    executorService.shutdown();

  }

  @Test
  public void otherTest() {
    // 获取线程返回值示例 http://note.youdao.com/noteshare?id=dccfbfcb5e7273681a693406094fdb68
  }
}
