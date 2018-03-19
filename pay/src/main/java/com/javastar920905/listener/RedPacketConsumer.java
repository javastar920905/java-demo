package com.javastar920905.listener;

import com.alibaba.fastjson.JSONObject;
import com.javastar920905.outer.spring.SpringContextUtil;
import com.javastar920905.service.pay.IRedPacketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;

import com.javastar920905.config.RabbitConfig;

/**
 * 打开红包消费对你
 */
@Component
public class RedPacketConsumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(RedPacketConsumer.class);


  @RabbitListener(
      bindings = @QueueBinding(
          value = @Queue(value = RabbitConfig.QUQUE_REDPACKET, durable = "true"),
          exchange = @Exchange(value = RabbitConfig.DEFAULT_EXCHAGE, type = "topic",
              durable = "true", ignoreDeclarationExceptions = "true"),
          key = RabbitConfig.ROUTE_KEY_REDPACKET))
  public void handleMessage(String msg) {
    LOGGER.info("来自消息队列" + msg);
    JSONObject json = JSONObject.parseObject(msg);
    try {
      IRedPacketService redPacketService = SpringContextUtil.getBean(IRedPacketService.class);
      JSONObject result = redPacketService.oepnRedPacket(json.getString("openId"),
          json.getString("nickName"), json.getString("redPacketId"));
      LOGGER.info(result.toJSONString());
    } catch (BeansException e) {
      LOGGER.warn("队列消费失败库存信息" + msg);
    }
  }


  @RabbitListener(
      bindings = @QueueBinding(
          value = @Queue(value = RabbitConfig.QUQUE_REDPACKET, durable = "true"),
          exchange = @Exchange(value = RabbitConfig.DEFAULT_EXCHAGE, type = "topic",
              durable = "true", ignoreDeclarationExceptions = "true"),
          key = RabbitConfig.ROUTE_KEY_REDPACKET_2))
  public void handleMessage2(String msg) {
    LOGGER.info("来自消息队列2 " + msg);
    JSONObject json = JSONObject.parseObject(msg);
    try {
      IRedPacketService redPacketService = SpringContextUtil.getBean(IRedPacketService.class);
      JSONObject result = redPacketService.oepnRedPacket2WithDoubleQuque(json.getString("openId"),
          json.getString("nickName"), json.getString("redPacketId"));
      LOGGER.info(result.toJSONString());
    } catch (BeansException e) {
      LOGGER.warn("队列消费失败库存信息" + msg);
    }
  }
}
