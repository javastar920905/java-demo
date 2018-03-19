package com.javastar920905

import com.javastar920905.config.MybatisConfig
import com.javastar920905.config.RedisConfig
import com.javastar920905.mapper.RedPacketDetailMapper
import com.javastar920905.mapper.RedPacketMapper
import com.javastar920905.outer.redis.RedisFactory
import com.javastar920905.service.impl.RedPacketServiceImpl
import com.javastar920905.service.pay.IRedPacketService
import org.spockframework.spring.xml.SpockMockFactoryBean
import org.springframework.beans.factory.FactoryBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.AnnotationConfigContextLoader
import spock.lang.Specification
import spock.mock.DetachedMockFactory

/**
 * @author ouzhx on 2018/3/19.
 *
 // Specification 描述,说明书基本概念  http://spockframework.org/spock/docs/1.1/all_in_one.html#_specification
 -- fields
 -- fixture methods
 -- feature methods
 -- helper methods

 //1 fields 实例字段(用于存储对象):
 def obj = new ClassUnderSpecification()    等价于setup()中声明的字段
 @Shared res = new VeryExpensiveResource()  等价于setupSpec() 创建的对象,可以在所有feature methods.中共享的字段 (可用于创建昂贵对象,或与其他方法交互)


  //2 Fixture Methods 固定方法(用于设置和恢复环境)
  def setup() {}          // run before every feature method  在每个特征方法前运行
  def cleanup() {}        // run after every feature method   在每个特征方法后运行
  def setupSpec() {}     // run before the first feature method 在第一个特征方法前运行
  def cleanupSpec() {}   // run after the last feature method   在最后一个特征方法后运行

  //3 Feature Methods 特征方法(核心测试方法,描述期待测试的特征)
  def "pushing an element on the stack"() {
  // blocks go here
  }-- 特征方法可以有6个代码块: setup, when, then, expect, cleanup, and where blocks.
  -- setup(给出数据,可省略);

  -- when(当条件发生,执行动作), then(限制于conditions, exception conditions, interactions,when和then通常一起)
  when:
  stack.pop()

  then:
  thrown(EmptyStackException)
  stack.empty

  -- Interactions交互代码(发布订阅  判断至少接收到一次消息)
  def "events are published to all subscribers"() {
  def subscriber1 = Mock(Subscriber)
  def subscriber2 = Mock(Subscriber)
  def publisher = new Publisher()
  publisher.add(subscriber1)
  publisher.add(subscriber2)

  when:
  publisher.fire("event")

  then:
  1 * subscriber1.receive("event")
  1 * subscriber2.receive("event")
  }-- expect 代码块,更明确指出期待结果,后面只能跟conditions 和 变量定义

  -- where 代码块一般在特征方法的最后面.用于提供数据
  def "computing the maximum of two numbers"() {
  expect:
  Math.max(a, b) == c

  where:
  a << [5, 3]
  b << [1, 9]
  c << [5, 9]
  }//4 Helper Methods http://spockframework.org/spock/docs/1.1/all_in_one.html#_helper_methods
  -- with 的使用
  with(pc) {
  vendor == "Sunny"
  clockRate >= 2333
  ram >= 406
  os == "Linux"
  }//5 扩展
  --   @Timeout Sets a timeout for execution of a feature or fixture method.
  --   @Ignore Ignores a feature method.
  --   @IgnoreRest Ignores all feature methods not carrying this annotation. Useful for quickly running just a single method.
  --   @FailsWith
  --   @Unroll 独立报道某个特征方法的错误

  //6 数据驱动测试 http://spockframework.org/spock/docs/1.1/all_in_one.html#_data_driven_testing
  expect:
  Math.max(a, b) == c

  -- where: 数据表方式
  a | b || c
  1 | 3 || 3
  7 | 4 || 7
  0 | 0 || 0

  -- where: 数据流方式
  a << [1, 7, 0]
  b << [3, 4, 0]
  c << [3, 7, 0]

  //7 交互测试 http://spockframework.org/spock/docs/1.1/all_in_one.html#_interaction_based_testing
  -- mock  方法调用次数...
  -- MockingApi.Mock() 以下都可以创建mock对象
  def subscriber = Mock(Subscriber)
  Subscriber subscriber = Mock()

  --
  1 * subscriber.receive("hello")
  |   |          |       |
  |   |          |       argument constraint
  |   |          method constraint
  |   target constraint
  cardinality

  --基数判断
  1 * subscriber.receive("hello")      // exactly one call
  0 * subscriber.receive("hello")      // zero calls
  (1..3) * subscriber.receive("hello") // between one and three calls (inclusive)
  (1.._) * subscriber.receive("hello") // at least one call
  (_..3) * subscriber.receive("hello") // at most three calls
  _ * subscriber.receive("hello")      // any number of calls, including zero   (rarely needed; see 'Strict Mocking')

  --调用方法目标交互
  1 * subscriber.receive("hello") // a call to 'subscriber'
  1 * _.receive("hello")          // a call to any mock object

  --参数交互
  1 * subscriber.receive("hello")     // an argument that is equal to the String "hello"
  1 * subscriber.receive(!"hello")    // an argument that is unequal to the String "hello"
  1 * subscriber.receive()            // the empty argument list (would never match in our example)
  1 * subscriber.receive(_)           // any single argument (including null)
  1 * subscriber.receive(*_)          // any argument list (including the empty argument list)
  1 * subscriber.receive(!null)       // any non-null argument
  1 * subscriber.receive(_ as String) // any non-null argument that is-a String
  1 * subscriber.receive({ it.size() > 3 }) // an argument that satisfies the given predicate  (here: message length is greater than 3)

  // 8 spring 集成 http://spockframework.org/spock/docs/1.1/all_in_one.html#_spring_module

 */
@ContextConfiguration(classes = MybatisConfig.class)
class DetachedJavaConfig extends Specification {
    //DetachedMockFactory and the SpockMockFactoryBean spring 集成提供的对象

    @Configuration
    static class ServiceConfig {
        def mockFactory = new DetachedMockFactory()

        @Bean
        IRedPacketService redPacketServiceSpy() {
            return mockFactory.Spy(RedPacketServiceImpl)
        }

        @Bean
        RedPacketMapper redPacketMapperMock() {
            return mockFactory.Mock(RedPacketMapper)
        }

        @Bean
        RedPacketDetailMapper redPacketDetailMapperMock() {
            return mockFactory.Mock(RedPacketDetailMapper)
        }

        /* @Bean
         IRedPacketService redPacketServiceMock() {
             return mockFactory.Mock(IRedPacketService)
         }

         @Bean
         IRedPacketService redPacketServiceStub() {
             return mockFactory.Stub(IRedPacketService)
         }

         @Bean
         FactoryBean<IRedPacketService> alternativeMock() {
             return new SpockMockFactoryBean(IRedPacketService)
         }*/
    }


    @Autowired
    IRedPacketService redPacketServiceSpy
    @Autowired
    RedPacketMapper redPacketMapperMock

}
