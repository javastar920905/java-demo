package com.javastar920905;

/**
 * @author ouzhx on 2018/4/17.
 * 
 *         虚拟机栈和本地方法栈溢出
 */
public class JavaVMStackSOF {
  private int stackLength = 1;

  /**
   * VM Args：-verbose:gc -Xss128k (-Xss参数减少栈内存容量) 如果线程请求的栈深度大于虚拟机所允许的最大深度，将抛出StackOverflowError异常。
   * 如果虚拟机在扩展栈时无法申请到足够的内存空间，则抛出OutOfMemoryError异常。
   * 
   * 报错如下 Exception in thread "main" java.lang.StackOverflowErrorstack length：18981
   * 
   * 出现StackOverflowError异常时有错误 堆栈可以阅读，相对来说，比较容易找到问题的所在。
   */
  public void stackLeak() {
    stackLength++;
    stackLeak();
  }

  public static void main(String[] args) throws Throwable {
    JavaVMStackSOF oom = new JavaVMStackSOF();
    try {
      oom.stackLeak();
    } catch (Throwable e) {
      System.out.println("stack length：" + oom.stackLength);
      throw e;
    }
  }
}
