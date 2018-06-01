package com.javastar920905.spider.task.article;


import com.javastar920905.spider.entity.Article;

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

}
