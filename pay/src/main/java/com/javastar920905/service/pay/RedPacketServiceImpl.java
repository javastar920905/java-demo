package com.javastar920905.service.pay;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.javastar920905.config.RabbitConfig;
import com.javastar920905.config.RedisConfig;
import com.javastar920905.constant.CommonConstants;
import com.javastar920905.outer.spring.mq.RabbitMessageProducer;
import com.javastar920905.util.RedPacketUtil;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.javastar920905.entity.domain.RedPacket;
import com.javastar920905.entity.domain.RedPacketDetail;
import com.javastar920905.outer.JSONUtil;
import com.javastar920905.outer.redis.RedisFactory;
import com.javastar920905.outer.spring.SpringContextUtil;
import com.javastar920905.util.BeanUtil;
import com.javastar920905.util.ByteUtil;
import com.javastar920905.util.MoneyUtil;
import org.springframework.util.CollectionUtils;


/**
 * @author ouzhx on 2018/3/7.
 */
@Service
public class RedPacketServiceImpl extends BaseService implements IRedPacketService {

  /**
   * 发红包 (添加红包记录,生成库存信息到reids)
   *
   * TODO 2 需要服务费计算接口 2 金额体现接口
   *
   * (红包类型,随机红包/均分红包,可以在生成库存后直接生成金额)
   *
   * @param redPacket
   * @return
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public JSONObject giveRedPacket(RedPacket redPacket) throws Exception {

    // TODO 添加数据校验
    // 1 生成订单信息,
    RLock lock = null;
    RedissonClient redissonClient = SpringContextUtil.getBean(RedissonClient.class);
    lock = redissonClient.getLock("oepnRedPacket:lock");
    if (lock.tryLock(1, 5, TimeUnit.SECONDS)) {
      redPacket.setRestMoney(redPacket.getMoney());
      Integer result = redPacketMapper.insertAllColumn(redPacket);
      if (result > 0) {
        // 2 todo 调用微信支付api,下红包订单,等待微信回调
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
              connection.incrBy(getRedPacketSizeKey(redPacket.getId()), redPacket.getPacketSize());

              // 循环生成库存信息
              for (int i = 1; i <= redPacket.getPacketSize(); i++) {
                connection.rPush(getRedPacketKey(redPacket.getId()), "1".getBytes());
              }
              // todo 默认一天过期 int expire = 60 * 60 * 24;
            } catch (DataAccessException e) {
              e.printStackTrace();
            } finally {
              if (connection != null) {
                connection.close();
              }
            }
            lock.unlock();
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
    } else {
      return buildfailResult("加锁失败,请稍后再试!");
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
    RedisConnection connection = null;
    try {
      connection = RedisFactory.getConnection();
      // 排队队列解决重复消费问题 ,判断没有进入过抢购结果队列?接着执行库存减1，写入数据库，将此user_id用户同时也进入结果队列。:(已经抢过了);


      // 1 因为列表的pop操作是原子的，即使有很多用户同时到达，也是依次执行的 (但是没有避免重复消费问题,可以在打开红包那里处理)
      if (connection.lPop(getRedPacketKey(redPacketId)) != null) {
        // 2 抢红包成功
        JSONObject userJson = new JSONObject();
        userJson.put("openId", openId);
        userJson.put("nickName", nickName);
        userJson.put("redPacketId", redPacketId);
        // 3 请求进入队列 (进入拆红包队列(推送队列rabbitmq 或者redis 发布订阅--可能出现消费不完整情况))
        // todo 24 小时未领取完 退回余额(24 小时后不再显示流水)
        // connection.rPush(getRedPacketQueueKey(redPacketId), openId.getBytes());
        // connection.publish(RedisConfig.CHANNEL_TOPIC_RED_PACKET.getBytes(), userInfo);
        /*
         * SpringContextUtil.getBean(RabbitMessageProducer.class).sendMessage(userJson.toJSONString(
         * ), RabbitConfig.ROUTE_KEY_REDPACKET);
         */
        return buildSuccessResult("成功进入到抢红包队列");
      } else {
        // 队列消费完又没有成功打开红包时(用户看到红包没有被领取完,红包也可能被重新转发等...),抢红包失败
        // todo 24 小时未领取完 退回余额(24 小时后不再显示流水)
        return buildfailResult("sorry! 红包已经被抢完.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
    return buildfailResult("sorry! 抢红包失败.");
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
    RLock lock = null;

    try {
      connection = RedisFactory.getConnection();

      // 1 加入分布式锁,保证操作原子性
      RedissonClient redissonClient = SpringContextUtil.getBean(RedissonClient.class);
      lock = redissonClient.getLock("oepnRedPacket:lock");
      /// 尝试加锁，最多等待1秒，上锁以后5秒自动解锁
      if (lock.tryLock(5, 3, TimeUnit.SECONDS)) {
        // 红包库存key
        byte[] redPacketKey = getRedPacketKey(redPacketId);
        // 剩余红包数key
        byte[] redPacketSizeKey = getRedPacketSizeKey(redPacketId);


        RedPacketDetail queryDetail = new RedPacketDetail();
        queryDetail.setRedPacketId(redPacketId);
        queryDetail.setOepnId(openId);
        if (redPacketDetailMapper.selectOne(queryDetail) != null) {
          // 2 避免重复领取(处理重复消费问题),恢复一个库存
          connection.rPush(redPacketKey, "1".getBytes());
          return buildfailResult("您已经领取过该红包,不能重复领取.");
        } else {
          // 3 开始拆红包,查询红包,生成随机金额
          RedPacket redPacket = redPacketMapper.selectById(redPacketId);

          // 4 避免超额消费
          if (redPacket.getRestMoney() > 0) {
            double randMoney = 0;
            byte[] size = connection.get(redPacketSizeKey);
            if (size != null && Integer.valueOf(ByteUtil.getString(size)) > 1) {
              // 生成红包金额,如果是最后一个红包则是剩余金额(要求每个人至少0.01)
              randMoney = Double.parseDouble(MoneyUtil.numberDown(MoneyUtil.nextDouble(0.01,
                  redPacket.getRestMoney() - (redPacket.getPacketSize() * 0.01))));
            } else {
              randMoney = redPacket.getRestMoney();
            }

            // 5 扣除余额
            RedPacket updateRedPacket = new RedPacket();
            updateRedPacket.setId(redPacket.getId());
            updateRedPacket.setRestMoney(redPacket.getRestMoney() - randMoney);
            int ur = redPacketMapper.updateById(updateRedPacket);
            if (ur > 0) {
              // 6 记录拆红包记录
              RedPacketDetail detail = new RedPacketDetail();
              detail.setMoney(randMoney);
              detail.setOepnId(openId);
              detail.setRedPacketId(redPacketId);
              detail.setCreateDate(new Timestamp(System.currentTimeMillis()));
              detail.setNickName(nickName);
              int detailResult = redPacketDetailMapper.insert(detail);
              if (detailResult > 0) {
                // 7 提供缓存实时领取记录
                connection.rPush(getRedPacketDetailKey(redPacketId), BeanUtil.toByteArray(detail));
                connection.decr(redPacketSizeKey);
                // todo 记录key 24小时后时效(优化处理)
                return buildSuccessResult("恭喜您领取到红包:" + randMoney);
              }
            }
          }
        }
        lock.unlock();
      } else {
        connection.rPush(getRedPacketKey(redPacketId), "1".getBytes());
        return buildfailResult("加锁失败,恢复库存信息");
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
    return buildfailResult("领取红包异常!");
  }



  /*******************************************************************************************************/

  /**
   * 抢红包实现方式2
   *
   * @param openId 微信标识
   * @param redPacketId 红包Id
   * @return
   */
  @Override
  public JSONObject bookingRedPacket2WithDoubleQuque(String openId, String nickName,
      String redPacketId) {
    RedisConnection connection = null;
    RLock lock = null;
    try {
      connection = RedisFactory.getConnection();
      RedissonClient redissonClient = SpringContextUtil.getBean(RedissonClient.class);
      // 锁住指定红包
      lock = redissonClient.getLock("redPacket:lock:" + redPacketId);

      // 尝试加锁，最多等待5秒，上锁以后5秒自动解锁(避免死锁)
      if (lock.tryLock(5, 5, TimeUnit.SECONDS)) {
        byte[] queueResultKey = getRedPacketQueueResultKey(redPacketId);
        // 避免重复领取(加入分布式锁,保证操作原子性)
        if (!connection.sIsMember(queueResultKey, openId.getBytes())) {

          // 1 成功抢到一个红包,减少一个库存
          if (connection.lPop(getRedPacketKey(redPacketId)) != null) {

            // 2 开始拆红包
            JSONObject resultJson = oepnRedPacket2(openId, nickName, redPacketId, connection);
            if (resultJson.getBooleanValue(CommonConstants.key.result.name())) {
              // 3 抢红包成功,添加到领取结果列表
              connection.sAdd(queueResultKey, openId.getBytes());
            }
            lock.unlock();
            return resultJson;
          } else {
            lock.unlock();
            return buildfailResult("sorry! 红包已经被抢完.");
          }
        } else {
          lock.unlock();
          return buildfailResult("sorry! 您已经抢过该红包.");
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null && !connection.isClosed()) {
        connection.close();
      }
    }
    return buildfailResult("sorry! 抢红包失败.");
  }

  /**
   * 拆红包2
   *
   * @param openId
   * @param redPacketId
   * @return
   */
  @Transactional(rollbackFor = Exception.class)
  @Override
  public JSONObject oepnRedPacket2(String openId, String nickName, String redPacketId,
      RedisConnection connection) {
    // 剩余红包数key
    byte[] redPacketSizeKey = getRedPacketSizeKey(redPacketId);

    // 1 开始拆红包,查询红包,生成随机金额
    RedPacket redPacket = redPacketMapper.selectById(redPacketId);

    // 2 避免超额消费
    if (redPacket.getRestMoney() > 0) {
      double randMoney = 0;
      byte[] size = connection.get(redPacketSizeKey);
      if (size != null) {
        // 生成红包金额,如果是最后一个红包则是剩余金额(要求每个人至少0.01)
        int restSize = Integer.valueOf(ByteUtil.getString(size));
        if (restSize > 1) {
          randMoney = Double.parseDouble(MoneyUtil.numberDown(
              MoneyUtil.nextDouble(0.01, redPacket.getRestMoney() - (restSize * 0.01))));
        } else {
          randMoney = redPacket.getRestMoney();
        }
      }

      // 3 扣除余额
      RedPacket updateRedPacket = new RedPacket();
      updateRedPacket.setId(redPacket.getId());
      updateRedPacket.setRestMoney(redPacket.getRestMoney() - randMoney);
      int ur = redPacketMapper.updateById(updateRedPacket);
      if (ur > 0) {
        // 4 记录拆红包记录
        RedPacketDetail detail = new RedPacketDetail();
        detail.setMoney(randMoney);
        detail.setOepnId(openId);
        detail.setRedPacketId(redPacketId);
        detail.setCreateDate(new Timestamp(System.currentTimeMillis()));
        detail.setNickName(nickName);
        int detailResult = redPacketDetailMapper.insert(detail);
        if (detailResult > 0) {
          // 5 减少库存数
          connection.decr(redPacketSizeKey);
          // todo 记录key 24小时后失效(优化处理)
          return buildSuccessResult("恭喜您领取到红包:" + randMoney);
        }
      }
    }
    return buildfailResult("领取红包异常!");
  }

  /**
   * 获取红包领取详情列表
   *
   * @param redPacketId
   * @return
   */
  @Override
  /**
   * TODO 默认缓存key以"方法名+参数值"区分 (getRedPacketDetailList+参数1值)
   */
  @Cacheable(value = RedisConfig.Cachekey.CACHE_REDPACKET_DETAIL, key = "#root.methodName+#p0")
  public JSONObject getRedPacketDetailList(String redPacketId) {
    JSONObject detailJson = new JSONObject();
    EntityWrapper<RedPacketDetail> detailEntityWrapper = new EntityWrapper();
    detailEntityWrapper.setEntity(new RedPacketDetail());
    detailEntityWrapper.where("red_packet_id={0}", redPacketId);
    List<RedPacketDetail> detailList = redPacketDetailMapper.selectList(detailEntityWrapper);

    JSONArray resultArray = new JSONArray();
    if (detailList != null) {

      // TODO 判断红包已经抢完后,计算手气最佳 迭代代码直接参考 Collections.max(detailList)
      if (RedPacketUtil.getRedPacketRemain(redPacketId).get() == 0) {
        Iterator<RedPacketDetail> i = detailList.iterator();
        RedPacketDetail candidate = i.next();
        resultArray.add(parseRedPacketDetailToJson(candidate));

        while (i.hasNext()) {
          RedPacketDetail next = i.next();
          resultArray.add(parseRedPacketDetailToJson(next));
          if (next.getMoney().compareTo(candidate.getMoney()) > 0) {
            candidate = next;
          }
        }
        // 手气最佳,设置lucky 字段
        int index = detailList.indexOf(candidate);
        JSONObject luckJson = JSONUtil.parseObjectToJSONObject(candidate, null);
        luckJson.put("lucky", true);
        resultArray.set(index, luckJson);
      } else {
        detailList.stream().forEach(item -> resultArray.add(parseRedPacketDetailToJson(item)));
      }
      detailJson.put("detailList", resultArray);
    } else {
      detailJson.put("detailList", null);
    }

    detailJson.put("detailList", detailList);
    return detailJson;
  }

  private JSONObject parseRedPacketDetailToJson(RedPacketDetail redPacketDetail) {
    JSONObject luckJson = JSONUtil.parseObjectToJSONObject(redPacketDetail, null);
    // todo 加载用户头像,昵称信息
    return luckJson;
  }
}
