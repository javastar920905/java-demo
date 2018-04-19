package com.javastar920905;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ouzhx on 2018/4/19.
 */
public class DynamicProxyTest {

  interface IHello {
    void sayHello();
  }

  static class Hello implements IHello {
    @Override
    public void sayHello() {
      System.out.println("hello world");
    }
  }


  static class DynamicProxy implements InvocationHandler {
    Object originalObj;

    Object bind(Object originalObj) {
      this.originalObj = originalObj;

      // 这个方法返回一个实现了IHello的接口，并且代理了new Hello（）实例行为的对象
      return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(),
          originalObj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      System.out.println("welcome");
      return method.invoke(originalObj, args);
    }
  }

  public static void main(String[] args) {
    IHello hello = (IHello) new DynamicProxy().bind(new Hello());
    hello.sayHello();
  }
}
