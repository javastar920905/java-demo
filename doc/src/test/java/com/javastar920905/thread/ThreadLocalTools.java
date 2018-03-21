package com.javastar920905.thread;

/**
 * @author ouzhx on 2018/3/21.
 *
 *         ThreadLocal的作用 和使用 http://www.cnblogs.com/xrq730/p/4854820.html
 */
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
 * 3、ThreadLocal.get()取值，就是根据当前的线程，获取线程中自己的ThreadLocal.ThreadLocalMap，然后在这个Map中根据第二点中循环计数器取得一个特定value值
 */
public class ThreadLocalTools {
  // ThreadLocal只能存一个值，一个Request由于是Map形式的，可以用key-value形式存多个值
  public static ThreadLocal<String> threadLocal = new ThreadLocal<>();
}
