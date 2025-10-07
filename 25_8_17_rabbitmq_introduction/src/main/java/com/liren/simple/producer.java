package com.liren.simple;

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
        factory.setHost("127.0.0.1");   // 设置ip
        factory.setPort(5672);               // 设置端口
        factory.setVirtualHost("lirendada"); // 设置虚拟主机名称
        factory.setUsername("liren");        // 设置用户名
        factory.setPassword("123456");       // 设置密码

        // 3. 创建连接Connection
        Connection connection = factory.newConnection();

        // 4. 通过连接Connection创建通道Channel
        Channel channel = connection.createChannel();

        // 5. 声明一个队列
        channel.queueDeclare("test", true, false, false, null);

        // 6. 发送消息（当使用内置交换机的时候，routingKey必须和队列名称保持一致）
        String text = "hello lirendada!";
        channel.basicPublish("", "test", null, text.getBytes(StandardCharsets.UTF_8));
        System.out.println(text + "消息发送成功");

        // 7. 释放资源
        connection.close();
    }
}
