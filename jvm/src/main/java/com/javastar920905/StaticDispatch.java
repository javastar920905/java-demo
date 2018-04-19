package com.javastar920905;

/**
 * @author ouzhx on 2018/4/19.
 *
 *         方法静态分配演示
 */
public class StaticDispatch {
  static abstract class Human {
  }
  static class Man extends Human {
  }
  static class Woman extends Human {
  }

  public void sayHello(Human guy) {
    System.out.println("hello,guy！");
  }

  public void sayHello(Man guy) {
    System.out.println("hello,gentleman！");
  }

  public void sayHello(Woman guy) {
    System.out.println("hello,lady！");
  }

  public static void main(String[] args) {
    /**
     * Human 被称为静态类型 或者外观类型
     *
     * Man 被称为实际类型
     */
    Human man = new Man();
    Human woman = new Woman();

    StaticDispatch sr = new StaticDispatch();
    /**
     * 静态类型变化
     *
     * 静态分派
     */
    sr.sayHello(man);
    sr.sayHello(woman);
  }
}
