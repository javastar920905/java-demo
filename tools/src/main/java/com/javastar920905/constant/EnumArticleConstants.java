package com.javastar920905.constant;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author ouzhx on 2017/12/21.
 */
public interface EnumArticleConstants {
  /**
   * 文章状态
   */
  @Getter
  enum ArticleStatus {
    draft("草稿", 0), audit("审核中", 10), noPass("不通过", 20), toBePublished("待发布", 25), published("已发布",
        30), recall("已撤回", 40), deleted("已删除", -1);

    public String desc;
    public int value;

    /**
     * 根据value获取ArticleStatus
     * 
     * @param value
     * @return
     */
    public static ArticleStatus getByValue(int value) {
      return Arrays.stream(ArticleStatus.values())
          .filter(articleStatus -> articleStatus.value == value).findAny().get();
    }

    ArticleStatus(String desc, int value) {
      this.desc = desc;
      this.value = value;
    }
  }


  /**
   * 百度语音内置发音人列表
   */
  enum baiduVoiceSpeaker {
    female("女生", "0"), male("男生", "1"), emotionMale("情感男生", "3"), emotionFemale("情感女生", "4");
    public String name;
    public String value;

    baiduVoiceSpeaker(String name, String value) {
      this.name = name;
      this.value = value;
    }
  }
}
