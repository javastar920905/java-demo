package com.javastar920905.entity;

import java.util.Comparator;

/**
 * @author ouzhx on 2018/10/17.
 */
public class PersonComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        PersonNoCompare b1 = (PersonNoCompare) o1;
        PersonNoCompare b2 = (PersonNoCompare) o2;
        System.out.println(b1 + " comparator " + b2);
        //默认倒序排序
        if (b1.getAge() > b2.getAge()) {
            return -1;
        }
        if (b1.getAge() < b2.getAge()) {
            return 1;
        }
        return b1.getName().compareTo(b2.getName());
    }
}
