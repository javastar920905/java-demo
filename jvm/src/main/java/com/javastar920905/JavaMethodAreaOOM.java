package com.javastar920905;

/**
 * @author ouzhx on 2018/4/17.
 *
 *         借助CGLib使方法区出现内存溢出异常
 */
public class JavaMethodAreaOOM {
  /**
   * VM Args：-verbose:gc -XX:PermSize=10M -XX:MaxPermSize=10M (限制方法区大 小，从而间接限制其中常量池的容量--在jdk 1.6中有用)
   * 
   * @param args
   */
  public static void main(String[] args) {
    // todo 缺少 cglib 依赖
    // while (true) {
    // Enhancer enhancer = new Enhancer();
    // enhancer.setSuperclass(OOMObject.class);
    // enhancer.setUseCache(false);
    // enhancer.setCallback(new MethodInterceptor() {
    // public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
    // throws Throwable {
    // return proxy.invokeSuper(obj, args);
    // }
    // });
    // enhancer.create();
    // }

    /**
     * 方法区溢出也是一种常见的内存溢出异常，一个类要被垃圾收集器回收掉，判定条件是 比较苛刻的。在经常动态生成大量Class的应用中，需要特别注意类的回收状况。这类场景除
     * 了上面提到的程序使用了CGLib字节码增强和动态语言之外，常见的还有：大量JSP或动态产 生JSP文件的应用（JSP第一次运行时需要编译为Java类）、基于OSGi的应用（即使是同一个
     * 类文件，被不同的加载器加载也会视为不同的类）等。
     */
  }

  static class OOMObject {
  }
}
