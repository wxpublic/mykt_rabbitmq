package com.itmayiedu.fanout;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.itmayiedu.utils.MQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

// 邮件消费者
public class ConsumerEmailFanout {
	private static final String EMAIL_QUEUE = "email_queue_fanout";
	// 交换机名称
	private static final String DESTINATION_NAME = "my_fanout_estination";

	public static void main(String[] args) throws IOException, TimeoutException {
		System.out.println("邮件消费启动");
		// 1. 建立mq连接
		Connection connection = MQConnectionUtils.newConnection();
		// 2.创建通道
		Channel channel = connection.createChannel();
		// 3.消费声明队列
		channel.queueDeclare(EMAIL_QUEUE, false, false, false, null);
		// 4.消费者队列绑定交换机
		channel.queueBind(EMAIL_QUEUE, DESTINATION_NAME, "");
		// 5.消费监听消息
		DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String msg = new String(body, "UTF-8");
				System.out.println("邮件消费者获取生产消息:" + msg);
			}
		};
		channel.basicConsume(EMAIL_QUEUE, true, defaultConsumer);

	}

}
