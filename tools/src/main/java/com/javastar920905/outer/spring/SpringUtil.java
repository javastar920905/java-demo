package com.javastar920905.outer.spring;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * Spring工具类
 */
public class SpringUtil {

  /**
   * 生成 PropertySourcesPlaceholderConfigurer
   *
   * @return PropertySourcesPlaceholderConfigurer
   */
  public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
    PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
    Resource serverProp = new FileSystemResource("/rencaijia/config/rencaijia.properties");
    configurer.setLocation(serverProp);
    configurer.setIgnoreResourceNotFound(true);
    configurer.setLocalOverride(true);
    return configurer;
  }
}
