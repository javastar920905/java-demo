package com.javastar920905

import com.javastar920905.outer.redis.RedisFactory
import org.springframework.data.redis.connection.RedisConnection

/**
 * @author ouzhx on 2018/3/27.
 * 测试时需要redis连接可以继承当前类
 */
class BaseRedisSpecification extends BaseDetachedConfig {
    RedisConnection connection = null

    /**
     * 每个方法执行前,初始化资源
     * @return
     */
    def setup() {
        connection = RedisFactory.getConnection()
        assert connection != null
        connection.flushDb()
    }

    /**
     * 每个方法执行后,清除资源
     * @return
     */
    def cleanup() {
        if (connection != null && !connection.isClosed()) {
            connection.close()
        }
    }
}
