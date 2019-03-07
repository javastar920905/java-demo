package javastar920905.mybatisv2.binding;

import javastar920905.dao.MsgMapper;
import javastar920905.entity.Msg;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ouzhx on 2019/3/7.
 * 模拟xml解析出来的数据,用于反射
 */
public class OzxMapperRegistry {

    /**
     * key=namespace+xml中的id
     */
    public static final Map<String, MapperData> sqlMethodMapping = new HashMap<>();

    public OzxMapperRegistry() {
        MapperData<Msg> mapperData = new MapperData<>("select * from msg where id=%d ", Msg.class);
        sqlMethodMapping.put("javastar920905.dao.MsgMapper.selectByPrimaryKey", mapperData);
    }

    public MapperData getMapperData(String statement) {
        return sqlMethodMapping.get(statement);
    }


    public class MapperData<T> {
        /**
         * 要执行的sql
         */
        private String sql;
        /**
         * 返回值类型
         */
        private Class<T> clazz;

        public MapperData(String sql, Class<T> clazz) {
            this.sql = sql;
            this.clazz = clazz;
        }

        public String getSql() {
            return sql;
        }

        public Class<T> getClazz() {
            return clazz;
        }
    }
}
