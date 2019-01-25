package com.javastar920905.web.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.javastar920905.entity.domain.RedPacket;
import com.javastar920905.service.pay.IRedPacketService;
import com.javastar920905.validator.RedPacketValidator;
import jodd.util.MimeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ouzhx on 2018/3/6.
 */
@RestController
@RequestMapping("/")
public class Index {
  /*
   * @Autowired private IRedPacketService redPacketService;
   */

  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.setValidator(new RedPacketValidator());
  }

  @GetMapping("/")
  public String hello(HttpSession session) {
    session.setAttribute("web", "web project attribute");
    String web2Attr = "无法获取 web2项目的session,添加spring boot 热部署测试";
    if (session.getAttribute("web2") != null) {
      web2Attr = (String) session.getAttribute("web2");
    }
    return "hello " + web2Attr;
  }

  /**
   * 发红包 测试:通过
   *
   * http://localhost:8080/giveRedPacket?userId=1&money=2&packetSize=2
   * 
   * @param redPacket
   * @param bindingResult
   * @return produces = MimeTypes.MIME_APPLICATION_JSON, consumes = MimeTypes.MIME_APPLICATION_JSON
   */
  @GetMapping(value = "/giveRedPacket")
  public String giveRedPacket(@Validated @ModelAttribute RedPacket redPacket,
      @RequestParam Map<String, String> allRequestParams, BindingResult bindingResult) {

    // 请求转换为对象技巧
    JSONObject redPacketJson = JSONObject
        .parseObject(JSON.toJSONString(allRequestParams, SerializerFeature.WriteMapNullValue));

    // 数据校验处理
    if (bindingResult.hasErrors()) {
      return "fail";
    }
    try {
      /*
       * JSONObject jsonObject = redPacketService.giveRedPacket(redPacket); return
       * jsonObject.toJSONString();
       */
      return "success";
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "fail";
  }

}
