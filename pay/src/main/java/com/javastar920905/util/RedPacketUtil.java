package com.javastar920905.util;

import com.javastar920905.outer.spring.SpringContextUtil;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;


/**
 * @author ouzhx on 2018/3/28.
 * 
 *         redisson 官网 https://redisson.org/ redisson api 使用演示
 *         https://blog.csdn.net/u014042066/article/details/72778440 redisson 和 原生api对比
 *         https://www.cnblogs.com/mcbye/archive/2017/08/18/Jedis-VS-Redisson.html
 */

public class RedPacketUtil {
  private RedPacketUtil() {}

  private static RedissonClient redissonClient =
      SpringContextUtil.getApplicationContext().getBean(RedissonClient.class);

  /**
   * 操作 红包未领取数量 封装
   * 
   * 获取数量 RedPacketUtil.getRedPacketRemain(redPacketId).get()
   * 
   * 设置红包数量 RedPacketUtil.getRedPacketRemain(redPacketId).set(数量)
   * 
   * 其他操作看api
   * 
   * @param redPacketId 红包id
   * @return
   */
  public static RAtomicLong getRedPacketRemain(String redPacketId) {
    return redissonClient.getAtomicLong(String.format("redPacket:%s:size", redPacketId));
  }
}
