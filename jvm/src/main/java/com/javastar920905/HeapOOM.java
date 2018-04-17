package com.javastar920905;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ouzhx on 2018/4/17.
 *
 *         Java堆溢出- 堆内存泄露示例
 */
public class HeapOOM {

  static class OOMObject {
  }

  /**
   * 
   * VM Args：-verbose:gc -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
   * (限制Java堆的大小为20MB，不可扩展,通过参数-XX:+HeapDumpOnOutOfMemoryError可以让虚拟机在出现内存溢出异常时Dump出当前的内存堆
   * 转储快照以便事后进行分析 )
   * 
   * 报错结果 Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
   *
   * 原因: Java堆用于存储对象实例，只要不断地创建对象，并且保证GC Roots到对象之间有可达 路径来避免垃圾回收机制清除这些对象，那么在对象数量到达最大堆的容量限制后就会产生
   * 内存溢出异常。
   * 
   * @param args
   */
  public static void main(String[] args) {
    List<OOMObject> list = new ArrayList<>();
    while (true) {
      list.add(new OOMObject());
    }
  }
}
