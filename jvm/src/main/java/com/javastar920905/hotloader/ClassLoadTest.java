package com.javastar920905.hotloader;

/**
 * @author ouzhx on 2019/1/25.
 */
public class ClassLoadTest {
    public static void main(String[] args) {
        new Thread(new MsgThread()).start();
    }
}
