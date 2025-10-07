package com.liren.publish_subscribe.fanout;

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

        // 3. 声明两个队列
        channel.queueDeclare(Constants.FANOUT_QUEUE1, true, false, false, null);
        channel.queueDeclare(Constants.FANOUT_QUEUE2, true, false, false, null);

        // 4. 创建交换机
        channel.exchangeDeclare(Constants.FANOUT_EXCHANGE, BuiltinExchangeType.FANOUT, true, false, false, null);

        // 5. 绑定交换机和队列（因为是广播模式，所以本质不需要routingkey，置为空字符串即可）
        channel.queueBind(Constants.FANOUT_QUEUE1, Constants.FANOUT_EXCHANGE, "");
        channel.queueBind(Constants.FANOUT_QUEUE2, Constants.FANOUT_EXCHANGE, "");

        // 6. 发送消息
        String text = "hello public/subscribe && fanout mode!";
        channel.basicPublish(Constants.FANOUT_EXCHANGE, "", null, text.getBytes(StandardCharsets.UTF_8));

        // 7. 释放资源
        connection.close();
    }
}
