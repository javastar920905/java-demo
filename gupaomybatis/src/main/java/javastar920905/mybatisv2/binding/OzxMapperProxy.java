package javastar920905.mybatisv2.binding;

import javastar920905.mybatisv2.session.OzxConfiguration;
import javastar920905.mybatisv2.session.OzxSqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author ouzhx on 2019/3/7.
 * <p>
 * xxMapper.java 接口的代理类(mapper接口是用来找xml对应的sql的)
 */
public class OzxMapperProxy<T> implements InvocationHandler {
    private  final OzxSqlSession sqlSession;
    private final Class<T> mapperInterface;

    public OzxMapperProxy(OzxSqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //获取类名
        String fullClazzName = method.getDeclaringClass().getName();

        //获取mapper 接口信息
        OzxMapperRegistry.MapperData mapperData = sqlSession.getConfiguration()
                                                            .getMapperRegistry()
                                                            .getMapperData(fullClazzName + "." + method.getName());


        if (null!=mapperData) {
            System.out.println(String.format("SQL [%S], parammeter [%S]",mapperData.getSql(),args));
            return sqlSession.selectOne(mapperData, String.valueOf(args[0]));
        }
        return method.invoke(proxy, args);
    }


}
