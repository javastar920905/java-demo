package com.javastar920905.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author ouzhx on 2018/4/2.
 * 
 *         Callable和Future，它俩很有意思的，一个产生结果，一个拿到结果。
 * 
 *         Callable接口类似于Runnable，从名字就可以看出来了，但是Runnable不会返回结果，并且无法抛出返回结果的异常，
 *         而Callable功能更强大一些，被线程执行后，可以返回值，这个返回值可以被Future拿到，也就是说，Future可以拿到异步执行任务的返回值
 */
public class CallableDemo {

  /**
   * 使用futureTask 获取callable返回结果
   */
  @Test
  public void testCallableWithFutureTask() {
    Callable<Integer> callable = new CallableAndFuture();

    // future 对象获取线程执行结果
    FutureTask<Integer> futureTask = new FutureTask<>(callable);
    new Thread(futureTask).start();

    try {
      /*
       * FutureTask实现了两个接口，Runnable和Future，所以它既可以作为Runnable被线程执行，又可以作为Future得到Callable的返回值，
       * 那么这个组合的使用有什么好处呢？假设有一个很耗时的返回值需要计算，并且这个返回值不是立刻需要的话，那么就可以使用这个组合，
       * 用另一个线程去计算返回值，而当前线程在使用这个返回值之前可以做其它的操作，等到需要这个返回值时，再通过Future得到
       */
      System.out.println(futureTask.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

  /**
   * 线程池 方式执行并返回callable 结果(好处,代码简化,Executor使我们无需显示的去管理线程的生命周期)
   */
  @Test
  public void testCallableWithThreadPool() {
    ExecutorService threadPool = Executors.newSingleThreadExecutor();
    Future<Integer> future = threadPool.submit(new CallableAndFuture());

    try {
      System.out.println(future.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

  /**
   * 执行多个带返回值的任务，并取得多个返回值
   */
  @Test
  public void testCallableWithCompletionService() {
    ExecutorService threadPool = Executors.newCachedThreadPool();


    int threadNums = 5;
    try {
      // 方式1 使用线程池的invokeAll()提交所有线程,并直接返回线程结果列表
      Set<Callable<Integer>> callables = new HashSet<>(threadNums);
      for (int i = 0; i < threadNums; i++) {
        callables.add(new CallableAndFuture());
      }
      List<Future<Integer>> futures = threadPool.invokeAll(callables);
      for (Future<Integer> future : futures) {
        System.out.println(future.get());// 会阻塞
      }

      System.out.println("==========>华丽分割线");
      // 方式2 使用CompletionService包装线程池 (会保证先执行完的结果,从队列中获取出来,减少future.get()等待时间)
      CompletionService<Integer> cs = new ExecutorCompletionService<>(threadPool);
      for (int i = 0; i < threadNums; i++) {
        cs.submit(new CallableAndFuture());
      }

      for (int i = 0; i < threadNums; i++) {
        System.out.println(cs.take().get());
      }

    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

  }

  public class CallableAndFuture implements Callable<Integer> {
    /**
     * Computes a result, or throws an exception if unable to do so.
     * 
     * @return 返回计算结果
     * @throws Exception 如果无法返回结果的时候就抛出异常
     */
    @Override
    public Integer call() throws Exception {
      return new Random().nextInt(100);
    }
  }
}
