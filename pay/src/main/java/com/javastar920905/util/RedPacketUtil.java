package com.javastar920905.util;

import com.javastar920905.entity.domain.RedPacket;
import com.javastar920905.outer.spring.SpringContextUtil;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RKeys;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import java.math.BigDecimal;


/**
 * @author ouzhx on 2018/3/28.
 * 
 *         redisson 官网 https://redisson.org/
 * 
 *         github wiki https://github.com/redisson/redisson/wiki/6.-distributed-objects
 * 
 *         redisson api 使用演示 https://blog.csdn.net/u014042066/article/details/72778440 redisson 和
 *         原生api对比 https://www.cnblogs.com/mcbye/archive/2017/08/18/Jedis-VS-Redisson.html
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

  /**
   *
   * 使用方式: RedPacketUtil.getKeys().delete(key...)
   *
   * RedPacketUtil.getKeys().countExists(?) == 1
   *
   * @return redisson 对key操作的封装
   */
  public static RKeys getKeys() {
    return redissonClient.getKeys();
  }

  /**
   *
   *
   * 使用演示:
   *
   * 获取红包对象 RedPacket redPacket = RedPacketUtil.getRedPacket(redPacketId).get()
   *
   * 设置红包对象 RedPacketUtil.getRedPacket(redPacketId).set(redPacket)
   *
   * @param redPacketId
   * @return 获取封装后的红包对象
   */
  public static RBucket<RedPacket> getRedPacket(String redPacketId) {
    return redissonClient.getBucket(String.format("redPacket:%s", redPacketId));
  }


  /**
   *
   * RMap<String, Integer> graped = RedPacketUtil.getGrabbedUser(redPacketId).get(xx);
   *
   * @param redPacketId 红包id
   * @return 已抢到红包对象封装 RMap 判断是否为空 if (graped.size() > 0 && graped.get(openId) != null) {}
   */
  public static RMap<String, Integer> getRobbedUser(String redPacketId) {
    return redissonClient.getMap(String.format("redPacket:%s:robbed", redPacketId));
  }

  /**
   * 将分为单位的转换为元 （除100）
   *
   * @param amount
   * @return
   * @throws Exception
   */
  public static BigDecimal changeF2Y(Long amount) {
    return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100));
  }

  /**
   * 将元为单位的转换为分 （乘100）
   *
   * @param amount
   * @return
   */
  public static BigDecimal changeY2F(Long amount) {
    return BigDecimal.valueOf(amount).multiply(new BigDecimal(100));
  }
}
