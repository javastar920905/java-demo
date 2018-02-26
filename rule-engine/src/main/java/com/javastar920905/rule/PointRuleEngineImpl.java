package com.javastar920905.rule;

import com.javastar920905.entity.Demo1;
import com.javastar920905.entity.PointDomain;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.IOException;

/**
 * 规则引擎实现类
 * 
 * @author ouzhx on 2018/2/26.
 *
 *         偷懒用网上现有代码了 http://blog.csdn.net/quzishen/article/details/6163012
 */
public class PointRuleEngineImpl implements PointRuleEngine {
  private KieServices kieServices;
  private KieContainer kieContainer;

  /**
   * 初始化规则引擎
   */
  @Override
  public void initEngine() {
    // 设置时间格式
    System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
    kieServices = KieServices.Factory.get();
    kieContainer = kieServices.newKieClasspathContainer();
  }

  /**
   * 刷新规则引擎中的规则
   */
  @Override
  public void refreshEnginRule() {
    initEngine();
  }

  /**
   * 执行规则引擎
   *
   * @param pointDomain 积分Fact
   */
  @Override
  public void executeRuleEngine(PointDomain pointDomain) {
    KieSession kieSession = kieContainer.newKieSession("helloWorldSession");

    // 准备参数
    kieSession.insert(pointDomain);

    // 然后通过kSession.fireAllRules方法来通知规则引擎执行规则。
    kieSession.fireAllRules();
    kieSession.dispose();
  }

  public KieServices getKieServices() {
    return kieServices;
  }

  public KieContainer getKieContainer() {
    return kieContainer;
  }
}
