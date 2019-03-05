package com.gupao.dal.dao;

public class ArticleWithBLOBs extends Article {
    private String articleContent;

    private String articleContentHtml;

    private String articleContentFullHtml;

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }

    public String getArticleContentHtml() {
        return articleContentHtml;
    }

    public void setArticleContentHtml(String articleContentHtml) {
        this.articleContentHtml = articleContentHtml == null ? null : articleContentHtml.trim();
    }

    public String getArticleContentFullHtml() {
        return articleContentFullHtml;
    }

    public void setArticleContentFullHtml(String articleContentFullHtml) {
        this.articleContentFullHtml = articleContentFullHtml == null ? null : articleContentFullHtml.trim();
    }
}