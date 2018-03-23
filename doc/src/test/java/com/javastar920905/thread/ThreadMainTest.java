package com.javastar920905.thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author ouzhx on 2018/3/21.
 * 
 *         //1 线程状态 虚拟机中的线程状态有六种，定义在Thread.State中：
 * 
 *         1、新建状态NEW new了但是没有启动的线程的状态。比如"Thread t = new Thread()"，t就是一个处于NEW状态的线程
 * 
 *         2、可运行状态RUNNABLE
 *         new出来线程，调用start()方法即处于RUNNABLE状态了。处于RUNNABLE状态的线程可能正在Java虚拟机中运行，也可能正在等待处理器的资源，因为一个线程必须获得CPU的资源后，才可以运行其run()方法中的内容，否则排队等待
 * 
 *         TODO 待理解 3、阻塞BLOCKED 如果某一线程正在等待监视器锁，以便进入一个同步的块/方法，那么这个线程的状态就是阻塞BLOCKED
 * 
 *         4、等待WAITING
 *         某一线程因为调用不带超时的Object的wait()方法、不带超时的Thread的join()方法、LockSupport的park()方法，就会处于等待WAITING状态
 * 
 *         5、超时等待TIMED_WAITING
 *         某一线程因为调用带有指定正等待时间的Object的wait()方法、Thread的join()方法、Thread的sleep()方法、LockSupport的parkNanos()方法、LockSupport的parkUntil()方法，就会处于超时等待TIMED_WAITING状态
 * 
 *         6、终止状态TERMINATED 线程调用终止或者run()方法执行结束后，线程即处于终止状态。处于终止状态的线程不具备继续运行的能力
 * 
 *
 *
 *         //2 Thread类中的方法调用方式：
 *         学习Thread类中的方法是学习多线程的第一步。在学习多线程之前特别提出一点，调用Thread中的方法的时候，在线程类中，有两种方式，一定要理解这两种方式的区别：
 *
 *         todo"this.XXX()"和"Thread.currentThread().XXX()"的区别，这个就是最好的例子。必须要清楚的一点就是：当前执行的Thread未必就是Thread本身.http://www.cnblogs.com/xrq730/p/4851344.html
 *         1、this.XXX() 这种调用方式表示的线程是线程实例本身
 * 
 *         2、Thread.currentThread.XXX()或Thread.XXX()
 *         上面两种写法是一样的意思。这种调用方式表示的线程是正在执行Thread.currentThread.XXX()所在代码块的线程
 * 
 *         当然，这么说，肯定有人不理解两者之间的差别。没有关系，之后会讲清楚，尤其是在讲Thread构造函数这块。讲解后，再回过头来看上面2点，会加深理解。
 * 
 * 
 *         看到在start()之前，线程的isAlive是false，start()之后就是true了。main函数中加上Thread.sleep(100)的原因是为了确保Thread06的run()方法中的代码执行完，否则有可能end这里打印出来的是true，有兴趣可以自己试验一下。
 *
 * 
 *         //Thread类中的实例方法 http://www.cnblogs.com/xrq730/p/4851233.html 1、start() 1
 *         start()方法的作用讲得直白点就是通知"线程规划器"，此线程可以运行了，正在等待CPU调用线程对象得run()方法，产生一个异步执行的效果。 2、run() 2
 *         线程开始执行，虚拟机调用的是线程run()方法中的内容
 *
 *         3、isAlive() 测试线程是否处于活动状态，只要线程启动且没有终止，方法返回的就是true。看一下例子：
 * 
 *         4、getId()
 *         这个方法比较简单，就不写例子了。在一个Java应用中，有一个long型的全局唯一的线程ID生成器threadSeqNumber，每new出来一个线程都会把这个自增一次，并赋予线程的tid属性，这个是Thread自己做的，用户无法执行一个线程的Id。
 * 
 *         5、getName()
 *         这个方法也比较简单，也不写例子了。我们new一个线程的时候，可以指定该线程的名字，也可以不指定。如果指定，那么线程的名字就是我们自己指定的，getName()返回的也是开发者指定的线程的名字；如果不指定，那么Thread中有一个int型全局唯一的线程初始号生成器threadInitNum，Java先把threadInitNum自增，然后以"Thread-threadInitNum"的方式来命名新生成的线程
 * 
 *         6、getPriority()和setPriority(int newPriority)
 *         这两个方法用于获取和设置线程的优先级，优先级高的CPU得到的CPU资源比较多，设置优先级有助于帮"线程规划器"确定下一次选择哪一个线程优先执行。
 *         换句话说，两个在等待CPU的线程，优先级高的线程越容易被CU选择执行。(线程默认优先级为5，如果不手动指定，那么线程优先级具有继承性，比如线程A启动线程B，那么线程B的优先级和线程A的优先级相同。)
 * 
 *         7、isDaeMon、setDaemon(boolean on)
 *         讲解两个方法前，首先要知道理解一个概念。Java中有两种线程，一种是用户线程，一种是守护线程。守护线程是一种特殊的线程，它的作用是为其他线程的运行提供便利的服务，
 *         最典型的应用便是GC线程。如果进程中不存在非守护线程了，那么守护线程自动销毁，因为没有存在的必要，为别人服务，结果服务的对象都没了，当然就销毁了。
 *         setDaemon(true)必须在线程start()之前
 * 
 *         8、interrupt() 这是一个有点误导性的名字，实际上Thread类的interrupt()方法无法中断线程。
 *         interrupt()方法的作用实际上是：在线程受到阻塞时抛出一个中断信号，这样线程就得以退出阻塞状态。换句话说，没有被阻塞的线程，调用interrupt()方法是不起作用的。
 *         9 isInterrupted() 测试线程是否已经中断，但不清除状态标识。
 * 
 *         10、join()
 *         讲解join()方法之前请确保对于http://www.cnblogs.com/xrq730/p/4853932.html一文，即wait()/notify()/notifyAll()机制已熟练掌握。
 *         join()方法的作用是等待线程销毁。join()方法反应的是一个很现实的问题，
 *         比如main线程的执行时间是1s，子线程的执行时间是10s，但是主线程依赖子线程执行完的结果，这时怎么办？
 *         可以像生产者/消费者模型一样，搞一个缓冲区，子线程执行完把数据放在缓冲区中，
 *         通知main线程，main线程去拿，这样就不会浪费main线程的时间了。另外一种方法，就是join()了。
 *
 *
 *
 *         //http://www.cnblogs.com/xrq730/p/4851344.html
 *         线程类的构造方法、静态块是被main线程调用的，而线程类的run()方法才是应用线程自己调用的。
 * 
 *         2、sleep(long millis)
 *         sleep(longmillis)方法的作用是在指定的毫秒内让当前"正在执行的线程"休眠（暂停执行）。这个"正在执行的线程"是关键，指的是Thread.currentThread()返回的线程。
 *
 *         //11 对类的静态方法加同步synchronized
 *         假如一个StaticMethod类中有一个静态同步方法syncA()，new出了两个类的实例sB和实例sC，线程t1持有实例sB，线程t2持有实例sC，
 *         只要线程t1调用了syncA()方法，那么线程t2调用syncA方法必须等待线程t1执行完syncA方法，尽管两个线程持有的是不同的对象。
 **/
