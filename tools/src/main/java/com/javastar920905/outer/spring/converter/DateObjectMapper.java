package com.javastar920905.outer.spring.converter;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by ouzhx on 2016/10/10.
 * 用于后台返回前端的日期格式数据转换
 */
public class DateObjectMapper extends ObjectMapper {
    public DateObjectMapper(){
        setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
}
