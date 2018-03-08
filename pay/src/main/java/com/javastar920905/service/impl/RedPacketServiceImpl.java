package com.javastar920905.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import com.javastar920905.config.RedisConfig;
import com.javastar920905.entity.domain.RedPacketDetail;
import com.javastar920905.mapper.RedPacketMapper;
import com.javastar920905.outer.JSONUtil;
import com.javastar920905.util.BeanUtil;
import com.javastar920905.util.MoneyUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.javastar920905.entity.domain.RedPacket;
import com.javastar920905.outer.redis.RedisFactory;
import com.javastar920905.service.IRedPacketService;


/**
 * @author ouzhx on 2018/3/7.
 */
@Service
public class RedPacketServiceImpl extends BaseService implements IRedPacketService {

  /**
   * 发红包 (添加红包记录,生成库存信息到reids)
   *
   * @param redPacket
   * @return
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public JSONObject giveRedPacket(RedPacket redPacket) {
    // 1 生成订单信息,
    redPacket.setRestMoney(redPacket.getMoney());
    int result = redPacketMapper.insertAllColumn(redPacket);
    if (result > 0) {
      // 2 调用微信支付api,下红包订单,等待微信回调
      int orderStatu = 1;
      if (orderStatu == 1) {
        // 3 修订订单状态为已支付
        RedPacket updateRedPacket = new RedPacket();
        updateRedPacket.setId(redPacket.getId());
        updateRedPacket.setExpireTime(new Timestamp(System.currentTimeMillis()));
        int ur = redPacketMapper.updateById(updateRedPacket);
        // 4 生成红包信息到redis
        if (ur > 0) {
          RedisConnection connection = RedisFactory.getConnection();
          try {
            // 默认一天过期
            int expire = 60 * 60 * 24;
            byte[] size = (redPacket.getPacketSize() + "").getBytes();
            connection.setEx(getRedPacketSizeKey(redPacket.getId()), expire, size);

            // 循环生成库存信息
            for (int i = 1; i <= redPacket.getPacketSize(); i++) {
              connection.rPush(getRedPacketKey(redPacket.getId()), "1".getBytes());
            }
          } catch (DataAccessException e) {
            e.printStackTrace();
          } finally {
            if (connection != null) {
              connection.close();
            }
          }
          return buildSuccessResult(
              "发送红包成功!" + JSONUtil.parseObjectToJSONObject(redPacket, null).toJSONString());
        } else {
          return buildfailResult("已扣款,但是生成红包信息失败");
        }
      } else {
        // 记录异常日志信息
        return buildfailResult("微信支付接口异常!");
      }
    } else {
      return buildfailResult("系统异常,发送红包失败!");
    }
  }

  /**
   * 抢红包 (生成排队队列,并发转单线程)
   *
   * @param openId 微信标识
   * @param redPacketId 红包Id
   * @return
   */
  @Override
  public JSONObject bookingRedPacket(String openId, String nickName, String redPacketId) {
    // 24 小时未领取完 退回余额(24 小时后不再显示流水)
    RedisConnection connection = null;
    try {
      connection = RedisFactory.getConnection();
      // 一个红包不能重复领取
      byte[] queueKey = getRedPacketQueueKey(redPacketId);
      List<String> userIdList = BeanUtil.toListString(connection.lRange(queueKey, 0, -1));
      if (userIdList.contains(openId)) {
        return buildfailResult("已经抢到过该红包,不能重复领取");
      }
      // 因为列表的pop操作是原子的，即使有很多用户同时到达，也是依次执行的
      if (connection.lPop(getRedPacketKey(redPacketId)) != null) {
        // 抢红包成功,进入拆红包队列(推送队列rabbitmq 或者redis 发布订阅)
        JSONObject userJson = new JSONObject();
        userJson.put("openId", openId);
        userJson.put("nickName", nickName);
        userJson.put("redPacketId", redPacketId);
        byte[] userInfo = BeanUtil.toByteArray(userJson);
        // 请求进入队列
        connection.rPush(queueKey, openId.getBytes());
        connection.publish(RedisConfig.CHANNEL_TOPIC_RED_PACKET.getBytes(), userInfo);
        return buildSuccessResult("成功进入到抢红包队列");
      } else {
        // 抢红包失败
        return buildfailResult("sorry! 红包已经被抢完.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }

  /**
   * 拆红包
   *
   * @param openId
   * @param redPacketId
   * @return
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public JSONObject oepnRedPacket(String openId, String nickName, String redPacketId) {

    // 24 小时未领取完 退回余额(24 小时后不再显示流水)
    RedisConnection connection = null;

    byte[] redPacketKey = getRedPacketKey(redPacketId);

    try {
      // 加入redis watch 保证原子性 https://www.jianshu.com/p/361cb9cd13d5
      // doxxx 非原子性操作 val = GET mykey;val = val + 1;SET mykey $val
      connection = RedisFactory.getConnection();
      connection.watch(redPacketKey);
      connection.multi();
      // 开始拆红包,查询红包,生成随机金额
      RedPacket redPacket = redPacketMapper.selectById(redPacketId);
      if (redPacket.getRestMoney() > 0) {
        double randMoney = 0;
        if (connection.lLen(redPacketKey) != null && connection.lLen(redPacketKey) > 1) {
          // 生成红包金额,如果是最后一个红包则是剩余金额(要求每个人至少0.01)
          randMoney = Double.parseDouble(MoneyUtil.numberDown(MoneyUtil.nextDouble(0.01,
              redPacket.getRestMoney() - (redPacket.getPacketSize() * 0.01))));
        } else {
          randMoney = redPacket.getRestMoney();
        }

        // 避免重复领取
        RedPacketDetail queryDetail = new RedPacketDetail();
        queryDetail.setRedPacketId(redPacketId);
        queryDetail.setOepnId(openId);
        if (redPacketDetailMapper.selectOne(queryDetail) == null) {
          // 扣除余额,记录拆红包记录
          RedPacket updateRedPacket = new RedPacket();
          updateRedPacket.setId(redPacket.getId());
          updateRedPacket.setRestMoney(redPacket.getRestMoney() - randMoney);
          int ur = redPacketMapper.updateById(updateRedPacket);
          if (ur > 0) {
            RedPacketDetail detail = new RedPacketDetail();
            detail.setMoney(randMoney);
            detail.setOepnId(openId);
            detail.setRedPacketId(redPacketId);
            detail.setCreateDate(new Timestamp(System.currentTimeMillis()));
            detail.setNickName(nickName);
            int detailResult = redPacketDetailMapper.insert(detail);
            if (detailResult > 0) {
              // 提供实时领取记录
              connection.rPush(getRedPacketDetailKey(redPacketId), BeanUtil.toByteArray(detail));
              connection.exec();
              // todo 记录key 24小时后时效
              return buildSuccessResult("恭喜您领取到红包:" + randMoney);
            }
          }
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
    return buildfailResult("领取红包异常!");
  }

}
