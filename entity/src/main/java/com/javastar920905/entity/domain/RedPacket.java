package com.javastar920905.entity.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

/*
 * @Data lombok 插件不生效所以注释了
 * 
 * @Accessors(chain = true)
 */
public class RedPacket implements Serializable{


  private String id;
  private String userId;
  private Double money;
  private Double restMoney;
  private java.sql.Timestamp expireTime;
  private java.sql.Timestamp createDate;
  private Integer packetSize;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Double getMoney() {
    return money;
  }

  public void setMoney(Double money) {
    this.money = money;
  }

  public Timestamp getExpireTime() {
    return expireTime;
  }

  public void setExpireTime(Timestamp expireTime) {
    this.expireTime = expireTime;
  }

  public Timestamp getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Timestamp createDate) {
    this.createDate = createDate;
  }

  public Integer getPacketSize() {
    return packetSize;
  }

  public void setPacketSize(Integer packetSize) {
    this.packetSize = packetSize;
  }

  public Double getRestMoney() {
    return restMoney;
  }

  public void setRestMoney(Double restMoney) {
    this.restMoney = restMoney;
  }
}
