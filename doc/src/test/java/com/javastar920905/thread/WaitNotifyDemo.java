package com.javastar920905.thread;

/**
 * @author ouzhx on 2018/3/23.
 * 
 *         http://www.cnblogs.com/xrq730/p/4853932.html
 *
 *         在Object对象中有三个方法wait()、notify()、notifyAll()，既然是Object中的方法，那每个对象自然都是有的
 * 
 *         wait()的作用是使当前执行代码的线程进行等待，将当前线程置入"预执行队列"中，并且wait()所在的代码处停止执行，直到接到通知或被中断。
 *         在调用wait()之前，线程必须获得该对象的锁，因此只能在同步方法/同步代码块中调用wait()方法。
 * 
 *         2、notify()
 * 
 *         notify()的作用是，如果有多个线程等待，那么线程规划器随机挑选出一个wait的线程，对其发出通知notify()，并使它等待获取该对象的对象锁。
 *         注意"等待获取该对象的对象锁"，这意味着，即使收到了通知，wait的线程也不会马上获取对象锁，必须等待notify()方法的线程释放锁才可以。
 *         和wait()一样，notify()也要在同步方法/同步代码块中调用。
 * 
 *         总结起来就是，wait()使线程停止运行，notify()使停止运行的线程继续运行。
 */
public class WaitNotifyDemo extends Thread {
  public static void main(String[] args) {
    Object lock = new Object();
    WaitThread waitThread = new WaitThread(lock);
    // 1 当前线程获取到lock对象锁,wait()使得线程释放对象锁
    waitThread.start();
    try {
      // Thread.sleep(3000)也是为了保证waitThread先运行，这样才能看到wait()和notify()的效果：
      // 2 mian 沉睡3秒
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    // 3 notify线程得到机会,获取对象锁,
    // 调用notify()"随机唤醒一个等待lock对象锁的线程"(直到当前notify线程执行结束,被唤醒的线程才有机会被执行wait()后面的代码)
    NotifyThread notifyThread = new NotifyThread(lock);
    notifyThread.start();

    // 如果wait()方法和notify()/notifyAll()方法不在同步方法/同步代码块中被调用，那么虚拟机会抛出java.lang.IllegalMonitorStateException，注意一下
  }


  public static class WaitThread extends Thread {
    private Object lock;

    public WaitThread(Object lock) {
      this.lock = lock;
    }

    @Override
    public void run() {
      try {
        synchronized (lock) {
          System.out.println("开始------wait time = " + System.currentTimeMillis());
          // 在调用wait()之前，线程必须获得该对象的锁，因此只能在同步方法/同步代码块中调用wait()方法。
          // 使当前执行代码的线程进行等待，将当前线程置入"预执行队列"中，并且wait()所在的代码处停止执行，直到接到通知或被中断
          // wait()方法 使该线程的释放共享资源lock的锁，然后从运行状态退出，进入等待队列，直到再次被唤醒。
          // notify()方法可以随机唤醒等待队列中等待同一共享资源的一个线程，并使得该线程退出等待状态，进入可运行状态
          lock.wait();
          // notifyAll()方法可以使所有正在等待队列中等待同一共享资源的全部线程从等待状态退出，进入可运行状态

          System.out.println("结束------wait time = " + System.currentTimeMillis());
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static class NotifyThread extends Thread {
    private Object lock;

    public NotifyThread(Object lock) {
      this.lock = lock;
    }

    @Override
    public void run() {
      synchronized (lock) {
        System.out.println("开始------notify time = " + System.currentTimeMillis());
        // notify()也要在同步方法/同步代码块中调用。
        // notify()的作用是，如果有多个线程等待，那么线程规划器随机挑选出一个wait的线程，对其发出通知notify()，并使它等待获取该对象的对象锁。
        // 注意"等待获取该对象的对象锁"，这意味着，即使收到了通知，wait的线程也不会马上获取对象锁，
        // 必须等待notify()方法的线程释放锁才可以。
        // notify()方法可以随机唤醒等待队列中等待同一共享资源的一个线程，并使得该线程退出等待状态，进入可运行状态
        // todo notify()方法不释放锁
        lock.notify();
        System.out.println("结束------notify time = " + System.currentTimeMillis());
      }
    }
  }


}
