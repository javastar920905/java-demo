package com.javastar920905

import com.javastar920905.config.PropertiesConfig
import com.javastar920905.config.RabbitConfig
import com.javastar920905.config.RedisConfig
import com.javastar920905.mapper.RedPacketDetailMapper
import com.javastar920905.mapper.RedPacketMapper
import com.javastar920905.service.pay.IRedPacketService
import groovy.sql.Sql
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import org.springframework.context.annotation.Import
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Controller
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.AnnotationConfigContextLoader
import spock.lang.Shared
import spock.lang.Specification

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

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
  -- 一般在then: 代码块后面(也可能用在when前面,如setup:中)
  -- mock  方法调用次数...
  -- MockingApi.Mock() 以下都可以创建mock对象
  def subscriber = Mock(Subscriber)
  Subscriber subscriber = Mock()

  --
  1 * subscriber.receive("hello")
  |   |          |       |
  |   |          |       argument constraint 参数约束
  |   |          method constraint 方法约束
  |   target constraint 调用者约束
  cardinality 基数


  --基数判断
  1 * subscriber.receive("hello")      // exactly one call,精确1次调用
  0 * subscriber.receive("hello")      // zero calls  0次调用
  (1..3) * subscriber.receive("hello") // between one and three calls (inclusive) 调用1-3次
  (1.._) * subscriber.receive("hello") // at least one call  至少一次调用
  (_..3) * subscriber.receive("hello") // at most three calls 最多3次调用
  _ * subscriber.receive("hello")      // any number of calls, including zero   调用任意次数(rarely needed; see 'Strict Mocking')

  --调用方法目标交互
  1 * subscriber.receive("hello") // a call to 'subscriber'
  1 * _.receive("hello")          // a call to any mock object

  --参数交互
  1 * subscriber.receive("hello")     // an argument that is equal to the String "hello"  参数必须是"hello"
  1 * subscriber.receive(!"hello")    // an argument that is unequal to the String "hello" 参数必须不是"hello"
  1 * subscriber.receive()            // the empty argument list (would never match in our example)  空参数
  1 * subscriber.receive(_)           // any single argument (including null) 任意单个参数
  1 * subscriber.receive(*_)          // any argument list (including the empty argument list) 任意参数个数
  1 * subscriber.receive(!null)       // any non-null argument 非空参数
  1 * subscriber.receive(_ as String) // any non-null argument that is-a String
  1 * subscriber.receive({ it.size() > 3 }) // an argument that satisfies the given predicate  (here: message length is greater than 3)


  --stubbing 打桩
  --根据参数计算结果返回不同值
  subscriber.receive(_) >> { args -> args[0].size() > 3 ? "ok" : "fail" }-- 不同调用次数返回不同数据 chain链式响应
  subscriber.receive(_) >>> ["ok", "fail", "ok"] >> { throw new InternalError() } >> "ok"
  前3次调用分别返回 "ok","fail","ok", 第4次抛出异常,其他任何调用都返回"ok"

  // 8 spring 集成 http://spockframework.org/spock/docs/1.1/all_in_one.html#_spring_module

 */
/**使用h2替换mysql,直接做隔离测试**/
@Configuration
@ContextConfiguration(loader = AnnotationConfigContextLoader, classes = [MybatisH2Config.class])
//把 ignore的配置手动注册,import 需要在@ComponentScan.Filter之前定义(剩余需要注册的非必要对象,可以mock掉)
//, PropertiesConfig.class, RabbitConfig.class rabbit mq测试时用不到就不用引入了
@Import([MockBeanConfig.class, RedisConfig.class])
@ActiveProfiles("test")
// spring bean 扫描注册
@ComponentScan(value = "com.javastar920905", excludeFilters =
        [@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class),
                @ComponentScan.Filter(type = FilterType.REGEX,
                        pattern = ["com.javastar920905.config.MybatisConfig",
                                "com.javastar920905.listener.*"
                        ])
        ])
class BaseDetachedConfig extends Specification {
    @Shared
            sql = Sql.newInstance("jdbc:h2:mem:paydb", "org.h2.Driver")

    // insert data (usually the database would already contain the data)
    def setupSpec() {
        sql.execute("create table maxdata (id int primary key, a int, b int, c int)")
        sql.execute("insert into maxdata values (1, 3, 7, 7), (2, 5, 4, 5), (3, 9, 9, 9)")
    }
    //使用spy对象前请三思(think twice before doing sth),基于真实对象
    @Autowired
    IRedPacketService redPacketService
    /**
     * 当使用MybatisH2Config时 会自动注入h2 mapper对象
     * 当使用MockBeanConfig时  自动注入mock后的mapper 对象
     * 该功能得益于spring IOC 容器特性
     */
    @Autowired
    RedPacketMapper redPacketMapper
    @Autowired
    RedPacketDetailMapper redPacketDetailMapper

    //多线程使用文档总结  http://note.youdao.com/noteshare?id=c1aa1a8700d550f5c2294d49c05d1b03&sub=CBCB97BFFB1D49B5A26E27E3E0A60A96
    @Shared
    ExecutorService threadPoolExecutor = Executors.newCachedThreadPool()
}
