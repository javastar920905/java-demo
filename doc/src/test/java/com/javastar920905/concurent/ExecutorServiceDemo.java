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
