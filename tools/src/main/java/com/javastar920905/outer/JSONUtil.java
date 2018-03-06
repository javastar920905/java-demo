package com.javastar920905.outer;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import java.util.Set;

/**
 * Created by ouzhx on 2016/12/2.
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
   * @param filter
   * @return JSONObject
   * @author chenjun
   */
  public static JSONObject parseObjectToJSONObject(Object object, SerializeFilter filter) {
    String jsonStr = JSON.toJSONString(object, filter);

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
