package com.javastar920905.outer.redis;

import com.javastar920905.outer.spring.SpringContextUtil;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;

/**
 * Created by ouzhx on 2016/8/23.
 */
@Component
public class RedisFactory {

  private RedisFactory() {}

  /**
   * 获取redis连接 用完以后一定要关闭连接！
   *
   * @return RedisConnection
   */
  public static RedisConnection getConnection() {
    JedisConnectionFactory factory =
        SpringContextUtil.getBean("jedisConnectionFactory", JedisConnectionFactory.class);
    return factory.getConnection();
  }
}
