package com.javastar920905.rule;

import com.javastar920905.entity.Demo1;
import com.javastar920905.entity.PointDomain;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author ouzhx on 2018/2/26.
 *         <p>
 *         drools 官方文档 (基于版本 7.6.0.Final)
 *         https://docs.jboss.org/drools/release/7.6.0.Final/drools-docs/html_single/index.html
 * 
 * 
 *         系列教程文档 http://blog.csdn.net/mxlmxlmxl33/article/details/78783416
 */
public class DroolsTest {
  public static void main(String[] args) {
    test();

    System.out.println("======>demo2 积分功能测试");
    jifenTest();
  }

  /**
   * 在 resources/META-INF 目录下创建 kmodule.xml 文件，
   * <p>
   * kmodule.xml 用来描述知识库资源的选择及知识库与会话的配置
   */
  public static void test() {
    // 设置时间格式
    System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");

    // 执行规则时必备的两个对象
    KieServices kieServices = KieServices.Factory.get();
    KieContainer kieContainer = kieServices.newKieClasspathContainer();

    // 利用kieContainer对象创建一个新的KieSession 参数是kmodule.xml 中定义的ksession名称
    KieSession kieSession = kieContainer.newKieSession("helloWorldSession");

    // 准备参数
    Demo1 demo1 = new Demo1();
    demo1.setMsg("bye");
    kieSession.insert(demo1);

    // 然后通过kSession.fireAllRules方法来通知规则引擎执行规则。
    kieSession.fireAllRules();
    kieSession.dispose();


    /**
     * // 系列教程文档 http://blog.csdn.net/mxlmxlmxl33/article/details/78783416
     * (KieServices、KieContainer、KieSession对象介绍)
     * 
     * KIE项目生命周期 一个Drools应用项目其实就是一个KIE项目，KIE的生命周期其实就是Drools和jBPM这些项目的生命周期。
     * 
     * KIE项目生命周期包含：编写（Author）、构建（Build）、测试（Test）、部署（Deploy）、使用（Utilize）、执行（Run）、交互（Work）、管理（Manage）。
     * 
     * 编写：编写就是编写规则文件或者流程文件； 构建：就是构建一个可以发布部署的组件，在KIE中就是构建一个jar文件； 测试：在部署到应用程序之前需要对规则或者流程进行测试；
     * 部署：就是将jar部署到应用程序，KIE利用Maven仓库来进行发布和部署；
     *
     * 使用：就是加载jar文件，并通过KieContainer对jar文件进行解析，然后创建KieSession；
     * 执行：系统通过KieSession对象的API跟Drools引擎进行交互，执行规则或者流程； 交互：用户通过命令行或者UI跟引擎进行交互；
     * 管理：管理KieSession或者KieContainer对象。
     * 
     * drools的标准存储结构就是在src/main/resources文件夹下面存储规则文件（包括DRL文件和Excel文件），然后在META-INF文件夹下面创建一个kmodule.xml文件用来存储规则定义声明。
     */
  }

  /**
   * 积分测试 demo http://blog.csdn.net/quzishen/article/details/6163012
   *
   * TODO 该功能未完成(api使用不熟练)
   * 
   * @throws Exception
   */
  public static void jifenTest() {
    try {
      PointRuleEngine pointRuleEngine = new PointRuleEngineImpl();
      boolean isInit = false;
      while (true) {
        System.out.println("请执行您的操作(s/e/r):");
        InputStream is = System.in;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String input = br.readLine();

        if (null != input && "s".equals(input)) {
          System.out.println("初始化规则引擎...");
          pointRuleEngine.initEngine();
          isInit = true;
          System.out.println("初始化规则引擎结束.");
        } else if ("e".equals(input)) {
          if (!isInit) {
            System.out.println("请先输入s初始化规则引擎!");
            continue;
          }
          final PointDomain pointDomain = new PointDomain();
          pointDomain.setUserName("hello kity");
          pointDomain.setBackMondy(100d);
          pointDomain.setBuyMoney(500d);
          pointDomain.setBackNums(1);
          pointDomain.setBuyNums(5);
          pointDomain.setBillThisMonth(5);
          pointDomain.setBirthDay(true);
          pointDomain.setPoint(0L);

          pointRuleEngine.executeRuleEngine(pointDomain);

          System.out.println("执行完毕BillThisMonth：" + pointDomain.getBillThisMonth());
          System.out.println("执行完毕BuyMoney：" + pointDomain.getBuyMoney());
          System.out.println("执行完毕BuyNums：" + pointDomain.getBuyNums());

          System.out.println("执行完毕规则引擎决定发送积分：" + pointDomain.getPoint());
        } else if ("r".equals(input)) {
          System.out.println("刷新规则文件...");
          pointRuleEngine.refreshEnginRule();
          System.out.println("刷新规则文件结束.");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
