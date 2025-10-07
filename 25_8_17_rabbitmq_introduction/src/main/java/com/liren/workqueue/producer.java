package com.liren.workqueue;

import com.liren.constant.Constants;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1. 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        // 2. 设置参数
        factory.setHost(Constants.IP);
        factory.setPort(Constants.PORT);
        factory.setVirtualHost(Constants.VIRTUALHOST);
        factory.setUsername(Constants.USERNAME);
        factory.setPassword(Constants.PASSWORD);

        // 3. 创建连接Connection
        Connection connection = factory.newConnection();

        // 4. 通过连接Connection创建通道Channel
        Channel channel = connection.createChannel();

        // 5. 声明一个队列
        channel.queueDeclare(Constants.WORK_QUEUE, true, false, false, null);

        // 6. 发送消息（当使用内置交换机的时候，routingKey必须和队列名称保持一致）
        for(int i = 0; i < 10; ++i) {
            String text = "hello workqueue " + i;
            channel.basicPublish("", Constants.WORK_QUEUE, null, text.getBytes(StandardCharsets.UTF_8));
        }

        // 7. 释放资源
        connection.close();
    }
}
