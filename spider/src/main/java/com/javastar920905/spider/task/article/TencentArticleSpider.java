package com.javastar920905.spider.task.article;

import java.util.Optional;

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

  /**
   * 爬取外部文章
   *
   * @param sourceUrl
   * @return
   */
  @Override
  public Article getAriticle(String sourceUrl) {
    String webPageString = doGet(sourceUrl);

    Html html = new Html(webPageString, sourceUrl);

    Article article = new Article();
    article.setTitle(html.css("div.qq_conent.clearfix > div.LEFT > h1").xpath("h1/text()").get());
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
