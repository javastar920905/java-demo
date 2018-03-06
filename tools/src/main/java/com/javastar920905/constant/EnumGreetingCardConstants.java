package com.javastar920905.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * 贺卡统计常量
 */
public interface EnumGreetingCardConstants {
  /**
   * 需要更新排名数据的贺卡Id的redisKey
   */
  String REDISKEY_RANKING_GREETING_CARD_ID = "cms:ranking:greetingCardId";

  @Getter
  @AllArgsConstructor
  @Accessors(chain = true)
  enum OperationType {
    /**
     * 操作类型
     */
    play("播放", 10), like("点赞", 20), forward("转发", 30);

    private String desc;
    private int value;
  }

  @Getter
  @AllArgsConstructor
  @Accessors(chain = true)
  enum Status {
    /**
     * 贺卡状态
     */
    normal("正常状态", 0), deleted("已删除", -1);

    private String desc;
    private int value;

  }


}
