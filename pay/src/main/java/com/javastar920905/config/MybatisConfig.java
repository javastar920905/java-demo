package com.javastar920905.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis 配置
 *
 * @author ouzhx on 2017/5/19
 */
@Configuration
// spring bean 扫描注册
@ComponentScan("com.javastar920905")
// todo 扫描首页mapper 接口,该包下面的mapper接口都不需要@mapper标识了
@MapperScan("com.javastar920905.mapper")
@EnableTransactionManagement
@Import({RedisConfig.class, PropertiesConfig.class, RabbitConfig.class})
public class MybatisConfig {


  @Bean
  public DruidDataSource dataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setUrl(PropertiesConfig.properties.getProperty("db.url"));
    dataSource.setUsername(PropertiesConfig.properties.getProperty("db.username"));
    dataSource.setPassword(PropertiesConfig.properties.getProperty("db.password"));
    PropertiesConfig.properties.setProperty("druid.initConnectionSqls", "set names utf8mb4;");
    dataSource.setConnectProperties(PropertiesConfig.properties);
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
   * <p>
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

