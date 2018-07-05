package com.javastar920905.submail.lib;



import com.javastar920905.submail.config.AppConfig;
import com.javastar920905.submail.lib.base.ISender;
import com.javastar920905.submail.lib.base.SenderWapper;

/**
 * A SenderWapper class as decoration class for user to subscribe and unsubscribe by message.
 * User can set the basic information of request by included several methods.
 * Then,send the request data by a mode of message.
 * 
 * @see Mail
 * 
 * @version 1.0 at 2014/10/28
 * */
public class ADDRESSBOOKMessage extends SenderWapper {

  public static final String ADDRESS = "address";
  public static final String TARGET = "target";
	protected AppConfig config = null;

	public ADDRESSBOOKMessage(AppConfig config) {
		this.config = config;
	}
	
	public void setAddress(String address){
		requestData.put(ADDRESS, address);
	}
	
	public void setAddressbook(String target){
		requestData.put(TARGET, target);
	}
	@Override
	public ISender getSender() {
		return new Message(this.config);
	}
	
	public void subscribe(){
		getSender().subscribe(requestData);
	}
	
	public void unsubscribe(){
		getSender().unsubscribe(requestData);
	}
}
