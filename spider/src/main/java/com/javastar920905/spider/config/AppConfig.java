package com.javastar920905.spider.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * 项目主要配置
 *
 * @author chenjun
 */
@SpringBootApplication(scanBasePackages = "com.javastar920905")
@Import({RedisConfig.class, ScheduleConfig.class})
public class AppConfig {


  public static void main(String[] args) {
    // 文字转语音 内容过长问题
    int size = 8 * 1024 * 10;
    System.setProperty("server.maxHttpHeaderSize", size + "");
    SpringApplication.run(AppConfig.class);

  }



}
