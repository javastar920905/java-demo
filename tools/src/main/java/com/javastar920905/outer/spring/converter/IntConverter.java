package com.javastar920905.outer.spring.converter;

import org.springframework.core.convert.converter.Converter;

import com.javastar920905.outer.StringUtil;


public class IntConverter implements Converter<String, Integer> {

  @Override
  public Integer convert(String source) {
    if (StringUtil.isNotBlank(source)) {
      return Integer.parseInt(source);
    }
    return 0;
  }
}
