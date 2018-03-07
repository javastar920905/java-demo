package com.javastar920905.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

/**
 * @author ouzhx on 2018/3/7.
 */
@Configuration
public class PropertiesConfig {
  /**
   * 加载classpath 下的application.properties
   **/
  public static final Properties properties = new Properties();
  static {
    Resource sources = new ClassPathResource("application.properties");
    try {
      properties.load(sources.getInputStream());
      System.out.println("application.properties 加载完成!");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
