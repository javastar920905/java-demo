package com.javastar920905.submail.lib;

import com.javastar920905.submail.config.AppConfig;
import com.javastar920905.submail.lib.base.ISender;
import com.javastar920905.submail.lib.base.Sender;

import java.util.Map;



/**
 * A Sender class define the message mode to send HTTP request.
 * 
 * @see ISender
 * @see Sender
 * @version 1.0 at 2014/10/28
 * */
public class Message extends Sender {

	private static final String API_SEND =API_HOST+  "/message/send.json";
	private static final String API_XSEND =API_HOST+  "/message/xsend.json";
	private static final String API_SUBSCRIBE = API_HOST+ "/addressbook/message/subscribe.json";
	private static final String API_UNSUBSCRIBE = API_HOST+ "/addressbook/message/unsubscribe.json";

	public Message(AppConfig config) {
		this.config = config;
	}

	@Override
	public boolean send(Map<String, Object> data) {
		return request(API_SEND, data);
	}

	@Override
	public boolean xsend(Map<String, Object> data) {
		return request(API_XSEND, data);
	}

	@Override
	public boolean subscribe(Map<String, Object> data) {
		// TODO Auto-generated method stub
		return request(API_SUBSCRIBE, data);
	}

	@Override
	public boolean unsubscribe(Map<String, Object> data) {
		// TODO Auto-generated method stub
		return request(API_UNSUBSCRIBE, data);
	}
}
