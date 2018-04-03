package com.javastar920905.learnutil;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author ouzhx on 2018/4/3. Arrays 工具类熟悉 数组的常用操作
 * 
 *         api 功能介绍 https://www.cnblogs.com/HeDante/p/7464874.html
 */
public class ArraysAPIDemo {
  @Test
  public void apiTest() {
    // 1 返回一个固定大小的list
    List<Integer> intList = Arrays.asList(1, 2, 3, 4);

    // 2 binarySearch方法支持在整个数组中查找，如：(也支持查找指定范围)
    int index = Arrays.binarySearch(new int[] {1, 2, 3, 4, 5, 6, 7}, 6);
    int index2 = Arrays.binarySearch(new String[] {"2", "5", "8"}, "5");
    System.out.println(index2);

    // 3 copyOf及copyOfRange方法
    String[] names2 = {"Eric", "John", "Alan", "Liz"};

    // [Eric, John, Alan]
    String[] copy = Arrays.copyOf(names2, 3);
    System.out.println("3 copy结果: " + Arrays.toString(copy));
    // [Alan, Liz]
    String[] rangeCopy = Arrays.copyOfRange(names2, 2, names2.length);


    // 4. sort方法
    String[] names = {"Liz", "bob", "Eric", "Alan", "Bib"};
    // 全部排序
    // 排序后:[Alan, Bib, Eric, Liz, bob]
    Arrays.sort(names);
    System.out.println("4 排序后:" + Arrays.toString(names));
    // 只排序前两个
    // [John, Liz, Eric, Alan]
    Arrays.sort(names, 0, 2);

    // 5 toString如果需要打印二维数组的内容：
    int[][] stuGrades = {{80, 81, 82}, {84, 85, 86}, {87, 88, 89}};
    Arrays.deepToString(stuGrades);

    // 7. equals方法
    // 使用Arrays.equals来比较1维数组是否相等。
    // Arrays.deepEquals能够去判断更加复杂的数组是否相等。
    String[] names1 = {"Eric", "John", "Alan", "Liz"};

    String[] names3 = {"Eric", "John", "Alan", "Liz"};
    System.out.println("7 equals方法" + Arrays.equals(names1, names3));

    // 8 fill方法
    int[] array1 = new int[8];
    Arrays.fill(array1, 2);
    // [2, 2, 2, 2, 2, 2, 2, 2]
    System.out.println("8 数组填充:" + Arrays.toString(array1));

    // 9 使用一个generator生成器,重新生成一个新数组
    int[] setAllArr = new int[5];
    System.out.println("9 copy 后" + Arrays.toString(setAllArr));
    // 一个数组全部做表达式操作
    Arrays.setAll(setAllArr, idx -> idx * 3);
    System.out.println("9 数组每个元素*3" + Arrays.toString(setAllArr));
  }

  @Test
  public void sortTest() {
    /*
     * 另外，Arrays的sort方法也可以结合比较器，完成更加复杂的排序。 public static <T> void sort(T[] a, Comparator<? super T>
     * c) { if (LegacyMergeSort.userRequested) legacyMergeSort(a, c); else TimSort.sort(a, c); }
     */
  }
}
