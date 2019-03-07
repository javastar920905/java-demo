package javastar920905.mybatisv2.session;

import javastar920905.mybatisv2.binding.OzxMapperRegistry;
import javastar920905.mybatisv2.executor.OzxExecutor;

/**
 * @author ouzhx on 2019/3/7.
 */
public class OzxSqlSession {
    OzxConfiguration configuration;
    OzxExecutor executor;

    public OzxSqlSession(OzxConfiguration configuration, OzxExecutor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    /**
     * 单一职责,委托模式的提现
     *
     * @param clazz 泛型class
     * @param <T>   接收任意类型的class
     * @return
     */
    public <T> T getMapper(Class<T> clazz) {
        return configuration.getMapper(clazz, this);
    }


    public <T> T selectOne(OzxMapperRegistry.MapperData mapperData, String parameter) {
        return executor.query(mapperData, parameter);
    }


    public OzxConfiguration getConfiguration() {
        return configuration;
    }

    public OzxExecutor getExecutor() {
        return executor;
    }
}
