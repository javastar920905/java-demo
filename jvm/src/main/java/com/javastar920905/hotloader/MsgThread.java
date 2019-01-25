package com.javastar920905.hotloader;

import com.javastar920905.hotloader.impl.HotDeployBase;
import com.javastar920905.hotloader.impl.HotDeployFactory;

import java.util.concurrent.TimeUnit;

/**
 * 不断刷新重新加载实现了热加载的类
 *
 * @author ouzhx on 2019/1/25.
 */
public class MsgThread implements Runnable {
    @Override
    public void run() {
        while (true) {
            HotDeployBase hotClass = HotDeployFactory.getHotClass(HotDeployFactory.myHotClassName);
            hotClass.logic();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
