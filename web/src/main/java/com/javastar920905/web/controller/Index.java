package com.javastar920905.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ouzhx on 2018/3/6.
 */
@RestController
@RequestMapping("/")
public class Index {
  @GetMapping("/")
  public String hello(HttpSession session) {
    session.setAttribute("web", "web project attribute");
    String web2Attr = "无法获取 web2项目的session";
    if (session.getAttribute("web2") != null) {
      web2Attr = (String) session.getAttribute("web2");
    }
    return "hello " + web2Attr;
  }
}
