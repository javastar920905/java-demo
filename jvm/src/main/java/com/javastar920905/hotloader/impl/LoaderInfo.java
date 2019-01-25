package com.javastar920905.hotloader.impl;

import com.javastar920905.hotloader.MyClassLoader;

/**
 * 封装加载类信息
 * @author ouzhx on 2019/1/25.
 */
public class LoaderInfo {

    /**自定义类加载器*/
    private MyClassLoader myClassLoader;
    /**记录类的加载时间戳*/
    private long loadTime;

    private HotDeployBase hotDeployBase;

    public LoaderInfo(MyClassLoader myClassLoader, long loadTime) {
        //?
        super();
        this.myClassLoader = myClassLoader;
        this.loadTime = loadTime;
    }

    public HotDeployBase getHotDeployBase() {
        return hotDeployBase;
    }

    public void setHotDeployBase(HotDeployBase hotDeployBase) {
        this.hotDeployBase = hotDeployBase;
    }

    public MyClassLoader getMyClassLoader() {
        return myClassLoader;
    }

    public long getLoadTime() {
        return loadTime;
    }
}
