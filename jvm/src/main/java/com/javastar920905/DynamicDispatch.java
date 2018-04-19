package com.javastar920905;

/**
 * @author ouzhx on 2018/4/19.
 */
public class DynamicDispatch {
  static abstract class Human {
    protected abstract void sayHello();
  }
  static class Man extends Human {
    @Override
    protected void sayHello() {
      System.out.println("man say hello ");
    }
  }
  static class Woman extends Human {
    @Override
    protected void sayHello() {
      System.out.println("woman say hello ");
    }
  }



  public static void main(String[] args) {
    Human man = new Man();
    Human woman = new Woman();

    /**
     * 虚拟机是如何知道要调用哪个方法 的？
     */
    man.sayHello();
    woman.sayHello();

  }
}
