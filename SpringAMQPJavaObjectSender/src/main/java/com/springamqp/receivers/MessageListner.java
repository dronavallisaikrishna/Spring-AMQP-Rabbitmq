package com.springamqp.receivers;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.springamqp.beans.BunnyMessage;
import com.springamqp.beans.RabbitMessageBean;

@Component
public class MessageListner {

	@RabbitListener(queues="${jsa.rabbitmq.RabbitBeanqueue}")
    public void recievedMessage(RabbitMessageBean msg) {
        System.out.println("Recieved Message: " + msg);
    }
	
	@RabbitListener(queues="${jsa.rabbitmq.Bunneyqueue}")
    public void recievedBunnyMessage(BunnyMessage msg) {
        System.out.println("Recieved Message: " + msg);
    }
	
//	@RabbitListener(queues="${jsa.rabbitmq.queue}")
//    public void recieveScheduledMessage(String msg) {
//        System.out.println("Recieved scheduled message is: " + msg);
//    }
	
	@RabbitListener(queues="${jsa.rabbitmq.timequeue}")
    public void recievedMessage(String msg) {
//		String message=new String(msg.getBody());
        System.out.println("Recieved Message: " + msg);
    }
}
