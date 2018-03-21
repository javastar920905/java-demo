package com.javastar920905.validator;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.javastar920905.entity.domain.RedPacket;
import org.springframework.beans.propertyeditors.ByteArrayPropertyEditor;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * 红包属性校验器
 * 
 * @author ouzhx on 2018/3/21.
 */
public class RedPacketValidator implements Validator {

  /*
   * private final SmartValidator smartValidator;
   * 
   *//**
      * 构造器方式组合其他校验器
      * 
      * @param smartValidator
      *//*
         * public RedPacketValidator(SmartValidator smartValidator) { if (smartValidator == null) {
         * throw new IllegalArgumentException( "The supplied [Validator] is " +
         * "required and must not be null."); } this.smartValidator = smartValidator; }
         */

  /**
   * 验证当前实例是否属于当前clazz Can this {@link Validator} {@link #validate(Object, Errors) validate}
   * instances of the supplied {@code clazz}?
   * <p>
   * This method is <i>typically</i> implemented like so:
   * 
   * <pre class="code">
   * return Foo.class.isAssignableFrom(clazz);
   * </pre>
   * 
   * (Where {@code Foo} is the class (or superclass) of the actual object instance that is to be
   * {@link #validate(Object, Errors) validated}.)
   *
   * @param clazz the {@link Class} that this {@link Validator} is being asked if it can
   *        {@link #validate(Object, Errors) validate}
   * @return {@code true} if this {@link Validator} can indeed {@link #validate(Object, Errors)
   *         validate} instances of the supplied {@code clazz}
   */
  @Override
  public boolean supports(Class<?> clazz) {
    return RedPacket.class.equals(clazz);
  }

  /**
   * object: 待校验的对象; errors: 错误报告信息 Validate the supplied {@code target} object, which must be of a
   * {@link Class} for which the {@link #supports(Class)} method typically has (or would) return
   * {@code true}.
   * <p>
   * The supplied {@link Errors errors} instance can be used to report any resulting validation
   * errors.
   *
   * @param target the object that is to be validated (can be {@code null})
   * @param errors contextual state about the validation process (never {@code null})
   * @see ValidationUtils
   */
  @Override
  public void validate(Object target, Errors errors) {
    // 当null 或 空字符串时拒绝
    ValidationUtils.rejectIfEmpty(errors, "userId", "userId 不能为空");
    ValidationUtils.rejectIfEmpty(errors, "money", "oepnId 不能为空");
    ValidationUtils.rejectIfEmpty(errors, "packetSize", "红包数量 不能为空");
    /*if (errors.hasErrors()) {
      for (FieldError fieldError : errors.getFieldErrors()) {
        System.out.println(fieldError.getField());
        System.out.println(fieldError.getRejectedValue());
        // ...
      }
    }*/
    RedPacket redPacket = (RedPacket) target;
    // 每个属性都需要做为空判断,验证器只会把错误信息填充到errors对象, 不会中断后面的语句
    if (redPacket.getPacketSize() != null) {
      if (redPacket.getPacketSize() < 1) {
        errors.rejectValue("packetSize", "红包数量不能少于1个");
      }
    }
    if (redPacket.getMoney() != null) {
      if (redPacket.getMoney() > 200) {
        errors.rejectValue("packetSize", "红包金额不能大于200");
      }
      if (redPacket.getMoney() < (0.01 * redPacket.getPacketSize())) {
        errors.rejectValue("packetSize", "每个红包金额不能少于0.01");
      }
    }


  }
}
