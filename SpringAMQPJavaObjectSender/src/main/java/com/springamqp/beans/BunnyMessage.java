package com.springamqp.beans;

public class BunnyMessage {

	private String bunnyName;
	
	public BunnyMessage() {
		super();
	}

	public BunnyMessage(String bunnyName) {
		super();
		this.bunnyName = bunnyName;
	}

	public String getBunnyName() {
		return bunnyName;
	}

	public void setBunnyName(String bunnyName) {
		this.bunnyName = bunnyName;
	}
	
	
}
