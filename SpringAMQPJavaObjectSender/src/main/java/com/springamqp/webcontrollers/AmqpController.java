package com.springamqp.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springamqp.beans.BunnyMessage;
import com.springamqp.beans.RabbitMessageBean;
import com.springamqp.senders.MessageSender;

@RestController
public class AmqpController {

	@Autowired
	private MessageSender sender;
	
	@RequestMapping(value="/send",method=RequestMethod.POST)
	public String sendMsg(@RequestBody RabbitMessageBean msg){
		try {
			sender.produceMsg(msg);
			return "Done";
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "failed";
		}
	}
	
	@RequestMapping(value="/send-bunny-message",method=RequestMethod.POST)
	public String sendBunnyMsg(@RequestBody BunnyMessage msg){
		try {
			sender.produceBunnyMsg(msg);
			return "Done";
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "failed";
		}
		
	}
}
