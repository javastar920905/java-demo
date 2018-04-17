package com.javastar920905;

/**
 * @author ouzhx on 2018/4/17.
 * 
 * 
 *         创建线程导致内存溢出异常
 *
 *         //todo VM Args：-verbose:gc -Xss2m
 * 
 * 
 */
public class JavaVMStackOOM {

  private void dontStop() {
    while (true) {
    }
  }

  public void stackLeakByThread() {
    while (true) {
      new Thread(() -> dontStop()).start();
    }
  }

  /**
   * 特别提示一下，如果读者要尝试运行上面这段代码，记得要先保存当前的工作。
   * 
   * 由于在Windows平台的虚拟机中，Java的线程是映射到操作系统的内核线程上的 [1] ，因此上述 代码执行时有较大的风险，可能会导致操作系统假死。
   * 
   * @param args
   * @throws Throwable
   */
  public static void main(String[] args) throws Throwable {
      //代码注释...没办法死机两次了
//    JavaVMStackOOM oom = new JavaVMStackOOM();
//    oom.stackLeakByThread();
  }
}
