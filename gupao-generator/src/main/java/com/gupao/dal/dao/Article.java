package com.gupao.dal.dao;

public class Article {
    private Long autoId;

    private String articleId;

    private String articleType;

    private String platform;

    private String author;

    private String title;

    private String articleTag;

    private String sourceUrl;

    private String publishTime;

    private String createTime;

    private Byte isOrigin;

    private String relatedArticleId;

    public Long getAutoId() {
        return autoId;
    }

    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId == null ? null : articleId.trim();
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType == null ? null : articleType.trim();
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getArticleTag() {
        return articleTag;
    }

    public void setArticleTag(String articleTag) {
        this.articleTag = articleTag == null ? null : articleTag.trim();
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl == null ? null : sourceUrl.trim();
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime == null ? null : publishTime.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Byte getIsOrigin() {
        return isOrigin;
    }

    public void setIsOrigin(Byte isOrigin) {
        this.isOrigin = isOrigin;
    }

    public String getRelatedArticleId() {
        return relatedArticleId;
    }

    public void setRelatedArticleId(String relatedArticleId) {
        this.relatedArticleId = relatedArticleId == null ? null : relatedArticleId.trim();
    }
}