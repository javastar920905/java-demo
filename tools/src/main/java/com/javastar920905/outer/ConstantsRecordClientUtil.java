package com.javastar920905.outer;

import static net.logstash.logback.marker.Markers.append;
import static net.logstash.logback.marker.Markers.appendArray;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.javastar920905.constant.ConstantRecordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by ouzhx on 2017/2/15. 负责将ConstantRecordUtil 中的字段转换为驼峰命名
 */
public class ConstantsRecordClientUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(ConstantRecordUtil.class);

    public static Map<String, String> constantsKeyMap = new HashMap<>();

    static {
        try {
            //constantsKeyMap 初始化
            ConstantRecordUtil obj = new ConstantRecordUtil();
            Field[] fields = ConstantRecordUtil.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String type = field.get(obj).toString();
                constantsKeyMap.put(camelName(type), type);
            }
            // 由于有些实体字段命名不统一,需要单独添加
            constantsKeyMap.put("workExperience", "join_work_year");
            constantsKeyMap.put("degree", "education");
            constantsKeyMap.put("overSea", "education_overseas_experience");
        } catch (Exception e) {
            LOGGER.error("对象转换成MaP失败");
        }

    }


    /**
     * (即,自动将实体类中包含ConstantRecord表的 Type字段的number的中文值注入到 传递的json中) 将对象中的Integer
     * 类型的并且constantsKeyMap包含的字段Name 添加到参数json
     *
     * @param
     * @return
     * @author ouzhx on 2017/2/21
     */
    public static void autoWiredConstantsNameByConstantsValue(Object obj, JSONObject json) {
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                String fieldName = field.getName();
                if (fieldName.equalsIgnoreCase("serialVersionUID")) {
                    continue;
                }
                Method m = obj.getClass().getMethod("get" + ReflectUtil.getMethodName(fieldName));
                // 类型检查
                if (field.getType() == Integer.class && constantsKeyMap.containsKey(fieldName)) {
                    Integer fieldValue = (Integer) m.invoke(obj);
                    if (fieldValue == null || fieldValue <= 0) {
                        json.put(fieldName + "Name", "");
                        continue;
                    }
                    // 此处要连接redis 需要启动web服务器连接 无法用main方法测试
                    if (fieldName.equals("salary")) {
                    } else if (fieldName.equals("recruitment")) {
                    } else if (fieldName.equals("workExperience") || fieldName
                        .equals("joinWorkYear")) {
                    } else {
          /*  json.put(fieldName + "Name", ConstantsRecordClient.findConstantNameByTypeAndValueEq(
                constantsKeyMap.get(fieldName), Integer.valueOf(fieldValue)));*/
                    }
                } else {
                    if (fieldName.equals("overSea")) {
                        String fieldValue = (String) m.invoke(obj);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(appendArray("params", obj).and(append("errType", e.toString())),
                "将对象中的Integer 类型的并且constantsKeyMap包含的字段Name 添加到json,程序出错!", e);
            e.printStackTrace();
        }
    }



    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
     * 例如：HELLO_WORLD->HelloWorld
     *
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    private static String camelName(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母小写
            return name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String camels[] = name.split("_");
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }
}
