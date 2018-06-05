package com.javastar920905.spider.task.article;

import com.javastar920905.outer.JSONUtil;
import com.javastar920905.spider.entity.Article;
import org.apache.commons.lang3.StringUtils;

/**
 * @author ouzhx on 2018/6/1.
 */
public class ArticleSpiderFactory {

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

    if (sourceUrl.contains("//www.toutiao.com")) {
      return new TouTiaoArticleSpider();
    } else if (sourceUrl.contains("//36kr.com/")) {
      return new Ke36ArticleSpider();
    } else if (sourceUrl.contains("//news.qq.com/") || sourceUrl.contains("//ent.qq.com")) {
      return new QQArticleSpider();
    } else if (sourceUrl.contains("//new.qq.com/")) {
      return new TencentArticleSpider();
    } else if (sourceUrl.contains("//ly.qq.com/")) {
      return new QQLyArticleSpider();
    } else if (sourceUrl.contains("//kuaibao.qq.com")) {
      return new QQKuaiBoArticleSpider();
    } else {
      System.err.println("目前不支持此平台");
      return null;
    }
  }

  public static void main(String[] args) {
    // 36氪测试通过
    String ke36SourceUrl = "http://36kr.com/video/7363";
    // 头条测试通过
    String toutiaoSourceUrl = "https://www.toutiao.com/a6563426372201480707/";
    // qq新闻 测试通过
    String tencentSourceUrl = "https://news.qq.com/a/20180605/015604.htm";
    // 腾讯新闻-国际新闻,法制新闻 测试通过
    String tencentSourceUrl2 = "http://new.qq.com/omn/20180605/20180605A0IQSN.html";
    // 腾讯滚动新闻 测试通过
    String tencentSourceUrl3 = "http://ent.qq.com/a/20180605/025639.htm";
    // qq 新闻哥- 快播新闻 测试通过
    String tencentSourceUrl4 = "http://kuaibao.qq.com/s/20180528A1KVHN00";
    // qq 旅游新闻 测试通过
    String tencentSourceUrl5 = "http://ly.qq.com/a/20180605/029075.htm";

    String sourceUrl = tencentSourceUrl;
    ArticleSpider spider = new ArticleSpiderFactory().getArticleSpider(sourceUrl);

    Article article = spider.getAriticle(sourceUrl);
    System.out.println(JSONUtil.parseObjectToJSONObject(article).toJSONString());
  }
}
