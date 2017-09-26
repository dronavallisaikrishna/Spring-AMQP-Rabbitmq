package com.springamqp.modals;

import java.io.Serializable;

public class RabbitMQMessage implements Serializable{
	
	private String messageFrom;
	private String message;
	
	
	
	public  RabbitMQMessage() {
//		super();
	}



	public  RabbitMQMessage(String messageFrom, String message) {
//		super();
		this.messageFrom = messageFrom;
		this.message = message;
	}



	public String getMessageFrom() {
		return messageFrom;
	}



	public void setMessageFrom(String messageFrom) {
		this.messageFrom = messageFrom;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}
}
