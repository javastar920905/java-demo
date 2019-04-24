package cn.javabus.codeidentification.util;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ouzhx on 2018/5/31.
 */
public class ThreadUtil {
  public static ThreadPoolExecutor threadPoolExecutor;

  static {
    threadPoolExecutor =
        new ThreadPoolExecutor(5, 20, 30, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1024));
  }
}
