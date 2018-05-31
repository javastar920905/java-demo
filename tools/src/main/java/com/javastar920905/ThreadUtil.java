package com.javastar920905;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ouzhx on 2018/5/31.
 */
public class ThreadUtil {
  public static ThreadPoolExecutor threadPoolExecutor;

  static {
    // ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("ffmpeg-pool-%d").build();
    threadPoolExecutor =
        new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1024));
  }
}
