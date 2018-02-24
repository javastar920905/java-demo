package com.javastar920905.javademo.java8.stream;

import com.javastar920905.javademo.JavademoApplicationTests;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ouzhx on 2017/12/1.
 */
public class HelloStream extends JavademoApplicationTests {
    List<Integer> numberList =
        Arrays.asList(1, 1, 2, 3, 1, 234, 54, 45, 655, 65, 76, 878, 34, 4, 5, 6, 7, 86, 9);

    /**
     * stream +for each 实现集合输出
     */
    @Test public void test1() {
        //将函数isHidden 利用Java 8的方法引用 :: 语法(即“把这个方法作为值”）将其传给 listFiles 方法)
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);
        //实现输出
        Arrays.stream(hiddenFiles).forEach(f -> System.out.println(f.getName()));
    }

    /***
     *  collect() 使用以及集合排序
     * @throws Exception
     */
    @Test public void test2() throws Exception {
        //从集合中筛选符合条件(求偶数)的元素列表, collect()
        List<Integer> oushuList =
            numberList.stream().filter(item -> item % 2 == 0).collect(Collectors.toList());
        //实现排序
        //1
        Collections.sort(oushuList);
        oushuList.stream().forEach(item -> System.out.println(item));
        //2 使用Comparator 接口实现自定义排序
        oushuList.sort(new Comparator<Integer>() {
            @Override public int compare(Integer n1, Integer n2) {
                return n2.compareTo(n1);
            }
        });
        oushuList.stream().forEach(item -> System.out.println(item));

        //3 使用lambda 表达式
        oushuList.sort((n1, n2) -> n1.compareTo(n2));
        //实现输出
        oushuList.stream().forEach(item -> System.out.println(item));
    }

    /**
     * 构建流
     *
     * @throws Exception
     */
    @Test public void buildSteamTest() throws Exception {
        //静态方法 Stream.of ，通过显式值创建一个流。它可以接受任意数量的参数
        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        //空流
        Stream<String> emptyStream = Stream.empty();

        //数组构建流
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();

        //由文件生成流  使用 Files.lines 得到一个流(每个元素都是给定文件中的一行)
        //应该注意的是，你该如何使用 flatMap 产生一个扁平的单词流，而不是给每一行生成一个单词流
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
        } catch (IOException e) {
        }

        // Stream.iterate 和 Stream.generate 。 0,2,4...
        Stream.iterate(0, n -> n + 2)
            .limit(10)
            .forEach(System.out::println);
    }

    /**
     * stream api 使用 (遍历对每个元素,执行流api)
     *
     * @throws Exception
     */
    @Test public void streamTest() throws Exception {

        //distinct 排除相同的元素
        numberList.stream().distinct().forEach(System.out::println);

        //filter  筛选过滤
        List<Integer> subList =
            numberList.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());

        //流还支持 skip(n) 方法，返回一个扔掉了前 n 个元素的流
        System.out.println("================>");
        numberList.stream().skip(3).forEach(System.out::println);

        //limit(n) 方法，该方法会返回一个不超过给定长度的流
        numberList.stream().limit(3).collect(Collectors.toList());

        //流支持 map 方法，它会接受一个函数作为参数。
        // 这个函数会被应用到每个元素上，并将其映射成一个新的元素（使用映射一词，是因为它和转换类似，但其中的细微差别在于它是“创建一 个新版本”而不是去“修改”）
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream().map(String::length).collect(Collectors.toList());
        //给定[1, 2, 3, 4,5]，应该返回[1, 4, 9, 16, 25]
        System.out.println("================>");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream().map(n -> n * n).forEach(System.out::println);

        //使用 flatMap (比较晦涩)
        //给定列表[1, 2, 3]和列表[3, 4]，应该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        //使用两个 map 来迭代这两个列表
        numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[] {i, j}))
            .forEach(System.out::println);



        //归约求和 reduce 首先， 0 作为Lambda（ a ）的第一个参数,然后取出numberList.stream()流的第一个参数 执行a+b运算,得到新的结果与流中de第二个参数继续计算...
        numberList.stream().reduce(0, (a, b) -> a + b);
        numberList.stream().reduce(0, Integer::sum);
        //最小值
        Optional<Integer> min = numberList.stream().reduce(Integer::min);
        //map 和 reduce 的连接通常称为 map-reduce 模式，因Google用它来进行网络搜索而出名
    }


    /**
     * 解决空引用问题null
     * (“价值亿万美金的错误”。就是在1965年发明了空引用--计算机科学巨擘之一托尼·霍尔)
     *
     * @throws Exception
     */
    @Test public void OptionalTest() throws Exception {
        //选择大于2的任意元素
        Optional<Integer> num = numberList.stream().filter(n -> n > 2).findAny();
    }
}
