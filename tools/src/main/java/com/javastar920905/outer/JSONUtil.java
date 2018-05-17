package com.javastar920905.outer;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import java.util.Set;

/**
 * Created by ouzhx on 2016/12/2.
 *
 * 参考文档:
 * http://note.youdao.com/noteshare?id=abf2b149d54fe631590212f6603b3220&sub=99E4BE4198BC42DEA0681470A5587B5E
 */
public class JSONUtil {

  private JSONUtil() {}

  /**
   * 获取JSON排除属性Filter
   * 
   * @param properties
   * @return SimplePropertyPreFilter
   * @author chenjun
   */
  public static PropertyPreFilter getExcludeFilter(String... properties) {
    SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
    Set<String> excludes = filter.getExcludes();

    Assert.notNull(properties);

    for (String property : properties) {
      excludes.add(property);
    }
    return filter;
  }

  /**
   * 获取JSON包含属性Filter
   *
   * @param properties
   * @return SimplePropertyPreFilter
   * @author chenjun
   */
  public static PropertyPreFilter getIncludeFilter(String... properties) {
    SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
    Set<String> includes = filter.getIncludes();

    Assert.notNull(properties);

    for (String property : properties) {
      includes.add(property);
    }
    return filter;
  }

  /**
   * 把Java对象转换成JSON对象
   * 
   * @param object
   * @param filter 属性过滤器 如: getIncludeFilter("value","name") 只包含value 和name 属性
   * @return JSONObject
   * @author chenjun
   */
  public static JSONObject parseObjectToJSONObject(Object object, SerializeFilter filter) {
    String jsonStr = JSON.toJSONString(object, filter);

    return JSON.parseObject(jsonStr);
  }

  /**
   * 把Java对象转换成JSON对象(带时间,包含值为null的字段)
   *
   * @param object
   * @return JSONObject
   * @author chenjun
   */
  public static JSONObject parseObjectToJSONObject(Object object) {
    String jsonStr = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd hh:mm:ss",
        SerializerFeature.WriteMapNullValue);

    return JSON.parseObject(jsonStr);
  }

  /**
   * 把Java对象转换成JSON对象(自定义时间格式)
   * 
   * @param object
   * @param dateFormate // 其他时间格式如: "MM月dd日 HH:mm"
   * @return
   */
  public static JSONObject parseObjectToJSONObject(Object object, String dateFormate) {
    String jsonStr = JSON.toJSONStringWithDateFormat(object, dateFormate);

    return JSON.parseObject(jsonStr);
  }

  /**
   * 把Java对象转换成JSON数组
   *
   * @param object
   * @param filter
   * @return JSONArray
   * @author chenjun
   */
  public static JSONArray parseObjectToJSONArray(Object object, SerializeFilter filter) {
    String jsonStr = JSON.toJSONString(object, filter);

    return JSON.parseArray(jsonStr);
  }
}
