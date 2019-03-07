package javastar920905.mybatis.session;

import javastar920905.mybatis.binding.OzxMapperProxy;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ouzhx on 2019/3/7.
 */
public class OzxConfiguration {

    public <T> T getMapper(Class<T> clazz, OzxSqlSession ozxSqlSession) {
        return (T) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{clazz},
                new OzxMapperProxy(ozxSqlSession));
    }


    /**
     * 模拟xml解析出来的数据,用于反射
     */
    public static class UserMapperXml {
        public static final String namespace = "javastar920905.dao.MsgMapper";

        public static final Map<String, String> sqlMethodMapping = new HashMap<>();

        static {
            sqlMethodMapping.put("selectByPrimaryKey", "select * from msg where id=%d ");
        }

    }
}
