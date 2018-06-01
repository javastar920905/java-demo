package com.javastar920905.spider.task.article;

import com.javastar920905.spider.constants.ArticleConstants;
import org.apache.commons.lang3.StringUtils;

/**
 * @author ouzhx on 2018/6/1.
 */
public class ArticleSpiderFactory {
  /**
   * 根据url识别平台
   * 
   * @return
   */
  private String getArticleSourceByUrl(String sourceUrl) {


    if (sourceUrl.contains("")) {
      return ArticleConstants.ArticleSource.tencent.name();
    } else if (sourceUrl.contains("")) {
      return ArticleConstants.ArticleSource.toutiao.name();
    } else if (sourceUrl.contains("")) {
      return ArticleConstants.ArticleSource.an36.name();
    } else {
      System.err.println("目前不支持此平台");
      return "目前不支持此平台";
    }
  }

  /**
   * 通过url自动选择 平台实现
   * 
   * @param sourceUrl
   * @return
   */
  ArticleSpider getArticleSpider(String sourceUrl) {
    if (StringUtils.isEmpty(sourceUrl)) {
      System.err.println("url 不能为空");
      return null;
    }
    String source = getArticleSourceByUrl(sourceUrl);
    if (ArticleConstants.ArticleSource.tencent.name().equals(source)) {
      return new TencentArticleSpider();
    } else if (ArticleConstants.ArticleSource.toutiao.name().equals(source)) {
      return null;
    } else if (ArticleConstants.ArticleSource.an36.name().equals(source)) {
      return null;
    } else {
      System.err.println("目前不支持此平台");
      return null;
    }
  };
}
