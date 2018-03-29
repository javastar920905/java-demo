package com.javastar920905

import com.javastar920905.mapper.RedPacketDetailMapper
import com.javastar920905.mapper.RedPacketMapper
import com.javastar920905.service.pay.IRedPacketService
import com.javastar920905.service.pay.RedPacketServiceImpl
import org.spockframework.spring.xml.SpockMockFactoryBean
import org.springframework.beans.factory.FactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import spock.mock.DetachedMockFactory

/**
 *
 * 该类的作用: 选择性扫描 生成需要的真实对象,以及mock对象 填充到spring 容器中
 * @author ouzhx on 2018/3/19.
 *
 * 什么是mock？
 * 在软件开发的世界之外, "mock"一词是指模仿或者效仿。
 * 因此可以将“mock”理解为一个替身，替代者. 在软件开发中提及"mock"，通常理解为模拟对象或者Fake。
 * mock 对象初始化时是没有任何行为的
 *
 * 如何为spring 测试框架注入测试用的mock对象而不是真实对象?(选择性扫描对象)
 */
@Configuration
class MockBeanConfig {
    // DetachedMockFactory and the SpockMockFactoryBean spring 集成提供的对象
    def mockFactory = new DetachedMockFactory()

    //TODO 启动缺少的不必要对象 都可以在这里mock
   /* @Bean
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

    @Bean
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
