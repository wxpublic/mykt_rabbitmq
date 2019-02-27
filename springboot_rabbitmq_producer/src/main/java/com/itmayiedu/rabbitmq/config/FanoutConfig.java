package com.itmayiedu.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//Fanout 类型 发布订阅模式
@Component
public class FanoutConfig {

	// 邮件队列
	private String FANOUT_EMAIL_QUEUE = "fanout_email_queue";
	// 短信队列
	private String FANOUT_SMS_QUEUE = "fanout_sms_queue";
	// fanout交换机
	private String EXCHANGE_NAME = "fanoutExchange";

	// 1.定义队列邮件
	@Bean
	public Queue fanOutEamilQueue() {
		return new Queue(FANOUT_EMAIL_QUEUE);
	}

	// 2.定义短信队列
	@Bean
	public Queue fanOutSmsQueue() {
		return new Queue(FANOUT_SMS_QUEUE);
	}

	// 3.fanout类型定义交换机, 思考下：topic类型怎么写？回答：使用TopicExchange 类就可以
	@Bean
	FanoutExchange fanoutExchange() {
		return new FanoutExchange(EXCHANGE_NAME);
	}

	// 3.队列与交换机绑定邮件队列
	@Bean
	Binding bindingExchangeEamil(Queue fanOutEamilQueue, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(fanOutEamilQueue).to(fanoutExchange);
	}

	// 4.队列与交换机绑定短信队列
	@Bean
	Binding bindingExchangeSms(Queue fanOutSmsQueue, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(fanOutSmsQueue).to(fanoutExchange);
	}
}
