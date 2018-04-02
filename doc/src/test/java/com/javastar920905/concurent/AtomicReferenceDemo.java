package com.javastar920905.concurent;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author ouzhx on 2018/4/2.
 * 
 *         提供了一个可以被原子性读和写的对象引用变量。
 * 
 *         原子性的意思是多个想 要改变同一个 AtomicReference 的线程不会导致 AtomicReference 处于不一致的状态。 AtomicReference 还有一个
 *         compareAndSet() 方法，通过它你可以将当前引用于一个期望值 (引用)进行比较，如果相等，在该 AtomicReference 对象内部设置一个新的引用。
 */
public class AtomicReferenceDemo {

  @Test
  public void autoReferenceAPITest() {
    // 创建对象
    AtomicReference atomicReference = new AtomicReference();
    // 使用指定引用创建对象
    String initialReference = "the initially referenced string";
    AtomicReference atomicReference2 = new AtomicReference(initialReference);

    // 创建泛型引用
    AtomicReference<String> atomicStringReference = new AtomicReference<>();
    AtomicReference<String> atomicStringReference2 = new AtomicReference<>(initialReference);

    // 获取 AtomicReference
    String automicStr = (String) atomicReference.get();
    String automicStr2 = atomicStringReference2.get();

    // 设置 AtomicReference 引用
    atomicReference.set(initialReference);

    // 比较并设置 AtomicReference 引用
    /**
     * compareAndSet() 可以将保存在 AtomicReference 里的引用于一个期望引用进行比较，如果两个引用是一样的(并非 equals() 的相等，而是 ==
     * 的一样)，将会给 AtomicReference 实例设置一个新的引用。
     **/

    String initialObj = "initial value referenced";
    // 创建了一个带有一个初始对象的AtomicReference
    AtomicReference<String> atomicObjReference = new AtomicReference<>(initialObj);
    String newObj = "new value referenced";
    // 对存储值和期望值进行对比, 期望值和已存在值相同,新值会被替换
    boolean exchanged = atomicObjReference.compareAndSet(initialObj, newObj);
    System.out.println("exchanged: " + exchanged);

    // 已存在值已经被替换了, 与期望值不同,返回false
    exchanged = atomicStringReference.compareAndSet(initialObj, newObj);
    System.out.println("exchanged: " + exchanged);
  }
}
