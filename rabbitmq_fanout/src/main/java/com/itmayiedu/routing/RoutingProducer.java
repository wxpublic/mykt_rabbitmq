package com.itmayiedu.routing;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.itmayiedu.utils.MQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

// 生产者  交换机类型 Direct类型（先启动生产者，以创建交换机，再启动消费者服务，更改路由键）
public class RoutingProducer {

	// 交换机名称
	private static final String DESTINATION_NAME = "my_routing_estination";

	public static void main(String[] args) throws IOException, TimeoutException {
		// 1. 建立mq连接
		Connection connection = MQConnectionUtils.newConnection();
		// 2.创建通道
		Channel channel = connection.createChannel();
		// 3.生产者绑定交换机 参数1 交换机名称 参数2 交换机类型
		channel.exchangeDeclare(DESTINATION_NAME, "direct");
		String routingKey = "email";
		// 4.创建消息
		String msg = "我是路由键 RoutngKey服务：" + routingKey;
		System.out.println("生产者投递消息:" + msg);
		// 5.发送消息 my_fanout_estination routingKey
		channel.basicPublish(DESTINATION_NAME, routingKey, null, msg.getBytes());
		// 6.关闭通道 和连接
		channel.close();
		connection.close();

	}

}
