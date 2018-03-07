package com.javastar920905.entity.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class RedPacketDetail implements Serializable{
  private Long id;
  private String redPacketId;
  private String oepnId;
  private String nickName;
  private Double money;
  private java.sql.Timestamp createDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRedPacketId() {
    return redPacketId;
  }

  public void setRedPacketId(String redPacketId) {
    this.redPacketId = redPacketId;
  }

  public String getOepnId() {
    return oepnId;
  }

  public void setOepnId(String oepnId) {
    this.oepnId = oepnId;
  }

  public Double getMoney() {
    return money;
  }

  public void setMoney(Double money) {
    this.money = money;
  }

  public Timestamp getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Timestamp createDate) {
    this.createDate = createDate;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }
}
