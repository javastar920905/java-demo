package com.javastar920905.spider.controller;

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
    return "hello ";
  }


}
