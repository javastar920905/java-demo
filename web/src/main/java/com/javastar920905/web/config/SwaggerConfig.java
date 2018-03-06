package com.javastar920905.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

/**
 * @author ouzhx on 2017/6/5.
 */
@Configuration
@EnableSwagger
public class SwaggerConfig {
  @Autowired
  private SpringSwaggerConfig springSwaggerConfig;

  @Bean
  public SwaggerSpringMvcPlugin customImplementation() {
    return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    ApiInfo apiInfo = new ApiInfo("接口文档 API", "接口文档", "/api-doc/index.html",
        "javastar920905@163.com", "无证书", "无 License URL");
    return apiInfo;
  }
}
