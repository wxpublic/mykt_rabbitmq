package com.itmayiedu.message.email;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout_email_queue")
public class EmailConsumer {

	@RabbitHandler
	public void process(String msg) {
		System.out.println("邮件消费者获取生产者消息msg:" + msg);
	}

}
