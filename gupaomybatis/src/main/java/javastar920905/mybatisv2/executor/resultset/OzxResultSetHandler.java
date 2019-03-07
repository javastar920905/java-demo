package javastar920905.mybatisv2.executor.resultset;

import javastar920905.mybatisv2.binding.OzxMapperRegistry;
import javastar920905.mybatisv2.session.OzxConfiguration;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ouzhx on 2019/3/7.
 */
public class OzxResultSetHandler {
    private OzxConfiguration ozxConfiguration;

    public OzxResultSetHandler(OzxConfiguration ozxConfiguration) {
        this.ozxConfiguration = ozxConfiguration;
    }

    public <E> E handler(PreparedStatement preparedStatement, OzxMapperRegistry.MapperData mapperData) {
         /* 使用反射实现如下硬编码,提高框架扩展性
            rs = preparedStatement.executeQuery();
          // 5.处理结果（依次打印出 user 表中的4列基本数据项的值）
          while (rs.next()) {
              result = new Msg();
              result.setId(rs.getString(1));
              result.setName(rs.getString(2));
          }*/

        //拿到类型创建目标空对象
        Object resultObj = new DefaultObjectFactory().create(mapperData.getClazz());
        try {
            ResultSet rs = preparedStatement.getResultSet();
            if (rs.next()) {
                int i = 0;
                for (Field field : resultObj.getClass().getDeclaredFields()) {
                    //给每个字段赋值
                    setValue(resultObj, field, rs, i);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (E) resultObj;
    }


    /**
     * 利用反射,调用目标类的setter方法
     *
     * @param resultObj
     * @param field
     * @param rs
     * @param i
     */
    private void setValue(Object resultObj, Field field, ResultSet rs, int i) {
        try {
            Method setMethod = resultObj.getClass().getMethod("set" + upperCapital(field.getName()), field.getType());
            setMethod.invoke(resultObj, getResult(field, rs));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Object getResult(Field field, ResultSet rs) throws SQLException {
        //todo typeHandles

        Class<?> type = field.getType();
        if (Integer.class == type) {
            return rs.getInt(field.getName());
        }

        if (String.class == type) {
            return rs.getString(field.getName());
        }

        return rs.getString(field.getName());
    }

    //首字母转大写
    private String upperCapital(String fieldName) {
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
    }
}
