package com.javastar920905.submail.lib;



import com.javastar920905.submail.config.AppConfig;
import com.javastar920905.submail.config.MailConfig;
import com.javastar920905.submail.config.MessageConfig;
import com.javastar920905.submail.lib.base.ISender;
import com.javastar920905.submail.lib.base.SenderWapper;

/**
 * A SenderWapper class as decoration class for user to subscribe and unsubscribe by mail.
 * User can set the basic information of request by included several methods.
 * Then,send the request data by a mode of mail.
 * 
 * @see Mail
 * 
 * @version 1.0 at 2014/10/28
 * */
public class ADDRESSBOOKMail extends SenderWapper {

  public static final String ADDRESS = "address";
  public static final String TARGET = "target";
	/**
	 * If the mode is mail,it's an instance of {@link MailConfig}. If the mode
	 * is message,it's an instance of {@link MessageConfig}
	 * */
	protected AppConfig config = null;

	public ADDRESSBOOKMail(AppConfig config) {
		this.config = config;
	}
	
	public void setAddress(String address, String name){
		requestData.put(ADDRESS, name + "<" + address + ">");
	}
	
	public void setAddressbook(String target){
		requestData.put(TARGET, target);
	}
	@Override
	public ISender getSender() {
		return new Mail(this.config);
	}
	
	public void subscribe(){
		getSender().subscribe(requestData);
	}
	
	public void unsubscribe(){
		getSender().unsubscribe(requestData);
	}
}
