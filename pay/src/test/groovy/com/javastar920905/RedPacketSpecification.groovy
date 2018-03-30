package com.javastar920905

import cn.hutool.json.JSONArray
import com.alibaba.fastjson.JSONObject
import com.baomidou.mybatisplus.mapper.EntityWrapper
import com.javastar920905.config.RedisConfig
import com.javastar920905.constant.CommonConstants
import com.javastar920905.entity.domain.RedPacket
import com.javastar920905.entity.domain.RedPacketDetail
import com.javastar920905.outer.redis.RedisFactory
import com.javastar920905.thread.RobThread
import com.javastar920905.util.BeanUtil
import org.springframework.data.redis.connection.RedisConnection
import spock.lang.Ignore
import spock.lang.IgnoreRest
import spock.lang.See
import spock.lang.Shared
import spock.lang.Stepwise
import spock.lang.Title

import java.sql.Timestamp
import java.util.concurrent.CountDownLatch

/**
 * @author ouzhx on 2018/3/19.
 */
@See("com.javastar920905.DetachedJavaConfig 查看主配置的参考信息")
@Title("红包功能集成测试")
@Stepwise
class RedPacketSpecification extends BaseDetachedConfig {
    @Shared
    RedisConnection connection = null

    def setup() {
        connection = RedisFactory.getConnection()
        assert connection != null
    }

    def cleanup() {
        if (connection != null) {
            connection.close()
        }
    }

    def "发红包功能测试"() {
        if (initDB) {
            // "删除当前数据库db0的所有key,确认redis中没有脏数据"
            connection.flushDb()
            assert connection.exists(redPacketService.getRedPacketKey(redPacketId)) == false
        }
        given: "设置红包信息(金额,数目等)"
        RedPacket redPacket = new RedPacket()
        redPacket.setPacketSize(size)
        redPacket.setId(redPacketId)
        redPacket.setUserId(userId)
        redPacket.setMoney(money)
        redPacket.setCreateDate(createDate)
        redPacket.setExpireTime(expireTime)

        when: "发送红包"
        JSONObject jsonObject = redPacketService.giveRedPacket(redPacket)

        then: "发送红包成功,生成库存信息!"
        assert connection.get(redPacketService.getRedPacketSizeKey(redPacket.getId())) != null
        assert connection.lLen(redPacketService.getRedPacketKey(redPacket.getId())) == size
        assert jsonObject.getBoolean(CommonConstants.key.result.name()) == true
        assert connection.exists(redPacketService.getRedPacketKey(redPacketId)) == true
        RedPacket dbRedPacket = redPacketMapper.selectById(redPacketId)
        assert dbRedPacket != null
        assert dbRedPacket.getPacketSize() == size
        assert dbRedPacket.getMoney() == money

        //mock打桩 stub 以及验证调用次数(很多时候需要在then:代码块后面才生效)
        /*with(redPacketMapper) {
            1 * insertAllColumn(_) >> 1
            1 * updateById(_) >> 1
        }*/

        where: "给出以下红包信息"
        initDB | redPacketId | userId  | money | size | createDate                                | expireTime
        true   | "1"         | "ouzhx" | 10d   | 10   | new Timestamp(System.currentTimeMillis()) | new Timestamp(System.currentTimeMillis() + 1000000000)
        false  | "2"         | "ouzhx" | 5d    | 5    | new Timestamp(System.currentTimeMillis()) | new Timestamp(System.currentTimeMillis() + 1000000000)
    }

    @Ignore("TODO ")
    def "抢红包功能测试-异常回滚"() {

    }

    def "抢红包功能测试-不论多少人抢都能保证金额一致性"() {
        assert redPacketId != null
        when: "开始抢红包"
        CountDownLatch countDownLatch = new CountDownLatch(robNums);
        for (int i = 0; i < robNums; i++) {
            final String uid = "" + i
            Thread thread = new RobThread(uid, redPacketId, countDownLatch, redPacketService)
            thread.start()
        }

        try {
            countDownLatch.await()
        } catch (InterruptedException e) {
            e.printStackTrace()
        }

        then: "验证红包结果"
        //一个红包只能同一个人被消费一次
        RedPacketDetail queryDetail = new RedPacketDetail();
        queryDetail.setRedPacketId(redPacketId);
        queryDetail.setOepnId(userId)
        try {
            redPacketDetailMapper.selectOne(queryDetail)
        } catch (Exception e) {
            assert 1 == 2
        }

        // 4 避免超额消费
        RedPacket redPacket = redPacketMapper.selectById(redPacketId);
        if (redPacket.getRestMoney() < 0) {
            assert 1 == 2
        }
        EntityWrapper<RedPacketDetail> detailEntityWrapper = new EntityWrapper()
        detailEntityWrapper.setEntity(new RedPacketDetail())
        detailEntityWrapper.where("red_packet_id={0}", redPacketId)
        List<RedPacketDetail> detailList = redPacketDetailMapper.selectList(detailEntityWrapper)
        long sumMoeny = 0
        if (detailList != null && detailList.size() > 0) {
            for (RedPacketDetail detail : detailList) {
                sumMoeny = detail.getMoney() + sumMoeny
            }
            //已抢红包金额永远<=总金额
            assert sumMoeny <= redPacket.getMoney()
        }

        byte[] size = connection.get(redPacketService.getRedPacketSizeKey(redPacketId))
        //剩余红包数
        int redPacketSize = BeanUtil.byte2Int(size)
        //已抢红包数
        Long queueResultSize = connection.sCard(redPacketService.getRedPacketQueueResultKey(redPacketId))
        if (redPacketSize == 0) {
            //红包抢完的情况(红包余额0,库存空,,消费数==红包数)
            assert redPacket.getRestMoney() == 0
            assert connection.exists(redPacketService.getRedPacketKey(redPacketId)) == false
            //总红包数=已抢红包数
            assert queueResultSize == redPacket.getPacketSize()
        } else {
            //红包没有抢完的情况
            if (queueResultSize == null) {
                queueResultSize = 0
            }
            //总红包数-已抢红包数=剩余红包
            assert (redPacket.getPacketSize() - queueResultSize) == redPacketSize
        }

        where: "给出以下红包信息"
        userId       | redPacketId | robNums
        "ouzhx-rob1" | "1"         | 5
        "ouzhx-rob1" | "1"         | 10
        "ouzhx-rob1" | "2"         | 50
    }

    @IgnoreRest
    def "红包领取详情+缓存"() {
        when: "给出模拟红包数据"
        def redPacketId = "1"
        byte[] redPacketDetailKey = (RedisConfig.Cachekey.CACHE_REDPACKET_DETAIL + redPacketId).getBytes();
        //sql添加需要的数据
        sql.execute("INSERT INTO `red_packet_detail`(`id`, `red_packet_id`, `oepn_id`,`money`,`create_date`)"
                + "VALUES ('1', '1','openId1',0.01, '2018-03-27 11:26:05')," +
                "('2', '1','openId1',0.01,'2018-03-27 11:26:05')" +
                ";");
        then: "清除redis 缓存"
        connection.flushDb()
        assert connection.exists(redPacketDetailKey) == false

        then: "执行数据查询"
        JSONObject detailList = redPacketService.getRedPacketDetailList(redPacketId)
        JSONArray array = detailList.getJSONArray("detailList")

        then: "加载到红包详情"
        assert array != null && array.size() > 0
        then: "已经缓存到redis"
        assert connection.exists(redPacketDetailKey) == true
    }


}
