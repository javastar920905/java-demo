package com.javastar920905.submail.lib.base;



import com.javastar920905.submail.entity.DataStore;

public abstract class SenderWapper {

  protected DataStore requestData = new DataStore();

  public abstract ISender getSender();
}
