package com.javastar920905.outer.spring.converter;

import org.springframework.core.convert.converter.Converter;

import com.javastar920905.outer.StringUtil;


public class FloatConverter implements Converter<String, Float> {

  @Override
  public Float convert(String source) {
    if (StringUtil.isNotBlank(source)) {
      return Float.parseFloat(source);
    }
    return 0.0f;
  }
}
