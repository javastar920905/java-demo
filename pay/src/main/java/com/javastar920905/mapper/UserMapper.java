package com.javastar920905.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.javastar920905.entity.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ouzhx on 2018/3/6.
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
