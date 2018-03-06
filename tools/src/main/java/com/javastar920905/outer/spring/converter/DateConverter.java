package com.javastar920905.outer.spring.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import com.javastar920905.outer.StringUtil;


/**
 * @author ouzhx
 */
public class DateConverter implements Converter<String, Date> {

  private static final Logger LOGGER = LoggerFactory.getLogger(DateConverter.class);

  @Override
  public Date convert(String source) {
    if (StringUtil.isNotBlank(source)) {
      try {
        String format = "yyyy-MM-dd";
        int len = source.length();
        if (len > 10) {
          format = "yyyy-MM-dd HH:mm:ss";
        } else if (len < 10) {
          format = "yyyy-MM";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
        return dateFormat.parse(source);
      } catch (ParseException e) {
        LOGGER.error("日期格式转换失败,不支持" + source + "该格式", e);
      }
    }
    return null;
  }
}
