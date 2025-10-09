package com.liren.rpc;

import com.liren.constant.Constants;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class server {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1. 创建连接工厂、连接Connection和通道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Constants.IP);
        factory.setPort(Constants.PORT);
        factory.setVirtualHost(Constants.VIRTUALHOST);
        factory.setUsername(Constants.USERNAME);
        factory.setPassword(Constants.PASSWORD);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 2. 接收请求
        // 设置同时最多只能获取一个消息💥
        channel.basicQos(1);

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String text = new String(body);
                System.out.println("接收到请求：" + text);

                String resp = "[response] " + text;

                // 3. 发送响应
                // 设置属性，将消息放到响应队列中
                AMQP.BasicProperties props = new AMQP.BasicProperties
                        .Builder()
                        .correlationId(properties.getCorrelationId())
                        .build();
                channel.basicPublish("", properties.getReplyTo(), props, resp.getBytes(StandardCharsets.UTF_8));

                // 因为设置了autoAck=false，所以需要手动ack确定一下💥
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(Constants.RPC_REQUEST_QUEUE, false, consumer);
    }
}
