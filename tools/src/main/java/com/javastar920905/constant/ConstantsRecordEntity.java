package com.javastar920905.constant;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 常量值记录实体类
 * 
 * @author ouzhx
 */
@Setter
@Getter
public class ConstantsRecordEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 主键id
   */
  private Integer id;

  /**
   * 常量描述
   */
  private String description;

  /**
   * 常量名称
   */
  private String name;

  /**
   * 常量类型 @see com.javastar920905.constant.ConstantRecordUtil
   */
  private String type;

  /**
   * 常量值
   */
  private Integer value;

  /**
   * 父Value
   */
  private Integer parentValue;

  /**
   * 排序值
   */
  private Integer order;
}
