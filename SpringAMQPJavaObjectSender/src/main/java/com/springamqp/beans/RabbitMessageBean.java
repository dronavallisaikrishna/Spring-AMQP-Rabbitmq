package com.springamqp.beans;

public class RabbitMessageBean {

	private String message;
	private String messageFrom;
	
	public RabbitMessageBean() {
		super();
	}

	public RabbitMessageBean(String message, String messageFrom) {
		super();
		this.message = message;
		this.messageFrom = messageFrom;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageFrom() {
		return messageFrom;
	}

	public void setMessageFrom(String messageFrom) {
		this.messageFrom = messageFrom;
	}
	
}
