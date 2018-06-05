package com.javastar920905.web2.controller;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;
import com.javastar920905.oss.alibaba.AliOSSConstants;
import com.javastar920905.oss.alibaba.AliOSSFileManageUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javastar920905.outer.StringUtil;

/**
 * @author ouzhx on 2018/3/6.
 */
@RestController
@RequestMapping("/")
public class Index {
  @GetMapping("/")
  public String hello(HttpSession session) {
    session.setAttribute("web2", "web2 project attribute");
    String webAttr = "无法获取 web项目的session";
    if (session.getAttribute("web") != null) {
      webAttr = (String) session.getAttribute("web");
    }
    return "hello " + webAttr;
  }

  @GetMapping(value = "/list/{pageNum}")
  public JSONArray listFiles(@PathVariable Integer pageNum) {
    JSONArray array = new JSONArray();
    AliOSSConstants.bucketName = "integratedwall";
    array.addAll(AliOSSFileManageUtils.listFiles("books/3DSea"));

    return array;
  }
}
