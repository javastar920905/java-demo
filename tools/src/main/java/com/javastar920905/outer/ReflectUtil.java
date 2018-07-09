package com.javastar920905.outer;

import static net.logstash.logback.marker.Markers.append;
import static net.logstash.logback.marker.Markers.appendArray;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.javastar920905.constant.ConstantsRecordEntityCacheClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

/**
 * Created by ouzhx on 2017/2/15. 反射工具类 主要是实体类与map,json之间的相互转化
 */
public class ReflectUtil {
  private static Logger LOGGER = LoggerFactory.getLogger(ReflectUtil.class);

  // 将默认值为可以为0的字段名保存在map的key中,这些字段不设置为空
  private static Map<String, Integer> default01Map = new HashMap<>();

  static {
    default01Map.put("recruitment", 0);
  }

  public static Object bean2JsonWithEmptyField(Object obj) {
    Object withEmptyFieldObj = JSON.parse(JSON.toJSONBytes(obj), Feature.InitStringFieldAsEmpty);
    LOGGER.info("将bean的空字段包含到json  {}", withEmptyFieldObj);
    return withEmptyFieldObj;
  }

  /**
   * 将对象字段封装到paramMap(包含父类字段),同时保留map原有字段; (Integer ==0 时,会被替换为null;
   * String.trim().equals("")时,会被替换为null)
   * 
   * @param obj
   * @param distMap 不能为空
   * @return
   */
  public static Map<String, Object> copyObj2Map(Object obj, Map<String, Object> distMap) {
    if (obj == null) {
      return distMap;
    }
    try {
      Field[] fields = obj.getClass().getDeclaredFields();
      for (Field field : fields) {
        String fieldName = field.getName();
        field.setAccessible(true);
        if (field.get(obj) == null) {
          distMap.put(fieldName, null);
          continue;
        }
        if (field.getType().isArray()) {
          // 如果是数组自动转化为list,默认sql执行的foreach调用的是list的函数
          Class elementType = field.getType().getComponentType();
          if (elementType == Integer.class) {
            // 这里需要转化为实际类型,否则无法转化为list
            Integer[] tempArr = (Integer[]) field.get(obj);
            distMap.put(field.getName(), Arrays.asList(tempArr));
          } else {
            String[] tempArr = (String[]) field.get(obj);
            distMap.put(field.getName(), Arrays.asList(tempArr));
          }
        } else if (field.getType() == Integer.class) {
          // 将Integer字段默认值为0的字段设置为空
          if ((Integer) field.get(obj) == 0 && !default01Map.containsKey(fieldName)) {
            distMap.put(fieldName, null);
          } else {
            distMap.put(fieldName, field.get(obj));
          }
        } else if (field.getType() == String.class && ((String) field.get(obj)).trim().equals("")) {
          distMap.put(fieldName, null);
        } else {
          distMap.put(fieldName, field.get(obj));
        }
      }
    } catch (Exception e) {
      LOGGER.error(appendArray("params", obj).and(append("errType", e.toString())),
          "将对象属性封装到map出错!", e);
    }
    return distMap;
  }


  public static Map<String, String> copyObj2StringMap(Object obj, Map<String, String> distMap) {
    if (obj == null) {
      return distMap;
    }
    try {
      Field[] fields = obj.getClass().getDeclaredFields();
      for (Field field : fields) {
        String fieldName = field.getName();
        field.setAccessible(true);
        if (field.get(obj) == null) {
          distMap.put(fieldName, null);
          continue;
        }
        if (field.getType() == String.class) {
          distMap.put(fieldName, (String) field.get(obj));
        }
      }
    } catch (Exception e) {
      LOGGER.error(appendArray("params", obj).and(append("errType", e.toString())),
          "将对象属性封装到map出错!", e);
    }
    return distMap;
  }

  /**
   * 获取对象指定字段方法名(无法调用静态方法)
   *
   * @param fildeName
   * @return
   */
  public static String getMethodName(String fildeName) throws Exception {
    byte[] items = new byte[0];
    items = fildeName.getBytes();
    items[0] = (byte) ((char) items[0] - 'a' + 'A');
    return new String(items);
  }
}
