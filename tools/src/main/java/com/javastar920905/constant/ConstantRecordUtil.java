/**
 *
 */
package com.javastar920905.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ouzhx 和数据库常量表constants_record 的type字段对应
 */
public class ConstantRecordUtil {
  // 用於配合 ConstantsRecordClient.findConstantsByTypeInAndValueEq()方法一起使用
  public static final List<String> salaryTypeList = new ArrayList<>();
  static {
    salaryTypeList.add(ConstantRecordUtil.DAY_SALARY);
    salaryTypeList.add(ConstantRecordUtil.MONTHLY_SALARY);
  }



  /**
   * 性别
   */
  public static final String SEX = "sex";


  /**
   * 薪酬
   */
  public static final String SALARY = "salary";


  /**
   * 薪酬类型 日薪
   */
  public static final String DAY_SALARY = "day_salary";
  /**
   * 薪酬类型 月薪
   */
  public static final String MONTHLY_SALARY = "monthly_salary";
}
