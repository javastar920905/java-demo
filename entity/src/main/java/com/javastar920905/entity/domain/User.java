package com.javastar920905.entity.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ouzhx on 2018/3/6.
 */
@Getter
@Setter
public class User {
  private String id;
  private String name;
  private Date birthday;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }
}
