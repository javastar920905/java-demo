package com.javastar920905.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.DefaultCookieSerializer;

import com.javastar920905.constant.CommonConstants;

import redis.clients.jedis.JedisPoolConfig;

/**
 * @author ouzhx on 2017/12/21.
 */
@Configuration
@EnableRedisHttpSession(redisNamespace = "javastar", maxInactiveIntervalInSeconds = 60 * 60 * 4)
public class WebRedisConfig {
  @Bean
  public JedisPoolConfig jedisPoolConfig() {
    JedisPoolConfig config = new JedisPoolConfig();
    // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
    // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    config.setMaxTotal(500);

    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
    config.setMaxIdle(500);

    // 示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
    config.setMaxWaitMillis(1000 * 10);
    config.setTestOnBorrow(true);
    return config;

  }

  @Bean(name = "jedisConnectionFactory")
  public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig,
      @Value("${redis.host}") String redisHost) {
    JedisConnectionFactory factory = new JedisConnectionFactory();
    factory.setUsePool(true);
    factory.setPoolConfig(jedisPoolConfig);
    factory.setHostName(redisHost);
    factory.setPort(6379);
    return factory;
  }

  @Bean
  public StringRedisTemplate redisTemplate(ApplicationContext context) {
    StringRedisTemplate template = new StringRedisTemplate(
        context.getBean("jedisConnectionFactory", JedisConnectionFactory.class));
    return template;
  }

  /**
   * 单点登录配置 cookie 设置在 指定域名(ip)/下 同时cookie名为CommonConstants.SSOSESSION
   * (注意 必须要指定域名系统才能登录)
   * @param cookieSSODomain
   * @return
   */
  @Bean
  @Profile("default")
  public DefaultCookieSerializer cookieSerializer(
      @Value("${cookie.domain}") String cookieSSODomain) {
    DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
    cookieSerializer.setCookiePath("/");
    cookieSerializer.setCookieName(CommonConstants.SSOSESSION);
    cookieSerializer.setDomainName(cookieSSODomain);
    cookieSerializer.setUseHttpOnlyCookie(false);
    return cookieSerializer;
  }

}
