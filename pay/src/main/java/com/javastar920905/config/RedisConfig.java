package com.javastar920905.config;

import com.javastar920905.listener.RedisSubscribeListener;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author ouzhx on 2017/12/21.
 */
@Configuration
public class RedisConfig {
  public static final String CHANNEL_TOPIC_RED_PACKET = "redPacket";

  @Bean
  public JedisPoolConfig jedisPoolConfig() {
    JedisPoolConfig config = new JedisPoolConfig();
    config.setMaxTotal(500);
    config.setMaxIdle(500);
    config.setMaxWaitMillis(1000 * 10);
    config.setTestOnBorrow(true);
    return config;
  }

  @Bean(name = "jedisConnectionFactory")
  public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
    JedisConnectionFactory factory = new JedisConnectionFactory();
    factory.setUsePool(true);
    factory.setPoolConfig(jedisPoolConfig);
    factory.setHostName(PropertiesConfig.properties.getProperty("redis.host"));
    factory.setPort(6379);
    return factory;
  }

  @Bean
  public StringRedisTemplate redisTemplate(ApplicationContext context) {
    StringRedisTemplate template = new StringRedisTemplate(
        context.getBean("jedisConnectionFactory", JedisConnectionFactory.class));
    return template;
  }

  /** 订阅发布 **/
  @Bean
  public RedisMessageListenerContainer messageListenerContainer(ApplicationContext context) {
    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(
        context.getBean("jedisConnectionFactory", JedisConnectionFactory.class));

    // 使用当前Listener 监听 redPacket 频道
    ChannelTopic topic = new ChannelTopic(CHANNEL_TOPIC_RED_PACKET);
    container.addMessageListener(new RedisSubscribeListener(), topic);
    return container;
  }

  /**
   * 分布式锁实现
   * 
   * @return
   */
  @Bean
  public RedissonClient redissonClient() {
    Config config = new Config();
    config.useSingleServer().setAddress(
        String.format("redis://%s:6379", PropertiesConfig.properties.getProperty("redis.host")));
    RedissonClient client = Redisson.create(config);
    return client;
  }
}
