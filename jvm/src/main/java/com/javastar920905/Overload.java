package com.javastar920905;

import java.io.Serializable;

/**
 * @author ouzhx on 2018/4/19.
 * 
 *         重载方法匹配优先级
 */
public class Overload {
  // public static void sayHello(Object arg) {
  // System.out.println("hello Object");
  // }

  // public static void sayHello(int arg) {
  // System.out.println("hello int");
  // }

  // public static void sayHello(long arg) {
  // System.out.println("hello long");
  // }

  // public static void sayHello(Character arg) {
  // System.out.println("hello Character");
  // }

  // public static void sayHello(char arg) {
  // System.out.println("hello char");
  // }

  public static void sayHello(char... arg) {
    System.out.println("hello char……");
  }

  // public static void sayHello(Serializable arg) {
  // System.out.println("hello Serializable");
  // }

  public static void main(String[] args) {
    /**
     * 1 不注释任何代码, 输出 hello char
     *
     * 2 注释 sayHello（char arg）方法 ,输出hello int 这时发生了一次自动类型转换，'a'除了可以代表一个字符串，还可以代表数字97（字
     * 符'a'的Unicode数值为十进制数字97），因此参数类型为int的重载也是合适的。
     *
     * 3 继续注释 sayHello（int arg）方法, 输出hello long 这时发生了两次自动类型转换，'a'转型为整数97之后，进一步转型为长整数97L，匹配
     * 了参数类型为long的重载。笔者在代码中没有写其他的类型如float、double等的重载，不过实
     * 际上自动转型还能继续发生多次，按照char-＞int-＞long-＞float-＞double的顺序转型进行匹
     * 配。但不会匹配到byte和short类型的重载，因为char到byte或short的转型是不安全的。
     *
     * 4 继续注释 sayHello（long arg）方法, 输出 hello Character
     * 这时发生了一次自动装箱，'a'被包装为它的封装类型java.lang.Character，所以匹配到了 参数类型为Character的重载
     *
     * 5 继续注释 sayHello（Character arg）方法，那输出hello Serializable
     * 这个输出可能会让人感觉摸不着头脑，一个字符或数字与序列化有什么关系？出现hello
     * Serializable，是因为java.lang.Serializable是java.lang.Character类实现的一个接口，当自动装箱
     * 之后发现还是找不到装箱类，但是找到了装箱类实现了的接口类型，所以紧接着又发生一次
     * 自动转型。char可以转型成int，但是Character是绝对不会转型为Integer的，它只能安全地转 型为它实现的接口或父类。
     *
     * 6 继续注释 后输出hello Object
     * 
     * 7 7个重载方法已经被注释得只剩一个了，可见变长参数的重载优先级是最低的，这时候 字符'a'被当做了一个数组元素。
     * 
     * 演示了编译期间选择静态分派目标的过程
     */
    sayHello('a');
  }
}
