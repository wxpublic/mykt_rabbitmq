package com.itmayiedu.rabbitmq.producer;

import java.util.Date;
//import java.util.UUID;
import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageBuilder;
//import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutProducer {
	@Autowired
	private AmqpTemplate amqpTemplate;

	public void send(String queueName) {
		System.out.println("queueName:" + queueName);
		String msg = "msg:" + new Date();
		amqpTemplate.convertAndSend(queueName, msg);
	}

//	public void send(String queueName) {
//		String msg = "my_fanout_msg:" + System.currentTimeMillis();
//		Message message = MessageBuilder.withBody(msg.getBytes()).setContentType(MessageProperties.CONTENT_TYPE_JSON)
//				.setContentEncoding("utf-8").setMessageId(UUID.randomUUID() + "").build();
//		System.out.println(msg + ":" + msg);
//		amqpTemplate.convertAndSend(queueName, message);
//	}
}
