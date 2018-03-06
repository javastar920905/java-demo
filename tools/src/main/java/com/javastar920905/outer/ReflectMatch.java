package com.javastar920905.outer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 测试时,可以使用ReflectMatch.setValue() 为每个对象随机赋值(工具需要继续完善)
 *
 * @author ouzhx on 2017/2/21
 */
public class ReflectMatch {
    protected static Logger logger = Logger.getLogger(ReflectMatch.class);

    public static void setValue(Object obj) {
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            Field[] parent = obj.getClass().getSuperclass().getDeclaredFields();
            List<Field> list = new ArrayList<>();
            // 自身字段赋值
            for (Field f : fields) {
                list.add(f);
            }
            // 父类的字段赋值
            for (Field f : parent) {
                list.add(f);
            }
            String methodName = null;
            String name = null;
            Method method = null;
            // 2、属性赋值
            for (Field field : list) {
                name = field.getName();
                methodName = "set" + (name.substring(0, 1)).toUpperCase() + name.substring(1);
                method = obj.getClass().getMethod(methodName, field.getType());
                fill(obj, field, method);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 将字符串值转换为合适的值填充到对象的指定域
     *
     * @param bean  被填充的java bean
     * @param field 需要填充的域
     */
    private static void fill(Object bean, Field field, Method method) {

        try {
            Object[] oo = new Object[1];

            String type = field.getType().getName();
            if ("java.lang.String".equals(type)) {
                oo[0] = "a";
            } else if ("int".equals(type) || "java.lang.Integer".equals(type)) {
                oo[0] = 1;
            } else if ("java.lang.Float".equals(type)) {
                oo[0] = 8.0;
            } else if ("java.lang.Double".equals(type)) {
                oo[0] = 8.0;
            } else if ("java.math.BigDecimal".equals(type)) {
                oo[0] = new BigDecimal(8);
            } else if ("java.util.Date".equals(type)) {
                oo[0] = new Date();
            } else if ("java.sql.Timestamp".equals(type)) {
                oo[0] = new Timestamp(0);
            } else if ("java.lang.Boolean".equals(type)) {
                oo[0] = true;
            } else if ("java.lang.Long".equals(type) || "long".equals(type)) {
                oo[0] = 2L;
            }
            method.invoke(bean, oo);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
