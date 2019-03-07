package javastar920905.mybatis.binding;

import javastar920905.mybatis.session.OzxConfiguration;
import javastar920905.mybatis.session.OzxSqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author ouzhx on 2019/3/7.
 * <p>
 * xxMapper.java 接口的代理类(mapper接口是用来找xml对应的sql的)
 */
public class OzxMapperProxy implements InvocationHandler {
    private OzxSqlSession sqlSession;

    public OzxMapperProxy(OzxSqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //获取方法名
        String fullClazzName = method.getDeclaringClass().getName();

        if (fullClazzName.equals(OzxConfiguration.UserMapperXml.namespace)) {
            String sql = OzxConfiguration.UserMapperXml.sqlMethodMapping.get(method.getName());
            return sqlSession.selectOne(sql, String.valueOf(args[0]));
        }
        return method.invoke(this, args);
    }


}
