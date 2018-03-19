package com.javastar920905

import com.alibaba.fastjson.JSONObject
import com.javastar920905.constant.CommonConstants
import com.javastar920905.entity.domain.RedPacket
import com.javastar920905.outer.redis.RedisFactory
import org.springframework.data.redis.connection.RedisConnection

import java.sql.Timestamp

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
        given: "设置红包信息(金额,数目等)"
        // "确认redis中没有脏数据"
        connection.del(redPacketServiceSpy.getRedPacketKey(id), redPacketServiceSpy.getRedPacketSizeKey(id))
        assert connection.exists(redPacketServiceSpy.getRedPacketKey(id)) == false
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
        //mock打桩 stub  以及验证调用次数
        1 * redPacketMapper.insertAllColumn(_) >> 1
        1 * redPacketMapper.updateById(_) >> 1
        assert connection.get(redPacketServiceSpy.getRedPacketSizeKey(redPacket.getId())) != null
        assert connection.lLen(redPacketServiceSpy.getRedPacketKey(redPacket.getId())) == size
        assert jsonObject.getBoolean(CommonConstants.key.result.name()) == true


        where: "给出以下红包信息"
        size | id  | userId  | money | createDate                                | expireTime
        10   | "1" | "ouzhx" | 10d   | new Timestamp(System.currentTimeMillis()) | new Timestamp(System.currentTimeMillis() + 1000000000)
        5    | "2" | "ouzhx" | 5d    | new Timestamp(System.currentTimeMillis()) | new Timestamp(System.currentTimeMillis() + 1000000000)
    }


}
