package com.springamqp.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springamqp.config.RabbitMQConfiguration;
import com.springamqp.modals.RabbitMQMessage;

@RestController
@RequestMapping("/amqp")
public class AMQPController {

	@Autowired
	private RabbitProperties props;

	
	ApplicationContext context =
		    new AnnotationConfigApplicationContext(RabbitMQConfiguration.class);
		AmqpTemplate template = context.getBean(AmqpTemplate.class);
		
	public Message createMessage(RabbitMQMessage message) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out=null;
		try {
			out = new ObjectOutputStream(bos);
			out.writeObject(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Message m= MessageBuilder.withBody(bos.toByteArray())
				.setMessageId(message.getMessageFrom())
				.setHeader("date", new Date())
				.setContentType(MessageProperties.CONTENT_TYPE_JSON)
				.build();
		return m;
	}
	@RequestMapping(value="/add-message",method=RequestMethod.POST)
	public String addMessage(@RequestBody  RabbitMQMessage message) {
		System.out.println("Message object is:-"+message);
		//AmqpTemplate template=config.rabbitTemplate();
		template.send("jsa.direct","jsa.routingkey",createMessage(message));
		return "success";
	}
	
	@RequestMapping(value="/get-message",method=RequestMethod.GET)
	public  Message getMessage() {
//		AmqpTemplate template=config.rabbitTemplate();
//		Object obj=template.receiveAndConvert("myqueue");
//		System.out.println("Oject class object is:-"+obj);
		
		String message=(String)template.receiveAndConvert("myqueue");
//		System.out.println("message is:-"+message);
		return template.receive("myqueue");
	}
	
	@RequestMapping(value="/get-properties",method=RequestMethod.GET)
	public RabbitProperties getRabbitProperties() {
		return props;
	}
	
	
}
