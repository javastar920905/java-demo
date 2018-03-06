package com.javastar920905.web2.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * 项目主要配置
 *
 */
@SpringBootApplication(scanBasePackages = "com.javastar920905")
@Import({RedisConfig.class, ScheduleConfig.class, SwaggerConfig.class})
public class AppConfig {


  public static void main(String[] args) {
    // 解决请求参数header 过大问题
    System.setProperty("server.port", "8082");
    int size = 8 * 1024 * 10;
    System.setProperty("server.maxHttpHeaderSize", size + "");
    SpringApplication.run(AppConfig.class);

  }



}
