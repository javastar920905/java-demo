package com.javastar920905.service;

import com.alibaba.fastjson.JSONObject;
import com.javastar920905.entity.domain.RedPacket;

/**
 * 红包服务接口
 * 
 * @author ouzhx on 2018/3/7.
 */
public interface IRedPacketService extends IBaseService {
  /** 红包key前缀 **/
  String REDIS_KEY_RED_PACKET_PREFIX = "redPacket:";
  /** 红包后缀 **/
  String REDIS_KEY_RED_PACKET_SUFFIX_SIZE = ":size";
  String REDIS_KEY_RED_PACKET_SUFFIX_DETAIL = ":detail";
  String REDIS_KEY_RED_PACKET_SUFFIX_QUEUE = ":queue";

  /**
   * 获取红包库存key
   * 
   * @param redPacketId
   * @return
   */
  default byte[] getRedPacketKey(String redPacketId) {
    return (REDIS_KEY_RED_PACKET_PREFIX + redPacketId).getBytes();
  }

  /**
   * 获取红包数量key
   * 
   * @param redPacketId
   * @return
   */
  default byte[] getRedPacketSizeKey(String redPacketId) {
    return (REDIS_KEY_RED_PACKET_PREFIX + redPacketId + REDIS_KEY_RED_PACKET_SUFFIX_SIZE)
        .getBytes();
  }

  /**
   * 获取红包领取详情key
   * 
   * @param redPacketId
   * @return
   */
  default byte[] getRedPacketDetailKey(String redPacketId) {
    return (REDIS_KEY_RED_PACKET_PREFIX + redPacketId + REDIS_KEY_RED_PACKET_SUFFIX_DETAIL)
        .getBytes();
  }

  /**
   * 获取抢红包队列key
   *
   * @return
   */
  default byte[] getRedPacketQueueKey(String redPacketId) {
    return (REDIS_KEY_RED_PACKET_PREFIX + redPacketId + REDIS_KEY_RED_PACKET_SUFFIX_QUEUE)
        .getBytes();
  }

  /**
   * 发红包 (添加红包记录,生成库存信息到reids)
   * 
   * @return
   */
  JSONObject giveRedPacket(RedPacket redPacket) throws Exception;


    //<editor-fold desc="redis 秒杀实现1  打开红包时,redis加锁时有遗漏消息的情况">
    /**
   * 抢红包(内存中实现速度快) (生成排队队列--最后只有少量有用请求进入到后台,并发转单线程) 减少内存库存数
   * 
   * @param openId 微信标识
   * @param redPacketId 红包Id
   * @return
   */
  JSONObject bookingRedPacket(String openId, String nickName, String redPacketId);


  /**
   * 拆红包(如果要记录头像,还需记录头像url)
   * 
   * (秒杀实现1 余额超时退还 依赖度较高)
   * 
   * 进入拆红包排队队列的只是少量的正常请求,正常使用数据库事务处理即可
   * 
   * @return
   */
  JSONObject oepnRedPacket(String openId, String nickName, String redPacketId);
    //</editor-fold>



    //<editor-fold desc="redis 秒杀实现2  使用redis双队列方式(库存队列+真实请求队列方式处理) (推荐,保证了数据一致性)">
    /**
   * 使用库存队列 和真实请求队列方式处理 (推荐,保证了数据一致性)
   * 
   * @return
   */
  public JSONObject bookingRedPacket2WithDoubleQuque(String openId, String nickName,
      String redPacketId);

  /**
   * 秒杀实现2
   * 
   * @param openId
   * @param nickName
   * @param redPacketId
   * @return
   */
  public JSONObject oepnRedPacket2WithDoubleQuque(String openId, String nickName,
      String redPacketId);
    //</editor-fold>
}
