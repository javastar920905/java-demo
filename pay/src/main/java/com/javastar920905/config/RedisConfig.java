package com.javastar920905.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

/**
 * @author ouzhx on 2017/12/21.
 */
@Configuration
public class RedisConfig {
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

}
