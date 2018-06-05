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
public class TouTiaoArticleSpider implements ArticleSpider {
  /**
   * 爬取外部文章
   *
   * @param sourceUrl
   * @return
   */
  @Override
  public Article getAriticle(String sourceUrl) {
    String webPageString = doGet(sourceUrl);
    int startPos = webPageString.indexOf("articleInfo:") + 12;
    int endPos = webPageString.indexOf("commentInfo:");
    String jsonData = webPageString.substring(startPos, endPos);
    jsonData = jsonData.substring(0, jsonData.lastIndexOf(","));

    JSONObject jsonObject = JSONObject.parseObject(jsonData);
    Article article = new Article();
    article.setTitle(jsonObject.getString("title"));
    String contentHtml = jsonObject.getString("content");
    Optional.ofNullable(contentHtml).ifPresent(content -> {
      article.setContentHtml(contentHtml);
      article.setContent(RegexUtil.trimTag(contentHtml));
      // 还需要去除&xx;格式标签
    });
    article.setSource(ArticleConstants.ArticleSource.toutiao.name());
    article.setSourceUrl(sourceUrl);
    return article;
  }
}
