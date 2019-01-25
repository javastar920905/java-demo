package com.javastar920905.concurent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ouzhx on 2019/1/23.
 */
public class SynchronizedDemo {

    //非线程安全
    public static class Data implements Runnable {
        int count = 0;

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        }
    }

    //a 和count 都是线程安全操作
    public static class DataSync implements Runnable {
        AtomicInteger a = new AtomicInteger();
        int count = 0;

        @Override
        public void run() {
            //this 指当前线程
            synchronized (this) {
                for (int i = 0; i < 10000; i++) {
                    count++;
                }
            }

            for (int i = 0; i < 10000; i++) {
                a.getAndIncrement();
            }
        }
    }


    public static void main(String[] args) {
        //ConfigurableApplicationContext context = SpringApplication.run(TaxArticleApplication.class, args);

        /**非线程安全测试**/
        Data instance = new Data();

        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
            //等待结果执行完毕再玩下
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //结果小于2w
        System.out.println(instance.count);


        /**线程安全ces**/
        DataSync dataSync = new DataSync();
        Thread t3 = new Thread(dataSync);
        Thread t4 = new Thread(dataSync);
        t3.start();
        t4.start();
        while (t3.isAlive() || t4.isAlive()) {
            //等待结果执行完毕再往下
        }

        //结果小于2w
        System.out.println(dataSync.count);
        //结果等于2w
        System.out.println(dataSync.a);


        /**没有并发问题,各个对象锁独立**/
        DataSync dataSync2 = new DataSync();
        DataSync dataSync3 = new DataSync();
        Thread t5 = new Thread(dataSync2);
        Thread t6 = new Thread(dataSync3);
        t5.start();
        t6.start();
        while (t5.isAlive() || t6.isAlive()) {
            //等待结果执行完毕再往下
        }

        //结果等于1w
        System.out.println(dataSync2.count);
        System.out.println(dataSync3.count);
        //结果等于1w
        System.out.println(dataSync2.a);
        System.out.println(dataSync3.a);

        /**类锁样式 n个对象排队堵塞执行**/
        synchronized (DataSync.class){

        }

    }

}
