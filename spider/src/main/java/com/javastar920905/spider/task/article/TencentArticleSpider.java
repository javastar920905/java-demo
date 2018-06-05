package com.javastar920905.spider.task.article;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.javastar920905.outer.RegexUtil;
import com.javastar920905.spider.constants.ArticleConstants;
import com.javastar920905.spider.entity.Article;

import us.codecraft.webmagic.selector.Html;

/**
 * @author ouzhx on 2018/6/1.
 * 
 *         腾讯新闻爬取实现
 */
public class TencentArticleSpider implements ArticleSpider {

  public static String getHtml(HttpResponse response) {
    try {
      Header[] headers = response.getHeaders("Accept-Encoding");
      if (headers != null && headers.length > 0 && headers[0].getValue().contains("gzip")) {
        // 如果经过gzip压缩则先解压,否则直接读取
        GZIPInputStream gzin = new GZIPInputStream(response.getEntity().getContent());
        BufferedReader bin = new BufferedReader(new InputStreamReader(gzin, "GB2312"));

        StringBuffer result = new StringBuffer();
        String line;
        while ((line = bin.readLine()) != null) {
          result.append(line);
        }
        return result.toString();
      } else {
        return EntityUtils.toString(response.getEntity());
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 爬取外部文章
   *
   * @param sourceUrl
   * @return
   */
  @Override
  public Article getAriticle(String sourceUrl) {
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    HttpGet request = new HttpGet(sourceUrl);
    request.addHeader("User-Agent",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
    request.addHeader("Accept", "*/*");
    request.addHeader("Accept-Encoding", "gzip, deflate");
    request.addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
    request.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
    request.setProtocolVersion(HttpVersion.HTTP_1_0);
    request.addHeader("Content-Type", "text/html; charset=GB2312");

    HttpResponse response = null;
    try {
      response = httpClient.execute(request);
    } catch (IOException e) {
      e.printStackTrace();
    }

    String webPageString = getHtml(response);


    Html html = new Html(webPageString, sourceUrl);

    Article article = new Article();
    article.setTitle(html.css("div.qq_conent.clearfix > div.LEFT > h1").get());
    String contentHtml =
        html.css("div.qq_conent.clearfix > div.LEFT > div.content.clearfix > div.content-article")
            .get();
    Optional.ofNullable(contentHtml).ifPresent(content -> {
      article.setContentHtml(contentHtml);
      article.setContent(RegexUtil.trimTag(contentHtml));
    });
    article.setSource(ArticleConstants.ArticleSource.tencent.name());
    article.setSourceUrl(sourceUrl);
    return article;
  }



}
