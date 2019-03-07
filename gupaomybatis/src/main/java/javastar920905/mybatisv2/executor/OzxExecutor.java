package javastar920905.mybatisv2.executor;

import javastar920905.mybatisv2.binding.OzxMapperRegistry;

/**
 * @author ouzhx on 2019/3/7.
 */
public interface OzxExecutor {

    <T> T query(OzxMapperRegistry.MapperData mapperData, Object parameter);
}
