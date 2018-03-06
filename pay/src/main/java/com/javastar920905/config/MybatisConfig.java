package com.javastar920905.config;

import java.io.IOException;
import java.util.Properties;

import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;


/**
 * mybatis 配置
 *
 * @author ouzhx on 2017/5/19
 */
@Configuration
// todo 扫描首页mapper 接口
@MapperScan("com.javastar920905.mapper")
@EnableTransactionManagement
public class MybatisConfig {


  @Bean
  public DruidDataSource dataSource() {
    Resource sources = new ClassPathResource("application.properties");
    Properties properties = new Properties();
    try {
      properties.load(sources.getInputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }

    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setUrl(properties.getProperty("db.url"));
    dataSource.setUsername(properties.getProperty("db.username"));
    dataSource.setPassword(properties.getProperty("db.password"));
    properties.setProperty("druid.initConnectionSqls", "set names utf8mb4;");
    dataSource.setConnectProperties(properties);
    return dataSource;
  }

  /**
   * 事务管理器
   *
   * @return DataSourceTransactionManager
   */
  @Bean
  public DataSourceTransactionManager dataSourceTransactionManager(
      ApplicationContext applicationContext) {
    DataSourceTransactionManager manager = new DataSourceTransactionManager();
    manager.setDataSource(applicationContext.getBean("dataSource", DruidDataSource.class));

    return manager;
  }

  /**
   * mybatis plus文件配置，扫描所有mapper文件
   *
   * 查看mybatis plus 文档 http://mp.baomidou.com/#/install
   *
   * @return MybatisSqlSessionFactoryBean
   */
  @Bean
  public SqlSessionFactory sqlSessionFactoryBean(ApplicationContext applicationContext)
      throws Exception {
    MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
    factoryBean.setDataSource(applicationContext.getBean("dataSource", DruidDataSource.class));
    factoryBean.setTypeAliasesPackage("com.javastar920905.entity.domain");
    factoryBean.setConfiguration(mybatisConfiguration());
    factoryBean.setGlobalConfig(globalConfiguration());
    /*
     * // 添加分页插件 PageHelper pageHelper = new PageHelper(); Properties properties = new Properties();
     * properties.setProperty("dialect", "mysql"); pageHelper.setProperties(properties);
     */

    factoryBean.setPlugins(new Interceptor[] {new PaginationInterceptor(),
        new OptimisticLockerInterceptor(), new PerformanceInterceptor()});


    /*
     * 额外的mybatis maper.xml配置目录 factoryBean
     * .setMapperLocations(applicationContext.getResources("classpath:conf/mybatis/mapper/*.xml"));
     */
    return factoryBean.getObject();
  }

  @Bean
  public MybatisConfiguration mybatisConfiguration() {
    MybatisConfiguration configuration = new MybatisConfiguration();
    configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
    configuration.setMapUnderscoreToCamelCase(true);
    configuration.setJdbcTypeForNull(JdbcType.NULL);
    return configuration;
  }

  @Bean
  public GlobalConfiguration globalConfiguration() {
    GlobalConfiguration configuration = new GlobalConfiguration();
    configuration.setLogicDeleteValue("-1");
    configuration.setLogicNotDeleteValue("-1");
    configuration.setIdType(2);
    configuration.setKeyGenerator(new OracleKeyGenerator());
    configuration.setSqlInjector(new LogicSqlInjector());
    return configuration;
  }


}