public class ThreadMainTest {

  /**
   * CurrentThreadDemo 线程类的构造方法、静态块是被main线程调用的，而线程类的run()方法才是应用线程自己调用的。
   * 
   * "this.XXX()"和"Thread.currentThread().XXX()"的区别，这个就是最好的例子。必须要清楚的一点就是：当前执行的Thread未必就是Thread本身。
   */
  @Test
  public void testCurrentThread() {
    CurrentThreadDemo thread = new CurrentThreadDemo();
    thread.start();
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * sleep(long millis)方法的作用是在指定的毫秒内让当前"正在执行的线程"休眠（暂停执行）。
   * 
   * 这个"正在执行的线程"是关键，指的是Thread.currentThread()返回的线程。
   * 
   * TODO sleep代码上下文如果被加锁了，锁依然在，但是CPU资源会让出给其他线程
   */
  @Test
  public void testSleep() {

  }

  /**
   * 继承thread 方式实现线程
   * 
   * 有可能有些人看不到这么明显的效果，这也很正常。
   * 
   * 所谓的多线程，指的是两个线程的代码可以同时运行，而不必一个线程需要等待另一个线程内的代码执行完才可以运行。
   * 
   * 对于单核CPU来说，是无法做到真正的多线程的，每个时间点上，CPU都会执行特定的代码，由于CPU执行代码时间很快，所以两个线程的代码交替执行看起来像是同时执行的一样。
   * 
   * 那具体执行某段代码多少时间，就和分时机制系统有关了。
   * 
   * 分时系统把CPU时间划分为多个时间片，操作系统以时间片为单位片为单位各个线程的代码，越好的CPU分出的时间片越小。所以看不到明显效果也很正常，
   * 
   * 一个线程打印5句话本来就很快，可能在分出的时间片内就执行完成了。所以，最简单的解决办法就是把for循环的值调大一点就可以了（也可以在for循环里加Thread.sleep方法，这个之后再说）。
   */
  @Test
  public void testNormalThread() {
    CustomerThread thread = new CustomerThread();
    thread.start();


    for (int i = 0; i < 20; i++) {
      System.out.println(Thread.currentThread().getName() + "在运行！");
    }
  }

  /**
   * 实现runnable接口实现线程 线程执行无序性测试
   * 
   * public class Thread implements Runnable {}
   * 其实Thread类也是实现的Runnable接口。两种实现方式对比的关键就在于extends和implements的对比，当然是后者好。
   *
   * 因为第一，继承只能单继承，实现可以多实现；
   *
   * 第二，实现的方式对比继承的方式，也有利于减小程序之间的耦合。
   *
   * 因此，多线程的实现几乎都是使用的Runnable接口的方式。不过，为了简单，就用继承Thread类的方式了。
   */
  @Test
  public void testRunnableThread() {
    CustomerRunnable runnable = new CustomerRunnable();
    Thread wapper = new Thread(runnable);// 线程处于NEW 状态
    System.out.println("testRunnableThread state1" + wapper.getState());
    /**
     * start()方法的作用讲得直白点就是通知"线程规划器"，此线程可以运行了，正在"等待CPU调用线程对象的run()方法"，产生一个异步执行的效果。
     * (处于RUNNABLE状态的线程可能正在Java虚拟机中运行，也可能正在等待处理器的资源，因为一个线程必须获得CPU的资源后，才可以运行其run()方法中的内容，否则排队等待)
     * 结果表明了：CPU执行哪个线程的代码具有不确定性。 // 调用start()方法的顺序不代表线程启动的顺序，线程启动顺序具有不确定性。
     * Thread实例run()方法里面的内容是没有任何异步效果的，全部被main函数执行。换句话说，只有run()而不调用start()启动线程是没有任何意义的。
     */
    wapper.start();// RUNNABLE 状态
    System.out.println("testRunnableThread state2" + wapper.getState());

    // 当前main线程的加载器(好像所有都是这个) sun.misc.Launcher$AppClassLoader@18b4aac2
    for (int i = 0; i < 20; i++) {
      System.out.println(Thread.currentThread().getName() + "在运行！");
    }
  }


  /**
   * daemon守护进程测试(main线程结束后,守护线程也结束)
   * 
   * Java中有两种线程，一种是用户线程，一种是守护线程。守护线程是一种特殊的线程，它的作用是为其他线程的运行提供便利的服务，
   * 最典型的应用便是GC线程。如果进程中不存在非守护线程了，那么守护线程自动销毁，因为没有存在的必要，为别人服务，结果服务的对象都没了，当然就销毁了。
   */
  @Test
  public void testDaemon() {
    System.out.println("5s后我离开Daemon thread对象再也不打印了！");
    DaemonThread daemonThread = new DaemonThread();
    Thread thread = new Thread(daemonThread);
    // setDaemon(true)必须在线程start()之前
    thread.setDaemon(true);
    thread.start();

    try {
      TimeUnit.SECONDS.sleep(5);
      System.out.println("我停止了！");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  /**
   * 线程join 测试 (子线程结束后,main才开始执行)
   *
   * join()方法会使调用join()方法的线程（也就是thread线程） 所在的线程（也就是main线程）无限阻塞，直到调用join()方法的线程销毁为止
   *
   * TODO http://www.cnblogs.com/xrq730/p/4851233.html
   *
   * join()方法的一个重点是要区分出和sleep()方法的区别。join(2000)也是可以的，表示调用join()方法所在的线程最多等待2000ms，两者的区别在于：
   * sleep(2000)不释放锁，"join(2000)释放锁"，因为join()方法内部使用的是wait()，因此会释放锁。看一下join(2000)的源码就知道了，join()其实和join(2000)一样，无非是join(0)而已：
   */
  @Test
  public void testJoin() {
    JoinThread runable = new JoinThread();
    Thread thread = new Thread(runable);
    thread.start();
    try {
      thread.join();

      System.out.println("我想当thread对象执行完毕之后我再执行，我做到了");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * 线程不安全测试
   */
  @Test
  public void testThreadUnSafe() {
    NumberOperation numberOperation = new NumberOperation();

    // 正常情况应该是先运行 a set over! a num =100,然后才执行b的
    new Thread(() -> numberOperation.addNumUnSafe("a")).start();
    new Thread(() -> numberOperation.addNumUnSafe("b")).start();

    // 运行过程分析:1 线程a 运行设置num=100,打印a set over!,然后睡眠(模拟cpu调度)
    // 2 线程a睡觉的时候,线程b也运行了,num被改成了200,然后打印出"b set over!"，然后打印"b num = 200"
    // 3 线程a 睡觉回来,继续打印numberOperation的num,此时只能拿到线程b修改后的值了
    // 解决方式: 如果想确保线程a拿到自己操作后的值,就需要把 赋值和输出语句同步(synchronized)了

    try {
      // 确保子线程够时间执行结束
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * 线程安全测试
   * 
   * // 这里有一个重要的概念。 http://www.cnblogs.com/xrq730/p/4851350.html
   * 关键字synchronized取得的锁都是对象锁，而不是把一段代码或方法（函数）当作锁，哪个线程先执行带synchronized关键字的方法，哪个线程就持有"该方法所属对象(numberOperation)的锁"，
   * // 其他线程都只能呈等待状态。但是这有个前提：既然锁叫做对象锁，那么势必和对象相关，所以多个线程访问的必须是同一个对象。
   *
   * TODO 1、A线程持有Object对象的Lock锁，B线程可以以异步方式调用Object对象中的非synchronized类型的方法
   * 
   * 2、A线程持有Object对象的Lock锁，B线程如果在这时调用Object对象中的synchronized类型的方法则需要等待，也就是同步
   * 
   * synchronized锁重入 关键字synchronized拥有锁重入的功能。所谓锁重入的意思就是：当一个线程得到一个对象锁后，再次请求此对象锁时时可以再次得到该对象的锁的
   * 
   * 当一个线程执行的代码出现异常时，其所持有的锁会自动释放
   *
   * todo synchronized锁方法块 http://www.cnblogs.com/xrq730/p/4851530.html
   */
  @Test
  public void testThreadSafe() {
    NumberOperation numberOperation = new NumberOperation();


    // join 方式实现主线程等待子线程结束
    // Thread thread1 = new Thread(() -> numberOperation.addNumSafe("a"));
    // Thread thread2 = new Thread(() -> numberOperation.addNumSafe("b"));
    //
    // thread1.start();
    // thread2.start();
    // try {
    // thread1.join();
    // thread2.join();
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }


    // 正常情况应该是先运行 a set over! a num =100,然后才执行b的
    // CountDownLatch 方式实现主线程等待子线程结束(会死锁)
    CountDownLatch latch = new CountDownLatch(2);
    new Thread(() -> {
      numberOperation.addNumSafe("a");
      latch.countDown();
    }).start();
    new Thread(() -> {
      numberOperation.addNumSafe("b");
      latch.countDown();
    }).start();
    try {
      latch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }


  }

  /**
   * 多个对象多个锁
   *
   * 关键字synchronized取得的锁都是对象锁，而不是把一段代码或方法（函数）当作锁，哪个线程先执行带synchronized关键字的方法，哪个线程就持有该方法所属对象的锁，其他线程都只能呈等待状态。
   * TODO 但是这有个前提：既然锁叫做对象锁，那么势必和对象相关，所以多个线程访问的必须是同一个对象。
   * 
   * 如果多个线程访问的是多个对象，那么Java虚拟机就会创建多个锁，这里，创建了两个对象，就产生了2个锁。所以两个线程持有的是不同的锁，
   * 自然不会受到"等待释放锁"这一行为的制约，可以分别运行addNum(String userName)中的代码。
   *
   * TODO 锁重入(当一个线程得到一个对象锁后，再次请求此对象锁时时可以再次得到该对象的锁的(即可以再次调用当前对象任意同步代码块)。)
   * http://www.cnblogs.com/xrq730/p/4851350.html
   * 1、A线程持有Object对象的Lock锁，B线程可以以异步方式调用Object对象中的非synchronized类型的方法
   * 
   * 2、A线程持有Object对象的Lock锁，B线程如果在这时调用Object对象中的synchronized类型的方法则需要等待，也就是同步
   *
   * 当一个线程执行的代码出现异常时，其所持有的锁会自动释放。
   */
  @Test
  public void testThreadSafe2() {
    NumberOperation numberOperation1 = new NumberOperation();
    NumberOperation numberOperation2 = new NumberOperation();


    // join 方式实现主线程等待子线程结束
    Thread thread1 = new Thread(() -> numberOperation1.addNumSafe("a"));
    Thread thread2 = new Thread(() -> numberOperation2.addNumSafe("b"));

    thread1.start();
    thread2.start();
    try {
      thread1.join();
      thread2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * 测试线程运行异常时,会自动释放对象锁
   */
  @Test
  public void testReturnLockWhenException() {
    ThreadWhenExceptionDemo exceptionDemo = new ThreadWhenExceptionDemo();

    // 两个线程调用同一个对象的同步方法,当一个线程获取到对象锁时,其他线程需要等待
    // 而 numberOperation 中的方法是死循环,一般不出异常时,是永远会执行下去,不会释放锁的
    Thread thread1 = new Thread(() -> exceptionDemo.testMethod());
    Thread thread2 = new Thread(() -> exceptionDemo.testMethod());

    thread1.start();
    thread2.start();
    try {
      System.out.println("thread2 状态1" + thread2.getState().name());// 状态是 RUNNABLE
      TimeUnit.SECONDS.sleep(1);// 确保两个线程都处于runnable状态
      System.out.println("thread2 状态2" + thread2.getState().name());// 状态是 BLOCKED
      // join 方式实现主线程等待子线程结束
      thread1.join();
      thread2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * 测试对长时间执行的任务 代码块加锁 说不太清楚 可以先看源文档 http://www.cnblogs.com/xrq730/p/4851530.html
   */
  @Test
  public void testLongTimeCondition() {
    DoLongTimeTask longTimeTask = new DoLongTimeTask();

    Thread thread1 = new Thread(() -> longTimeTask.doLongTimeTask());
    Thread thread2 = new Thread(() -> longTimeTask.doLongTimeTask());

    thread1.start();
    thread2.start();

    try {

      // join 方式实现主线程等待子线程结束
      thread1.join();
      thread2.join();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * todo wait()和notify()/notifyAll() http://www.cnblogs.com/xrq730/p/4853932.html
   *
   * 在调用wait()之前，线程必须获得该对象的锁，因此只能在同步方法/同步代码块中调用wait()方法。 和wait()一样，
   * 
   * todo notify()也要在同步方法/同步代码块中调用。并使它等待获取该对象的对象锁。注意"等待获取该对象的对象锁"，
   * 这意味着，即使收到了通知，wait的线程也不会马上获取对象锁，必须等待notify()方法的线程释放锁才可以
   * 
   * 总结: wait()使线程停止运行， notify()使停止运行的线程继续运行。
   */
  @Test
  public void testNotify() {

  }


  /**
   * TODO synchronized锁定类方法、volatile关键字及其他 http://www.cnblogs.com/xrq730/p/4853578.html
   */
  @Test
  public void testVolatile() {
    VolatileThreadDemo.UnsafeThread unsafeThread = new VolatileThreadDemo.UnsafeThread();
    unsafeThread.start();

    try {
      TimeUnit.SECONDS.sleep(1);

      unsafeThread.setRunning(false);
      System.out.println(unsafeThread.getName() + " 已赋值为run=false");

      System.out.println(unsafeThread.getName() + " 状态=" + unsafeThread.getState().name());// 结果为RUNNABLE

      // 明明isRunning已经设置为false了， 线程还没停止呢？
      /**
       * TODO 理解 这就要从Java内存模型（JMM）说起，简单讲，虚拟机那块会详细讲的。 根据JMM，Java中有一块主内存，不同的线程有自己的工作内存，同一个变量值在主内存中有一份，
       * 如果线程用到了这个变量的话，自己的工作内存中有一份一模一样的拷贝,每次进入线程从主内存中拿到变量值，每次执行完线程将变量从工作内存同步回主内存中。
       * 
       * 出现打印结果现象的原因就是主内存和工作内存中数据的不同步造成的。因为执行run()方法的时候拿到一个主内存isRunning的拷贝，而设置isRunning是在main函数中做的.
       * 换句话说设置的isRunning设置的是主内存中的isRunning，更新了主内存的isRunning，线程工作内存中的isRunning没有更新，当然一直死循环了，因为对于线程来说，它的isRunning依然是true。
       * 
       * 解决这个问题很简单，给isRunning关键字加上volatile。
       * 加上了volatile的意思是，每次读取isRunning的值的时候，都先从主内存中把isRunning同步到线程的工作内存中，再当前时刻最新的isRunning。
       **/
      System.out.println("=============>分割线 \n\n");
      VolatileThreadDemo.VolatileThread volatileThread = new VolatileThreadDemo.VolatileThread();
      volatileThread.start();

      TimeUnit.SECONDS.sleep(1);

      volatileThread.setRunning(false);
      System.out.println(unsafeThread.getName() + " 已赋值为run=false");

      System.out.println(unsafeThread.getName() + " 状态=" + volatileThread.getState().name());// 结果为RUNNABLE,但是线程会马上被停止
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  /**
   * 线程安全类,多个逻辑操作也不保证线程安全(需要加锁)
   */
  @Test
  public void testAtomicUnsafe() {
    CountDownLatch latch = new CountDownLatch(5);
    AtomicDemo demo = new AtomicDemo();

    new Thread(() -> {
      demo.addNumUnSafe();
      latch.countDown();
    }).start();
    new Thread(() -> {
      demo.addNumUnSafe();
      latch.countDown();
    }).start();
    new Thread(() -> {
      demo.addNumUnSafe();
      latch.countDown();
    }).start();
    new Thread(() -> {
      demo.addNumUnSafe();
      latch.countDown();
    }).start();
    new Thread(() -> {
      demo.addNumUnSafe();
      latch.countDown();
    }).start();


    try {
      latch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(demo.aiRef.get());
  }

  @Test
  public void testAtomicSafe() {
    CountDownLatch latch = new CountDownLatch(5);

    AtomicDemo demo = new AtomicDemo();

    new Thread(() -> {
      demo.addNumSafe();
      latch.countDown();
    }).start();
    new Thread(() -> {
      demo.addNumSafe();
      latch.countDown();
    }).start();
    new Thread(() -> {
      demo.addNumSafe();
      latch.countDown();
    }).start();
    new Thread(() -> {
      demo.addNumSafe();
      latch.countDown();
    }).start();
    new Thread(() -> {
      demo.addNumSafe();
      latch.countDown();
    }).start();


    try {
      latch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(demo.aiRef.get());
  }

  /**
   * ThreadLocal的作用和使用 http://www.cnblogs.com/xrq730/p/4854820.html
   * 
   * ThreadLocal不是用来解决共享对象的多线程访问问题的，通过ThreadLocal的set()方法设置到线程的ThreadLocal.ThreadLocalMap里的是是线程自己要存储的对象，其他线程不需要去访问，也是访问不到的。
   * 各个线程中的ThreadLocal.ThreadLocalMap以及ThreadLocal.ThreadLocal中的值都是不同的对象。
   * 
   * 
   * ThreadLocal源码剖析 http://www.cnblogs.com/xrq730/p/4854813.html
   *
   * ThreadLocal其实比较简单，因为类里就三个public方法：set(T value)、get()、remove()
   *
   * 
   * 三个理论基础:
   * 
   * 1、每个线程都有一个自己的ThreadLocal.ThreadLocalMap对象
   * 
   * 2、每一个ThreadLocal对象都有一个循环计数器
   * 
   * 3、ThreadLocal.get()取值，就是根据当前的线程，获取线程中自己的ThreadLocal.ThreadLocalMap，
   * 然后在这个Map中根据第二点中循环计数器取得一个特定value值
   */
  @Test
  public void testThreadLocal() {
    ThreadLocalThread a = new ThreadLocalThread("ThreadA");
    ThreadLocalThread b = new ThreadLocalThread("ThreadB");
    ThreadLocalThread c = new ThreadLocalThread("ThreadC");
    a.start();
    b.start();
    c.start();

    try {
      a.join();
      b.join();
      c.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * TODO ReentrantLock的使用和Condition http://www.cnblogs.com/xrq730/p/4855155.html
   * 
   * 运行结果,没有任何的交替，数据都是分组打印的，说明了一个线程打印完毕之后下一个线程才可以获得锁去打印数据，这也证明了ReentrantLock具有加锁的功能
   * (线程进入方法是无序的,但是执行加锁的代码块是原子操作)
   * 
   * ReentrantLock持有的锁是需要手动去unlock()的
   *
   * TODO 公平锁与非公平锁 http://www.cnblogs.com/xrq730/p/4855538.html
   * 
   * 公平锁表示线程获取锁的顺序是按照线程排队的顺序来分配的，而非公平锁就是一种获取锁的抢占机制，是随机获得锁的，先来的未必就一定能先得到锁
   * 从这个角度讲，synchronized其实就是一种非公平锁。非公平锁的方式可能造成某些线程一直拿不到锁，自然是非公平的了
   */
  @Test
  public void testReentrantLock() {
    ReentrantLockDemo lockDemo = new ReentrantLockDemo();

    int threadNums = 100;
    CountDownLatch latch = new CountDownLatch(threadNums);
    for (int i = 0; i < threadNums; i++) {
      new Thread(() -> {
        lockDemo.testMethod();
        latch.countDown();
      }).start();
    }

    try {
      latch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }
}
