package com.javastar920905.spider.task.article;

import java.util.Optional;

import com.alibaba.fastjson.JSONObject;
import com.javastar920905.outer.RegexUtil;
import com.javastar920905.spider.constants.ArticleConstants;
import com.javastar920905.spider.entity.Article;

/**
 * @author ouzhx on 2018/6/5.
 * 
 *         36 氪 文章爬取
 */
public class Ke36ArticleSpider implements ArticleSpider {
  /**
   * 爬取外部文章
   *
   * @param sourceUrl
   * @return
   */
  @Override
  public Article getAriticle(String sourceUrl) {
    String webPageString = doGet(sourceUrl);

    int startPos = webPageString.indexOf("props=") + 6;
    int endPos = webPageString.indexOf("source_type") - 2;
    String jsonData = webPageString.substring(startPos, endPos) + "}}";
    JSONObject jsonObject = JSONObject.parseObject(jsonData).getJSONObject("detailArticle|post");
    if (jsonObject == null) {
      // 目前业务不需要视频类型
      jsonObject = JSONObject.parseObject(jsonData).getJSONObject("videoDetail|video");
    }
    Article article = new Article();
    article.setTitle(jsonObject.getString("title"));
    String contentHtml = jsonObject.getString("content");
    Optional.ofNullable(contentHtml).ifPresent(content -> {
      article.setContentHtml(contentHtml);
      article.setContent(RegexUtil.trimTag(contentHtml).replaceAll("&nbsp;", ""));
    });
    article.setSource(ArticleConstants.ArticleSource.ke36.name());
    article.setSourceUrl(sourceUrl);
    return article;
  }
}
