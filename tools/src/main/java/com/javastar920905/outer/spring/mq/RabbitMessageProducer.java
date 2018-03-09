package com.javastar920905.outer.spring.mq;

import java.util.Random;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javastar920905.config.RabbitConfig;

/**
 * Rabbit消息发送端
 */
@Component
public class RabbitMessageProducer {

  private static final Integer HIGH_PRIORITY = 10;

  private static final Integer LOW_PRIORITY = 5;

  @Autowired
  private AmqpTemplate amqpTemplate;

  public void sendMessage(String message, String routingKey) {
    amqpTemplate.convertAndSend(RabbitConfig.DEFAULT_EXCHAGE, routingKey, message);
  }

  /**
   * 发送固定级别的高优先级队列消息
   * 
   * @date 2017/4/25
   */
  public void sendChangelessHighPriorityMessage(String message, String routingKey) {
    MessagePostProcessor messagePostProcessor = dealMessage -> {
      dealMessage.getMessageProperties().setPriority(HIGH_PRIORITY);
      return dealMessage;
    };
    amqpTemplate.convertAndSend(RabbitConfig.DEFAULT_EXCHAGE, routingKey, message,
        messagePostProcessor);
  }

  /**
   * 发送随机级别的低优先级队列
   * 
   * @date 2017/4/25
   */
  public void sendRandomLowPriorityMessage(String message, String routingKey) {
    MessagePostProcessor messagePostProcessor = dealMessage -> {
      Integer priority = new Random().nextInt(LOW_PRIORITY);
      dealMessage.getMessageProperties().setPriority(priority);
      return dealMessage;
    };
    amqpTemplate.convertAndSend(RabbitConfig.DEFAULT_EXCHAGE, routingKey, message,
        messagePostProcessor);
  }

  public Object sendMessageAndReceive(String message, String routingKey) {
    return amqpTemplate.convertSendAndReceive(RabbitConfig.DEFAULT_EXCHAGE, routingKey, message);
  }
}
