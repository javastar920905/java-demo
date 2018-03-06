package com.javastar920905.outer.spring.converter;

import org.springframework.core.convert.converter.Converter;

import com.javastar920905.outer.StringUtil;


public class DoubleConverter implements Converter<String, Double> {

  @Override
  public Double convert(String source) {
    if (StringUtil.isNotBlank(source)) {
      return Double.parseDouble(source);
    }
    return 0.0;
  }
}
