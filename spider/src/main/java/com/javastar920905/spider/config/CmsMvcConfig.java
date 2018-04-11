package com.javastar920905.spider.config;

import java.util.LinkedList;
import java.util.List;

import com.javastar920905.outer.spring.converter.DateConverter;
import com.javastar920905.outer.spring.converter.DateObjectMapper;
import com.javastar920905.outer.spring.converter.DoubleConverter;
import com.javastar920905.outer.spring.converter.FloatConverter;
import com.javastar920905.outer.spring.converter.IntConverter;
import com.javastar920905.spider.config.inteceptor.AdditionalCookiesInterceptor;
import com.javastar920905.spider.config.servlet.CustomDispatcherServlet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



/**
 * @author ouzhx on 2017/5/12.
 */
@Configuration
@EnableWebMvc
public class CmsMvcConfig extends WebMvcConfigurerAdapter {

  @Bean
  public DispatcherServlet dispatcherServlet(WebApplicationContext webApplicationContext) {
    return new CustomDispatcherServlet(webApplicationContext);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new AdditionalCookiesInterceptor());
    super.addInterceptors(registry);
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/api-doc/**").addResourceLocations("classpath:templates/api-doc/");
    registry.addResourceHandler("/build/**").addResourceLocations("classpath:static/build/");
    registry.addResourceHandler("/ueditor/**").addResourceLocations("classpath:static/ueditor/");
    registry.addResourceHandler("/webjars/**").addResourceLocations("/META-INF/resources/webjars/");
    registry.addResourceHandler("/favicon.ico")
        .addResourceLocations("classpath:templates/favicon.ico");
    super.addResourceHandlers(registry);
  }

  /**
   * 添加全局的注解的转换 避免在每个Date 类型字段上添加@DateTimeFormat 标签
   *
   * @return
   */
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    // 全局日期转换器 不包含json转换
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    converter.setObjectMapper(new DateObjectMapper());
    converters.add(converter);

    StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();
    List<MediaType> mediaTypes = new LinkedList<>();
    mediaTypes.add(MediaType.TEXT_PLAIN);
    mediaTypes.add(MediaType.TEXT_HTML);
    messageConverter.setSupportedMediaTypes(mediaTypes);
    converters.add(messageConverter);
  }

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(new DateConverter());
    registry.addConverter(new DoubleConverter());
    registry.addConverter(new FloatConverter());
    registry.addConverter(new IntConverter());
    super.addFormatters(registry);
  }


}
