package javastar920905.mybatisv2.plugins;

import javastar920905.mybatisv2.binding.OzxMapperRegistry;
import javastar920905.mybatisv2.executor.OzxExecutor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 * @author ouzhx on 2019/3/8.
 * 使用方式查看 mybatis 文档
 * @link http://www.mybatis.org/mybatis-3/zh/configuration.html#plugins
 */
@Intercepts({@Signature(
        type = OzxExecutor.class,
        method = "query",
        args = {OzxMapperRegistry.MapperData.class, Object.class})})
public class OzxPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        BoundSql boundSql = mappedStatement.getBoundSql(invocation.getArgs()[1]);
        System.out.println(String.format("plugin output sql = %s , param=%s", boundSql.getSql(), boundSql.getParameterObject()));
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
