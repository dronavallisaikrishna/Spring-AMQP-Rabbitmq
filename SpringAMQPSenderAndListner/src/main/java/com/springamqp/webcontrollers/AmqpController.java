package com.springamqp.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springamqp.senders.MessageSender;

@RestController
public class AmqpController {

	@Autowired
	private MessageSender sender;
	
	@RequestMapping(value="/send",method=RequestMethod.POST)
	public String sendMsg(@RequestBody String msg){
		sender.produceMsg(msg);
		return "Done";
	}
}
