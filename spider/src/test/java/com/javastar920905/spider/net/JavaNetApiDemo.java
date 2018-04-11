package com.javastar920905.spider.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

import com.javastar920905.spider.downloader.JavaNetUtils;
import com.javastar920905.spider.parse.RegexUtil;

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


  /**
   * 抽象类 URLConnection 是所有类的超类，它代表应用程序和 URL 之间的通信链接;直接已知子类：HttpURLConnection, JarURLConnection
   *
   * 
   * https://www.oschina.net/uploads/doc/javase-6-doc-api-zh_CN/java/net/URLConnection.html
   * URLConnection 使用步骤:
   * 
   * 通过在 URL 上调用 openConnection 方法创建连接对象。
   * 
   * 处理设置connection对象,参数和一般请求属性。
   * 
   * 使用 connect 方法建立到远程对象的实际连接。
   * 
   * 远程对象变为可用。远程对象的头字段和内容变为可访问
   */
  @Test
  public void urlConnectionTest() {
    try {
      URL url = new URL("http://www.baidu.com");
      // 返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接。
      // openConnection(Proxy proxy) 重载了代理设置方法
      URLConnection connection = url.openConnection();
      connection.addRequestProperty("word", "hello");

      connection.connect();

      System.out.println(connection.getContentEncoding());
      System.out.println(connection.getContentType());
      System.out.println(connection.getContentLength());
      System.out.println(connection.getContent());

      InputStream inputStream = connection.getInputStream();
      byte[] buffer = new byte[connection.getContentLength()];
      inputStream.read(buffer);

      System.out.println(new String(buffer));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
