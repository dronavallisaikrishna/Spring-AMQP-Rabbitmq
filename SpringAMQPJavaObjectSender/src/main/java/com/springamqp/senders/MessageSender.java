package com.springamqp.senders;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springamqp.beans.BunnyMessage;
import com.springamqp.beans.RabbitMessageBean;

@Component
public class MessageSender {

	@Autowired
	AmqpTemplate amqpTemplate;

	@Value("${jsa.rabbitmq.exchange}")
	private String exchange;

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public SimpleRabbitListenerContainerFactory listner() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setMessageConverter(jsonMessageConverter());
		return factory;
	}

	@Scheduled(fixedRate = 3000)
	public void sendMessage() {
		amqpTemplate.convertAndSend("jsa.queue.3", new Date().toString());
	}

	public void produceMsg(RabbitMessageBean msg) {
		amqpTemplate.convertAndSend("jsa.queue.1", msg);
		System.out.println("Send msg = " + msg);
	}

	public void produceBunnyMsg(BunnyMessage msg) {
		amqpTemplate.convertAndSend("jsa.queue.2", msg);
		System.out.println("Send msg = " + msg);
	}
}
