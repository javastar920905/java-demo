package com.javastar920905;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.javastar920905.config.MybatisConfig;
import com.javastar920905.entity.domain.RedPacket;
import com.javastar920905.entity.domain.User;
import com.javastar920905.mapper.UserMapper;
import com.javastar920905.service.IRedPacketService;
import com.javastar920905.util.StringUtil;

/**
 * @author ouzhx on 2018/3/6.
 */
public class ConfigTest {
  AnnotationConfigApplicationContext context = null;

  @Before
  public void before() {
    context = new AnnotationConfigApplicationContext(MybatisConfig.class);
  }


  @Test
  public void giveRedPacketTest() {
    IRedPacketService service = context.getBean(IRedPacketService.class);


    // 发红包
    RedPacket redPacket = new RedPacket();
    redPacket.setPacketSize(5);
    redPacket.setId(redPacketId);
    redPacket.setUserId("ouzhx");
    redPacket.setMoney(5d);
    redPacket.setCreateDate(new Timestamp(System.currentTimeMillis()));
    redPacket.setExpireTime(new Timestamp(System.currentTimeMillis() + 1000000000));
    try {
      JSONObject jsonObject = service.giveRedPacket(redPacket);

      System.out.println(jsonObject.toJSONString());
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * 发红包接口测试
   *
   */
  String redPacketId = "testRedPacketId37";

  /**
   * 多线程模拟抢红包
   */
  @Test
  public void bookingRedPacketTest() {
    giveRedPacketTest();
    IRedPacketService service = context.getBean(IRedPacketService.class);


    int threadNums = 100;
    CountDownLatch countDownLatch = new CountDownLatch(threadNums);
    for (int i = 0; i < threadNums; i++) {
      new Thread(() -> {
        JSONObject jsonObject =
            service.bookingRedPacket(StringUtil.generateUUID(), "欧besos", redPacketId);

        System.out.println(jsonObject.toJSONString());
        countDownLatch.countDown();
      }).start();
    }

    try {
      countDownLatch.await();

      // 给消费队列留时间
      TimeUnit.SECONDS.sleep(60 * 2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }



  }

  /**
   * 用户接口测试
   *
   */
  @Test
  public void userTest() {
    UserMapper userMapper = context.getBean(UserMapper.class);
    // 初始化 影响行数
    int result = 0;
    // 初始化 User 对象
    User user = new User();

    // 插入 User (插入成功会自动回写主键到实体类,表名必须为小写)
    user.setName("ouzhx");
    user.setId(StringUtil.generateUUID());
    result = userMapper.insert(user);

    // 更新 User
    user.setName("ouzhx");
    result = userMapper.updateById(user);

    // 查询 User
    User exampleUser = userMapper.selectById(user.getId());

    // 查询姓名为‘张三’的所有用户记录
    List<User> userList = userMapper.selectList(new EntityWrapper<User>().eq("name", "ouzhx"));

    // 删除 User
    result = userMapper.deleteById(user.getId());

  }
}
