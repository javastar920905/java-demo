package javastar920905.mybatis.session;

import javastar920905.mybatis.executor.OzxExecutor;

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

    /**
     * @param statement
     * @param parameter
     * @param <T>       任何类型
     * @return
     */
    public <T> T selectOne(String statement, String parameter) {
        return executor.query(statement, parameter);
    }

}
