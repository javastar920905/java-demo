package com.javastar920905.web2.config;
/*

import com.alibaba.druid.pool.DruidDataSource;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.github.pagehelper.PageHelper;
import com.core.interceptor.mybatis.AuditingInterceptor;
import com.core.interceptor.mybatis.SyncInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.util.Properties;
*/


/**
 * mybatis 配置
 *
 * @author ouzhx on 2017/5/19
 */
/*@Configuration
@EnableTransactionManagement
@EnableApolloConfig*/
public class MybatisConfig {

 /* @Bean
  public DruidDataSource dataSource(@Value("${db.url}") String url,
      @Value("${db.username}") String username,
      @Value("${db.password}") String password) {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setUrl(url);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    Properties properties = new Properties();
    properties.setProperty("druid.initConnectionSqls", "set names utf8mb4;");
    dataSource.setConnectProperties(properties);
    return dataSource;
  }

  *//**
   * 事务管理器
   *
   * @return DataSourceTransactionManager
   *//*
  @Bean
  public DataSourceTransactionManager dataSourceTransactionManager(
      ApplicationContext applicationContext) {
    DataSourceTransactionManager manager = new DataSourceTransactionManager();
    manager.setDataSource(applicationContext.getBean("dataSource", DruidDataSource.class));

    return manager;
  }

  *//**
   * spring与mybatis整合配置，扫描所有dao
   *
   * @return MapperScannerConfigurer
   *//*
  @Bean
  public MapperScannerConfigurer mapperScannerConfigurer() {
    MapperScannerConfigurer configurer = new MapperScannerConfigurer();
    configurer.setBasePackage("com.cms.persistent.mapper");
    configurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");

    return configurer;
  }

  *//**
   * mybatis文件配置，扫描所有mapper文件
   *
   * @return SqlSessionFactoryBean
   *//*
  @Bean
  public SqlSessionFactoryBean sqlSessionFactoryBean(ApplicationContext applicationContext)
      throws IOException {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(applicationContext.getBean("dataSource", DruidDataSource.class));

    org.apache.ibatis.session.Configuration mybatisSessionConfig =
        new org.apache.ibatis.session.Configuration();
    mybatisSessionConfig.setMapUnderscoreToCamelCase(true);
   *//* factoryBean.setTypeAliasesPackage("com.domain.common");
    factoryBean.setTypeAliasesPackage("com.domain.recruitment");
    factoryBean.setTypeAliasesPackage("com.crm.persistent.entity.vo");*//*
    factoryBean.setConfiguration(mybatisSessionConfig);
    // 添加插件
    PageHelper pageHelper = new PageHelper();
    Properties properties = new Properties();
    properties.setProperty("dialect", "mysql");
    pageHelper.setProperties(properties);
    factoryBean.setPlugins(
        new Interceptor[] {pageHelper, new AuditingInterceptor(), new SyncInterceptor()});

    factoryBean
        .setMapperLocations(applicationContext.getResources("classpath:conf/mybatis/mapper/*.xml"));
    return factoryBean;
  }*/
}
