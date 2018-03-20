package com.javastar920905

import com.alibaba.fastjson.JSONObject
import com.javastar920905.constant.CommonConstants
import com.javastar920905.entity.domain.RedPacket
import com.javastar920905.outer.redis.RedisFactory
import org.springframework.data.redis.connection.RedisConnection

import java.sql.Timestamp
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * @author ouzhx on 2018/3/19.
 */
class RedPacketSpecification extends DetachedJavaConfig {
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
        // "确认redis中没有脏数据"
        connection.del(redPacketServiceSpy.getRedPacketKey(id), redPacketServiceSpy.getRedPacketSizeKey(id))
        assert connection.exists(redPacketServiceSpy.getRedPacketKey(id)) == false

        given: "设置红包信息(金额,数目等)"
        RedPacket redPacket = new RedPacket()
        redPacket.setPacketSize(size)
        redPacket.setId(id)
        redPacket.setUserId(userId)
        redPacket.setMoney(money)
        redPacket.setCreateDate(createDate)
        redPacket.setExpireTime(expireTime)

        when: "发送红包"
        JSONObject jsonObject = redPacketServiceSpy.giveRedPacket(redPacket)

        then: "发送红包成功,生成库存信息!"
        assert connection.get(redPacketServiceSpy.getRedPacketSizeKey(redPacket.getId())) != null
        assert connection.lLen(redPacketServiceSpy.getRedPacketKey(redPacket.getId())) == size
        assert jsonObject.getBoolean(CommonConstants.key.result.name()) == true

        //mock打桩 stub 以及验证调用次数(很多时候需要在then:代码块后面才生效)
        with(redPacketMapperMock) {
            1 * insertAllColumn(_) >> 1
            1 * updateById(_) >> 1
        }

        where: "给出以下红包信息"
        id  | userId  | money | size | createDate                                | expireTime
        "1" | "ouzhx" | 10d   | 10   | new Timestamp(System.currentTimeMillis()) | new Timestamp(System.currentTimeMillis() + 1000000000)
        "2" | "ouzhx" | 5d    | 5    | new Timestamp(System.currentTimeMillis()) | new Timestamp(System.currentTimeMillis() + 1000000000)
    }


    def "抢红包功能测试"() {
        setup: "发红包"
        发红包功能测试()

        when: "开始抢红包"
        int threadNums = 15
        CountDownLatch countDownLatch = new CountDownLatch(threadNums);
        for (int i = 0; i < threadNums; i++) {
            final String uid = "" + i
            Thread thread = new Runnable() {
                @Override
                void run() {
                    redPacketServiceSpy.bookingRedPacket2WithDoubleQuque(uid, "欧besos" + uid, redPacketId)
                    countDownLatch.countDown()
                }
            }
            thread.start()
        }

        try {
            countDownLatch.await();

            // 给消费队列留时间
            TimeUnit.SECONDS.sleep(60 * 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        then: "验证红包结果"


        where: "给出以下红包信息"
        id  | userId       | redPacketId
        "1" | "ouzhx-rob1" | "1"
        "2" | "ouzhx-rob2" | "2"

    }


}
