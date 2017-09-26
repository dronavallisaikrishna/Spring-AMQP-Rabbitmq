package com.springamqp.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
@EnableRabbit
public class RabbitMQConfiguration {
	
	
	
	@Bean
	@Primary
	public RabbitProperties setProperties() {
		RabbitProperties props=new RabbitProperties();
		props.setUsername("guest");
		props.setPassword("guest");
		return props;
	}
	@Bean
	public ConnectionFactory connectionFactory() {
	    CachingConnectionFactory cf = new CachingConnectionFactory();
	    cf.setAddresses(setProperties().getAddresses());
	    cf.setUsername(setProperties().getUsername());
	    cf.setPassword(setProperties().getPassword());
//	    cf.setVirtualHost(setProperties().getVirtualHost());
	    return cf;
	}
	
	 @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrentConsumers(3);
        factory.setMaxConcurrentConsumers(10);
        return factory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public AmqpTemplate rabbitTemplate() {
    	AmqpTemplate template=  new RabbitTemplate(connectionFactory());
        return template;
    }

//    @Bean
//    public Queue myQueue() {
//       return new Queue("jsa.queue");
//    }
    
    @RabbitListener(bindings = @QueueBinding(value =@Queue(value="jsa.queue"),
    		exchange=@Exchange(value="jsa.direct"),key="jsa.routingkey" ))
    public void processOrder(String data) {
        System.out.println("data is:-"+data);
    }
}