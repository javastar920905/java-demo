package com.javastar920905

import com.alibaba.fastjson.JSONObject
import com.javastar920905.constant.CommonConstants
import com.javastar920905.entity.domain.RedPacket
import com.javastar920905.outer.redis.RedisFactory
import com.javastar920905.util.ByteUtil
import org.springframework.data.redis.connection.RedisConnection

import java.sql.Timestamp

/**
 * 需要新建groovy class
 * @author ouzhx on 2018/3/19.
 */
class RedPacketSpecification extends DetachedJavaConfig {
    RedisConnection connection = null

    def setup() {
        connection = RedisFactory.getConnection()
        assert connection != null
        connection.expire("redPacket".getBytes(), 0l)
    }


    def cleanup() {
        if (connection != null) {
            connection.close()
        }
    }

    def "发红包功能测试"() {
        given: "设置红包信息(金额,数目等)"
        RedPacket redPacket = new RedPacket()
        redPacket.setPacketSize(size)
        redPacket.setId(id)
        redPacket.setUserId(userId)
        redPacket.setMoney(money)
        redPacket.setCreateDate(createDate)
        redPacket.setExpireTime(expireTime)

        //打桩 stub
        redPacketMapperMock.insertAllColumn(_) >> 1
        1 * redPacketMapperMock.updateById(_) >> 1


        when: "发送红包"
        JSONObject jsonObject = redPacketServiceSpy.giveRedPacket(redPacket)

        then: "发送红包成功,生成库存信息!"
        1 * redPacketMapperMock.insertAllColumn(_)
        1 * redPacketMapperMock.updateById(_)
        def redisSize = ByteUtil.getInt(connection.get(redPacketServiceMock.getRedPacketSizeKey(id)));
        assert jsonObject.getBoolean(CommonConstants.key.result.name()) == true
        assert redisSize == size
        assert ByteUtil.getInt(connection.lLen(redPacketServiceMock.getRedPacketKey(id))) == size


        where: "给出以下红包信息"
        size | id  | userId  | money | createDate                                | expireTime
        10   | "1" | "ouzhx" | 10d   | new Timestamp(System.currentTimeMillis()) | new Timestamp(System.currentTimeMillis() + 1000000000)
        5    | "2" | "ouzhx" | 5d    | new Timestamp(System.currentTimeMillis()) | new Timestamp(System.currentTimeMillis() + 1000000000)
    }
}
