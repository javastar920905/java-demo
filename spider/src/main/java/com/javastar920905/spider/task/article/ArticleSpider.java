package com.javastar920905.spider.task.article;


import com.javastar920905.spider.entity.Article;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author ouzhx on 2018/6/1.
 */
public interface ArticleSpider {

  /**
   * 爬取外部文章
   * 
   * @param sourceUrl
   * @return
   */
  Article getAriticle(String sourceUrl);

  /**
   * 添加公共请求头
   * 
   * @param request
   */
  default void addCommonReqHeader(HttpRequestBase request) {
    request.addHeader("User-Agent",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
    request.addHeader("Accept", "*/*");
    request.addHeader("Accept-Encoding", "gzip, deflate");
    request.addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
    request.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
    request.setProtocolVersion(HttpVersion.HTTP_1_0);
    request.addHeader("Content-Type", "text/html; charset=GB2312");
  }

  /**
   * get 请求
   * 
   * @param url
   * @return
   */
  default String doGet(String url) {
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    HttpGet request = new HttpGet(url);
    addCommonReqHeader(request);

    try {
      HttpResponse response = httpClient.execute(request);
      return EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

}
