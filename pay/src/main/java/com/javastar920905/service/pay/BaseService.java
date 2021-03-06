package com.javastar920905.service.pay;

import com.javastar920905.mapper.RedPacketDetailMapper;
import com.javastar920905.mapper.RedPacketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ouzhx on 2018/3/7. 当前类专门用于注入依赖
 */
@Service
public class BaseService {
  @Autowired
  protected RedPacketMapper redPacketMapper;
  @Autowired
  protected RedPacketDetailMapper redPacketDetailMapper;
}
