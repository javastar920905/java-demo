package javastar920905.mybatisv2.session;

import javastar920905.mybatisv2.binding.OzxMapperProxy;
import javastar920905.mybatisv2.binding.OzxMapperRegistry;
import org.apache.ibatis.binding.MapperRegistry;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ouzhx on 2019/3/7.
 */
public class OzxConfiguration {
    private OzxMapperRegistry mapperRegistry=new OzxMapperRegistry();
    private Class mapperInterface;
    private String scanPath;

    public <T> T getMapper(Class<T> clazz, OzxSqlSession ozxSqlSession) {
        return (T) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{clazz},
                new OzxMapperProxy(ozxSqlSession,clazz));
    }

    public void build(){

    }



    public OzxMapperRegistry getMapperRegistry() {
        return mapperRegistry;
    }

    public String getScanPath() {
        return scanPath;
    }

    public void setScanPath(String scanPath) {
        this.scanPath = scanPath;
    }
}
