package com.liren.publish_subscribe.routing;

import com.liren.constant.Constants;
import com.rabbitmq.client.BuiltinExchangeType;
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
        factory.setHost(Constants.IP);
        factory.setPort(Constants.PORT);
        factory.setVirtualHost(Constants.VIRTUALHOST);
        factory.setUsername(Constants.USERNAME);
        factory.setPassword(Constants.PASSWORD);

        // 2. 创建连接Connection和通道
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 3. 声明队列
        channel.queueDeclare(Constants.DIRECT_QUEUE1, true, false, false, null);
        channel.queueDeclare(Constants.DIRECT_QUEUE2, true, false, false, null);

        // 4. 声明交换机
        channel.exchangeDeclare(Constants.DIRECT_EXCHANGE, BuiltinExchangeType.DIRECT, true, false, false, null);

        // 5. 绑定交换机和队列
        channel.queueBind(Constants.DIRECT_QUEUE1, Constants.DIRECT_EXCHANGE, "orange");
        channel.queueBind(Constants.DIRECT_QUEUE2, Constants.DIRECT_EXCHANGE, "black");
        channel.queueBind(Constants.DIRECT_QUEUE2, Constants.DIRECT_EXCHANGE, "green");

        // 6. 发送消息
        String text1 = "hello routing, i am orange!";
        channel.basicPublish(Constants.DIRECT_EXCHANGE, "orange", null, text1.getBytes(StandardCharsets.UTF_8));

        String text2 = "hello routing, i am black!";
        channel.basicPublish(Constants.DIRECT_EXCHANGE, "black", null, text2.getBytes(StandardCharsets.UTF_8));

        String text3 = "hello routing, i am green!";
        channel.basicPublish(Constants.DIRECT_EXCHANGE, "green", null, text3.getBytes(StandardCharsets.UTF_8));

        // 7. 释放资源
        connection.close();
    }
}
