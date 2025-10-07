package com.liren.workqueue;

import com.liren.constant.Constants;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class consumer2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1. 创建连接工厂（同生产者）
        ConnectionFactory factory = new ConnectionFactory();

        // 2. 设置参数（同生产者）
        factory.setHost(Constants.IP);
        factory.setPort(Constants.PORT);
        factory.setVirtualHost(Constants.VIRTUALHOST);
        factory.setUsername(Constants.USERNAME);
        factory.setPassword(Constants.PASSWORD);

        // 3. 创建连接Connection（同生产者）
        Connection connection = factory.newConnection();

        // 4. 通过连接Connection创建通道Channel（同生产者）
        Channel channel = connection.createChannel();

        // 5. 声明一个队列（同生产者）（这是安全性措施，因为如果生产者还没创建队列的话，消费者这边直接读取会报错）
        channel.queueDeclare(Constants.WORK_QUEUE, true, false, false, null);

        // 6. 接收消息，进行消费💥
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
        channel.basicConsume(Constants.WORK_QUEUE, true, defaultConsumer);
    }
}
