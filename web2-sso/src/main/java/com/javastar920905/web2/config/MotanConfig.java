package com.javastar920905.web2.config;
/*

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.weibo.api.motan.config.springsupport.AnnotationBean;
import com.weibo.api.motan.config.springsupport.BasicRefererConfigBean;
import com.weibo.api.motan.config.springsupport.ProtocolConfigBean;
import com.weibo.api.motan.config.springsupport.RegistryConfigBean;
*/

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author ouzhx on 2017/5/11. 使用注解方式配置未生效有空再测试
 */
@Configuration
@Import(RedisConfig.class)
public class MotanConfig {
  /*public static final String BASIC_REFERER = "basicRefererConfig";

  *//**
   * 添加用到motan注解的类的包名 用到service的需要添加注解 @MotanReferer(basicReferer = "basicRefererConfig")
   *
   * @MotanReferer 路径,一般为controller 路径
   * @return AnnotationBean
   *//*
  @Bean
  public AnnotationBean annotationBean() {
    AnnotationBean annotationBean = new AnnotationBean();
    annotationBean.setPackage("com.javastar920905");
    return annotationBean;
  }

  *//**
   * 配置ProtocolConfig、RegistryConfig、BasicServiceConfig的bean对象，功能与xml配置中的protocol、registry、basicService标签一致。
   *//*
  @Bean("motanProtocol")
  public ProtocolConfigBean protocolConfig(@Value("${motan.name}") String name,
      @Value("${motan.loadBalance}") String loadBalance,
      @Value("${motan.haStrategy}") String haStrategy) {
    ProtocolConfigBean config = new ProtocolConfigBean();
    config.setDefault(true);
    config.setName(name);
    config.setLoadbalance(loadBalance);
    config.setHaStrategy(haStrategy);
   // config.setFilter("auditor_client");
    return config;
  }

  @Bean(name = "registryConfig")
  public RegistryConfigBean registryConfig(@Value("${motan.registry.name}") String name,
      @Value("${motan.registry.address}") String address,
      @Value("${motan.registry.protocol}") String protocol) {
    RegistryConfigBean registryConfig = new RegistryConfigBean();
    registryConfig.setAddress(address);
    registryConfig.setName(name);
    registryConfig.setRegProtocol(protocol);
    registryConfig.setDefault(true);
    return registryConfig;
  }

  @Bean(name = BASIC_REFERER)
  public BasicRefererConfigBean basicRefererConfigBean(
      @Value("${motan.timeout.request:30000}") int requestTimeout,
      @Value("${motan.group.recruitment}") String group) {
    BasicRefererConfigBean config = new BasicRefererConfigBean();
    config.setProtocol("motanProtocol");
    config.setRegistry("registryConfig");
    config.setGroup(group);
    config.setRequestTimeout(requestTimeout);
    config.setCheck(false);
    config.setRetries(2);
    config.setAccessLog(false);
    config.setThrowException(true);
    return config;
  }*/
}
