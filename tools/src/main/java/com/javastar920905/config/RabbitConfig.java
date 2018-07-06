package com.javastar920905.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

import com.javastar920905.outer.spring.mq.RabbitMessageProducer;

/**
 * @author ouzhx by
 */
@Configuration
@EnableRabbit
@Import(RabbitMessageProducer.class)
public class RabbitConfig {
  public static final String DEFAULT_EXCHAGE = "defaultexchage";
  public static final String QUQUE_REDPACKET = "redPacket";
  // 秒杀实现1
  public static final String ROUTE_KEY_REDPACKET = "redPacketRoute";
  // 秒杀实现2
  public static final String ROUTE_KEY_REDPACKET_2 = "redPacketRoute2";

  /*
   * @Bean public ConnectionFactory rabbitConnectionFactory(@Value("${rabbitmq.host}") String host,
   * 
   * @Value("${rabbitmq.username}") String username,
   * 
   * @Value("${rabbitmq.password}") String password) { CachingConnectionFactory connectionFactory =
   * new CachingConnectionFactory(host); connectionFactory.setUsername(username);
   * connectionFactory.setPassword(password); return connectionFactory; }
   */
  @Bean
  public ConnectionFactory rabbitConnectionFactory() {
    // TODO
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory("39.106.115.236");
    connectionFactory.setPort(Integer.valueOf(443));

    connectionFactory.setUsername("guest");
    connectionFactory.setPassword("guest");

    return connectionFactory;
  }

  /**
   * 创建rabbitAdmin代理类
   *
   * @return
   */
  @Bean
  public AmqpAdmin amqpAdmin(ApplicationContext context) {
    return new RabbitAdmin(context.getBean("rabbitConnectionFactory", ConnectionFactory.class));
  }

  @Bean
  public AmqpTemplate rabbitTemplate(ApplicationContext context) {
    RabbitTemplate template =
        new RabbitTemplate(context.getBean("rabbitConnectionFactory", ConnectionFactory.class));
    RetryTemplate retryTemplate = new RetryTemplate();
    ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
    retryTemplate.setBackOffPolicy(backOffPolicy);
    template.setRetryTemplate(retryTemplate);
    return template;
  }

  /**
   * 创建默认topic交换器
   *
   * @return
   */
  @Bean
  public TopicExchange topicExchange() {
    return new TopicExchange(DEFAULT_EXCHAGE, true, false);
  }

  /**
   * 创建红包消息队列
   *
   * @return
   */
  @Bean
  public Queue scheduleQueue() {
    return new Queue(QUQUE_REDPACKET);
  }


  /**
   * 消费者配置
   * 
   * @param context
   * @return
   */
  @Bean
  public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
      ApplicationContext context) {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    factory
        .setConnectionFactory(context.getBean("rabbitConnectionFactory", ConnectionFactory.class));
    factory.setConcurrentConsumers(3);
    factory.setMaxConcurrentConsumers(10);
    return factory;
  }
}
