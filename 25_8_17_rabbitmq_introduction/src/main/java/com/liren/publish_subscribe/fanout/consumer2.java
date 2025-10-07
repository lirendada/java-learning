package com.liren.publish_subscribe.fanout;

import com.liren.constant.Constants;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class consumer2 {
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

        // 3. 声明队列（这是安全性措施，因为如果生产者还没创建队列的话，消费者这边直接读取会报错）
        channel.queueDeclare(Constants.FANOUT_QUEUE2, true, false, false, null);

        // 4. 接收消息，进行消费💥
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException
            {
                System.out.println("接收到消息: " + new String(body));
            }
        };
        channel.basicConsume(Constants.FANOUT_QUEUE2, true, defaultConsumer);
    }
}
