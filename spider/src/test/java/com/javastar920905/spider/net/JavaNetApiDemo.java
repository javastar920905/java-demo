package com.javastar920905.spider.net;

import com.javastar920905.spider.downloader.JavaNetUtils;
import com.javastar920905.spider.parse.RegexUtil;
import org.junit.Test;

/**
 * @author ouzhx on 2018/4/11. 使用java .net包下的api 实现爬虫
 */
public class JavaNetApiDemo {
  @Test
  public void urlSpider() {
    // 1 抓取页面
    String htmlStr = JavaNetUtils.doGet("http://www.baidu.com");
    System.out.println(htmlStr);

    // 2 解析html dom
    // 使用正则匹配图片的src内容
    String imgSrc = RegexUtil.RegexString(htmlStr, "src=\"(.+?)\"");
    // 打印结果
    System.out.println("\n\n\n\n");
    System.out.println(imgSrc);
  }
}
