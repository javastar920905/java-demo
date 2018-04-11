package com.javastar920905.spider.link;

/**
 * @author ouzhx on 2018/4/11. 可以起过滤作用；
 */
public interface LinkFilter {
  boolean accept(String url);
}
