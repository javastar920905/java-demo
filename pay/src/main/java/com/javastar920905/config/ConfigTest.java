package com.javastar920905.config;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.javastar920905.entity.domain.User;
import com.javastar920905.mapper.UserMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * @author ouzhx on 2018/3/6.
 */
public class ConfigTest {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(MybatisConfig.class);


    UserMapper userMapper = context.getBean(UserMapper.class);
    // 初始化 影响行数
    int result = 0;
    // 初始化 User 对象
    User user = new User();

    // 插入 User (插入成功会自动回写主键到实体类,表名必须为小写)
    user.setName("ouzhx");
    user.setId("123456");
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
