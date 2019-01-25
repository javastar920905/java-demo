package com.javastar920905.collection;

import com.javastar920905.entity.Person;
import com.javastar920905.entity.PersonComparator;
import com.javastar920905.entity.PersonNoCompare;
import org.junit.Test;

import java.util.TreeSet;

/**
 * @author ouzhx on 2018/10/17.
 * https://blog.csdn.net/qq_33642117/article/details/52040345
 */
public class TreeSetTest {
    //有一批数据，要求不能重复存储元素，而且要排序
    @Test
    public void name() {
        //红-黑树的数据结构，默认对元素进行自然排序（String）。如果在比较的时候两个对象返回值为0，那么元素重复
        TreeSet ts = new TreeSet();
        ts.add("ccc");
        ts.add("aaa");
        ts.add("ddd");
        ts.add("bbb");

        System.out.println(ts); // [aaa, bbb, ccc, ddd]
    }

    //使用Person的默认排序
    @Test
    public void name2() {
        TreeSet ts = new TreeSet();
        ts.add(new Person("aa", 20, "男"));
        ts.add(new Person("bb", 18, "女"));
        ts.add(new Person("cc", 17, "男"));
        ts.add(new Person("dd", 17, "女"));
        ts.add(new Person("dd", 15, "女"));
        ts.add(new Person("dd", 15, "女"));


        System.out.println(ts);//[Person [name=dd, age=15, gender=女], Person [name=cc, age=17, gender=男], Person [name=dd, age=17, gender=女], Person [name=bb, age=18, gender=女], Person [name=aa, age=20, gender=男]]
        System.out.println(ts.size()); //
    }

    /**
     * 需求：当元素自身不具备比较性，或者元素自身具备的比较性不是所需的。
     *
     * 那么这时只能让TreeSet容器自身具备。
     *
     * 定义一个类实现Comparator 接口，覆盖compare方法。
     *
     * 并将该接口的子类对象作为参数传递给TreeSet集合的构造函数。
     */
    @Test
    public void name3() {
        TreeSet ts = new TreeSet(new PersonComparator());
        ts.add(new PersonNoCompare("aa", 20, "男"));
        ts.add(new PersonNoCompare("bb", 18, "女"));
        ts.add(new PersonNoCompare("cc", 17, "男"));
        ts.add(new PersonNoCompare("dd", 17, "女"));
        ts.add(new PersonNoCompare("dd", 15, "女"));
        ts.add(new PersonNoCompare("dd", 15, "女"));


        System.out.println(ts);//[Person [name=dd, age=15, gender=女], Person [name=cc, age=17, gender=男], Person [name=dd, age=17, gender=女], Person [name=bb, age=18, gender=女], Person [name=aa, age=20, gender=男]]
        System.out.println(ts.size()); //
    }


}
