package com.javastar920905.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javastar920905.listener.RedisSubscribeListener;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ouzhx on 2017/12/21.
 */
@Configuration
@EnableCaching
public class RedisConfig {
  public static final String CHANNEL_TOPIC_RED_PACKET = "redPacket";
  public static final String CACHE_MANAGER_SHORT = "redisCacheManager";

  public interface Cachekey {
    String CACHE_REDPACKET_DETAIL = "cache:redPacket:detail";
    String CACHE_REDPACKET_USER_DETAIL = "cache:redPacket:user:detail";
  }

  // 缓存管理器 只缓存1分钟
  @Bean(CACHE_MANAGER_SHORT)
  @Primary
  public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
    // 设置缓存过期时间
    cacheManager.setDefaultExpiration(60 * 1);

    // 单独设置某些key的过期时间
    Map<String, Long> expires = new HashMap<>();
    long redPacketExpireTime = 60L * 60 * 24;
    expires.put(Cachekey.CACHE_REDPACKET_DETAIL, redPacketExpireTime);
    expires.put(Cachekey.CACHE_REDPACKET_USER_DETAIL, redPacketExpireTime);
    cacheManager.setExpires(expires);
    return cacheManager;
  }

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
    setSerializer(template);
    return template;
  }

  private void setSerializer(StringRedisTemplate template) {
    @SuppressWarnings({"rawtypes", "unchecked"})
    Jackson2JsonRedisSerializer jackson2JsonRedisSerializer =
        new Jackson2JsonRedisSerializer(Object.class);
    ObjectMapper om = new ObjectMapper();
    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    jackson2JsonRedisSerializer.setObjectMapper(om);
    template.setValueSerializer(jackson2JsonRedisSerializer);
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
