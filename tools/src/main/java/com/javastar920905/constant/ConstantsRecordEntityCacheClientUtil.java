package com.javastar920905.constant;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.javastar920905.outer.ReflectUtil;
import com.javastar920905.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;


/**
 * @author ouzhx on 2017/2/15.
 */
public class ConstantsRecordEntityCacheClientUtil {
  private static Logger LOGGER = LoggerFactory.getLogger(ConstantRecordKeys.class);

  /**
   * 字段预览 : companyScale, education, resumeContactObtainMethod, jobNature, collegeType, language,
   * itProject, welfare, salary, subjectType, daySalary, politicsStatus,
   * educationOverseasExperience, listenSpeakAbility, monthlySalary, companyStage, dateType,
   * languageLevel, joinWorkYear, companyNature, currency, personType, resumePriceBase,
   * authenticationStatus, collegeNature, dataVisible, jobWantedStatus, resumeTimeliness,
   * awardsLevel, salaryType, sex, educationFullTime, dataStatus, [daySalary, monthlySalary],
   * pidType, resumeSource, resumePriceTimelinessCoefficient, literacy, scholarshipType,
   * positionLabel, countryBuildKey, maritalStatus, scholarshipLevel, matchingCreatedMethod
   */
  public static Map<String, String> constantsKeyMap = new HashMap<>();

  static {
    try {
      // 负责将ConstantRecordUtil 中的字段转换为驼峰命名
      ConstantRecordKeys obj = new ConstantRecordKeys();
      Field[] fields = ConstantRecordKeys.class.getDeclaredFields();
      for (Field field : fields) {
        field.setAccessible(true);
        String type = field.get(obj).toString();
        constantsKeyMap.put(StringUtil.camelName(type), type);
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
   * 使用反射api 实现,自动将实体类中包含ConstantRecord表的Type字段的number的name注入到 传递的json中)
   *
   * 将对象中的Integer 类型的并且constantsKeyMap包含的字段Name 添加到参数json
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

        // 忽略序列化id字段处理
        if (fieldName.equalsIgnoreCase("serialVersionUID")) {
          continue;
        }
        dealJsonField(obj, field, fieldName, json);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 方法重载,可指定要自动转换的字段列表(字段名要存在于 constantsKeyMap中)
   * 
   * @param obj
   * @param json
   * @param includeFields
   */
  public static void autoWiredConstantsNameByConstantsValue(Object obj, JSONObject json,
      List<String> includeFields) {
    Field[] fields = obj.getClass().getDeclaredFields();
    try {
      for (Field field : fields) {
        String fieldName = field.getName();
        if (fieldName.equalsIgnoreCase("serialVersionUID")) {
          continue;
        }

        // 不在包含字段列表中则跳过
        if (includeFields != null) {
          if (!includeFields.contains(fieldName)) {
            continue;
          }
        }

        dealJsonField(obj, field, fieldName, json);

      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  private static JSONObject dealJsonField(Object obj, Field field, String fieldName,
      JSONObject json) throws Exception {
      //获取对象get方法
    Method m = obj.getClass().getMethod("get" + ReflectUtil.getMethodName(fieldName));
    // 类型检查,对Integer 类型的(字段名要存在于 constantsKeyMap中)的字段实现自动获取name
    if (field.getType() == Integer.class && constantsKeyMap.containsKey(fieldName)) {
      Integer fieldValue = (Integer) m.invoke(obj);

      if (fieldValue == null || fieldValue <= 0) {
          //空值处理
        json.put(fieldName + "Name", "");
      } else if (fieldName.equals("recruitment")) {
        json.put("recruitmentName", (fieldValue != null && fieldValue == 1) ? "统招" : "");
      } /*
            else if (fieldName.equals("salary")) { // 此处要连接redis 需要启动web服务器连接 无法用main方法测试
         * json.put("salaryName", SalaryUtil.getName(fieldValue,null,null)); }
         * else if (fieldName.equals("workExperience")) { // 调用职位工作经验时间方法
         * json.put("workExperienceName", CommonUtils.fetchWorkExperienceName(fieldValue)); } else
         * if (fieldName.equals("joinWorkYear")) { // 调用简历工作经验时间方法 json.put("workExperienceName",
         * CommonUtils.getWorkExperienceNameByJoinWorkYear(fieldValue)); }
         */else {
        json.put(fieldName + "Name", ConstantsRecordEntityCacheClient.findConstantNameByTypeAndValueEq(
            constantsKeyMap.get(fieldName), Integer.valueOf(fieldValue)));
      }
    } else {
      if (fieldName.equals("overSea")) {
        String fieldValue = (String) m.invoke(obj);
        json.put("overSeaName",
            fieldValue != null ? ConstantsRecordEntityCacheClient.findConstantNameByTypeAndValueEq(
                constantsKeyMap.get(fieldName), Integer.valueOf(fieldValue)) : "");
      }
    }
    return json;
  }


}
