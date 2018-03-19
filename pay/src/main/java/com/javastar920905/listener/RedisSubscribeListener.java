package com.javastar920905.listener;

import static net.logstash.logback.marker.Markers.append;
import static net.logstash.logback.marker.Markers.appendArray;

import java.io.UnsupportedEncodingException;

import com.javastar920905.constant.CommonConstants;
import com.javastar920905.outer.spring.SpringContextUtil;
import com.javastar920905.service.pay.IRedPacketService;
import com.javastar920905.util.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.javastar920905.config.RedisConfig;


/**
 * @author ouzhx
 */
@Component
public class RedisSubscribeListener implements MessageListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(RedisSubscribeListener.class);

  /**
   * 有消息丢失可能(重复消费?)
   * 
   * @param message
   * @param pattern
   */
  @Override
  public void onMessage(Message message, byte[] pattern) {
    try {
      String channel = new String(message.getChannel(), CommonConstants.Charset.utf8.value);
      if (StringUtils.equalsIgnoreCase(RedisConfig.CHANNEL_TOPIC_RED_PACKET, channel)) {
        JSONObject json = (JSONObject) BeanUtil.toObject(message.getBody());
        IRedPacketService redPacketService = SpringContextUtil.getBean(IRedPacketService.class);
        JSONObject result = redPacketService.oepnRedPacket(json.getString("openId"),
            json.getString("nickName"), json.getString("redPacketId"));
        LOGGER.info(result.toJSONString());
      } else {
        String msg = new String(message.getBody(), CommonConstants.Charset.utf8.value);
        LOGGER.warn("有新的未处理渠道消息:{}", msg);
      }

    } catch (UnsupportedEncodingException e) {
      LOGGER.error(appendArray("params", message.getBody()).and(append("errType", e.toString())),
          "redis订阅 转码异常!", e);
    }
  }

}
