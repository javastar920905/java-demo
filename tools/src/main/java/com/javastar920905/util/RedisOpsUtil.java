package com.javastar920905.util;

/**
 * @author ouzhx on 2018/5/23.
 */
public class RedisOpsUtil {
  /** 最大时间 MAX_TIME 9999999999999 （13位） **/
  private static final long MAX_TIME = 9999999999999L;
  /** 等级偏移 10000000000000000（14位） **/
  private static final long SCORE_MOVE = Double.valueOf(Math.pow(10, 14)).longValue();

  /**
   * redis zadd 计算可用于分数和时间排序的 分数
   *
   * 参考文档: https://www.cnblogs.com/cci8go/p/5964485.html
   * 
   * redis zadd 文档: http://www.redis.cn/commands/zadd.html
   * 
   * 
   * 方式二 14 ~ 19位，那么等级最大数据就只能是 919999，超过这个数就会溢出。可以把时间戳降低到秒级别，可以支持更大数字
   * 
   * @return
   */
  public static long getRankScore(int score) {
    return score * SCORE_MOVE + MAX_TIME - System.currentTimeMillis();
  }

  public static void main(String[] args) {
    System.out.println();
  }

}
