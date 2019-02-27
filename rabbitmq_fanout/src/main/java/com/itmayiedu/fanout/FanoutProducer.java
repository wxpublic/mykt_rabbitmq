package com.itmayiedu.fanout;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.itmayiedu.utils.MQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

// ������  ���������� producer fanout����
public class FanoutProducer {

	// ����������
	private static final String DESTINATION_NAME = "my_fanout_estination";

	public static void main(String[] args) throws IOException, TimeoutException {
		// 1. ����mq����
		Connection connection = MQConnectionUtils.newConnection();
		// 2.����ͨ��
		Channel channel = connection.createChannel();
		// 3.�����߰󶨽����� ����1 ���������� ����2 ����������
		channel.exchangeDeclare(DESTINATION_NAME, "fanout");
		// 4.������Ϣ
		String msg = "��dei˾ ���� վͷ��������������Ͷ�ݵ���Ϣ����";
		System.out.println("������Ͷ����Ϣ:" + msg);
		// 5.������Ϣ my_fanout_estination routingKey
		channel.basicPublish(DESTINATION_NAME, "", null, msg.getBytes());
		// 6.�ر�ͨ�� ������
		channel.close();
		connection.close();

	}

}