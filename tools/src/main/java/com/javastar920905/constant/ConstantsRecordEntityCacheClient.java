package com.javastar920905.constant;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.javastar920905.outer.StringUtil;
import com.javastar920905.outer.redis.RedisFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;

/**
 * @author ouzhx on 2016/9/21.
 *
 *         缓存查询工具类(不适合缓存大字段,如常量描述,爬取的网页html等,查询会非常缓慢)
 */
public class ConstantsRecordEntityCacheClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(ConstantsRecordEntityCacheClient.class);

  private static List<ConstantsRecordEntity> allConstants;

  private ConstantsRecordEntityCacheClient() {
    RedisConnection connection = RedisFactory.getConnection();
    byte[] constantByte = connection.get(CommonConstants.CACHE_CONSTANTS.getBytes());
    try {
      allConstants = JSONObject.parseArray(
          new String(constantByte, CommonConstants.Charset.utf8.value), ConstantsRecordEntity.class);
      LOGGER.info("获取redis缓存数据:" + allConstants.size());
    } catch (UnsupportedEncodingException e) {
      LOGGER.error(e.getMessage(), e);
    }

    connection.close();
  }

  public static List<ConstantsRecordEntity> getAllConstants() {
    if (allConstants == null) {
      new ConstantsRecordEntityCacheClient();
    }
    return allConstants;
  }

  /**
   * 根据type查询
   *
   * @param type
   * @return
   * @author huangzhk 2016/09/21
   */
  public static List<ConstantsRecordEntity> findConstantsByTypeEq(String type) {
    return getAllConstants().stream()
        .filter(constantsRecordEntity -> StringUtil.equals(constantsRecordEntity.getType(), type))
        .sorted(Comparator.comparingInt(ConstantsRecordEntity::getOrder)).collect(Collectors.toList());
  }


  /**
   *
   * ouzhx 2016年9月21日
   *
   * @param parentValue
   * @return 根据ParentValue 和type查询
   */
  public static List<ConstantsRecordEntity> findConstantsByParentValueAndType(Integer parentValue,
      String type) {
    return getAllConstants().stream()
        .filter(constantsRecordEntity -> constantsRecordEntity.getParentValue() != null
            && constantsRecordEntity.getParentValue().equals(parentValue)
            && constantsRecordEntity.getType().equals(type))
        .collect(Collectors.toList());
  }

  /**
   * 根据type和value值查询
   *
   * @param type
   * @param value
   * @return
   * @author huangzhk 2016/09/21
   */
  public static ConstantsRecordEntity findConstantsByTypeAndValueEq(String type, Integer value) {
    List<ConstantsRecordEntity> constantsRecordEntityList = getAllConstants().stream()
        .filter(constantsRecordEntity -> StringUtil.equals(constantsRecordEntity.getType(), type)
            && constantsRecordEntity.getValue().equals(value))
        .limit(1).collect(Collectors.toList());
    if (!constantsRecordEntityList.isEmpty()) {
      return constantsRecordEntityList.get(0);
    }
    return null;
  }


  public static ConstantsRecordEntity findConstantsByTypeAndNameEq(String type, String name) {
    List<ConstantsRecordEntity> constantsRecordEntityList = getAllConstants().stream()
        .filter(constantsRecordEntity -> StringUtil.equals(constantsRecordEntity.getType(), type)
            && StringUtil.equals(constantsRecordEntity.getName(), name))
        .collect(Collectors.toList());
    if (!constantsRecordEntityList.isEmpty()) {
      return constantsRecordEntityList.get(0);
    }
    return null;
  }

  /**
   * 对指定Type进行模糊查询
   * 
   * @param type
   * @param name
   * @return
   */
  public static List<ConstantsRecordEntity> findConstantsByTypeAndNameLike(String type, String name) {
    List<ConstantsRecordEntity> constantsRecordEntityList = getAllConstants().stream()
        .filter(constantsRecordEntity -> StringUtil.equals(constantsRecordEntity.getType(), type)
            && StringUtil.contains(StringUtil.fullToHalf(constantsRecordEntity.getName()).toUpperCase(),
                StringUtil.fullToHalf(name).toUpperCase()))
        .collect(Collectors.toList());
    return constantsRecordEntityList;
  }

  /**
   * 根据多个type和value值查询
   *
   * @param typeList
   * @param value
   * @return
   * @author huangzhk 2017/02/04
   */
  public static List<ConstantsRecordEntity> findConstantsByTypeInAndValueEq(List<String> typeList,
      Integer value) {

    if (CollectionUtils.isEmpty(typeList) || value == null) {
      return null;
    }

    return getAllConstants().stream()
        .filter(constantsRecordEntity -> typeList.contains(constantsRecordEntity.getType())
            && constantsRecordEntity.getValue().equals(value))
        .collect(Collectors.toList());
  }

  /**
   * 根据type和value值查询常量名
   *
   * @param type
   * @param value
   * @return
   * @author huangzhk 2016/10/08
   */
  public static String findConstantNameByTypeAndValueEq(String type, Integer value) {
    ConstantsRecordEntity constantsRecordEntity = null;
    if (StringUtil.isNotBlank(type) && value != null) {
      constantsRecordEntity = findConstantsByTypeAndValueEq(type, value);
    }
    return constantsRecordEntity != null ? constantsRecordEntity.getName() : StringUtil.EMPTY;
  }

  /**
   * 根据type和value列表查询name
   *
   * @param type
   * @param valueList
   * @param symbol 多个name用symbol隔开
   * @return
   * @author huangzhk 2016/09/14
   */
  public static String getNameByTypeAndValueList(String type, List<Integer> valueList,
      String symbol) {
    if (StringUtil.isNotBlank(type) && !valueList.isEmpty()) {
      List<String> nameList = new ArrayList<>();
      for (int value : valueList) {
        ConstantsRecordEntity constantsRecordEntity = findConstantsByTypeAndValueEq(type, value);
        if (constantsRecordEntity != null) {
          nameList.add(constantsRecordEntity.getName());
        }
      }
      return StringUtil.join(nameList, symbol);
    }
    return StringUtil.EMPTY;
  }

}
