package com.javastar920905.hotloader.impl;

/**
 * 此类需要实现java类热加载功能
 *
 * @author ouzhx on 2019/1/25.
 */
public class MyHotImpl implements HotDeployBase {
    @Override
    public void logic() {

        System.out.println("当字节码改变时,我需要被热加载");

        //System.out.println("解除注释后,rebuild一下工程,我需要热加载");

    }
}
