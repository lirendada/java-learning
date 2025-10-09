package com.liren.rpc;

import com.liren.constant.Constants;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

public class client {
    private final static BlockingQueue<String> bq = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 1. 创建连接工厂、连接Connection和通道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Constants.IP);
        factory.setPort(Constants.PORT);
        factory.setVirtualHost(Constants.VIRTUALHOST);
        factory.setUsername(Constants.USERNAME);
        factory.setPassword(Constants.PASSWORD);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 2. 声明队列
        channel.queueDeclare(Constants.RPC_REQUEST_QUEUE, true, false, false, null);
        channel.queueDeclare(Constants.RPC_RESPONSE_QUEUE, true, false, false, null);

        // 3. 发送请求到请求队列中，需要设置属性（使用内置交换机时, routingKey要和队列名称一样, 才可以路由到对应的队列上去）
        String id = UUID.randomUUID().toString();
        String text = "hello rpc!";
        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .replyTo(Constants.RPC_RESPONSE_QUEUE) // 设置回调队列
                .correlationId(id)                     // 唯一标志本次请求
                .build();
        channel.basicPublish("", Constants.RPC_REQUEST_QUEUE, props, text.getBytes(StandardCharsets.UTF_8));

        // 4. 接收响应队列中的消息（需要放到阻塞队列中，保持接收时候的同步）
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String resp = new String(body);
                System.out.println("接收到响应队列中消息：" + resp);

                // 校验CorrelationId是否一致
                if(id.equals(properties.getCorrelationId())) {
                    bq.offer(resp);
                }
            }
        };
        channel.basicConsume(Constants.RPC_RESPONSE_QUEUE, true, consumer);

        // 获取回调的结果（因为是从阻塞队列中拿，所以这里会阻塞）
        String result = bq.take();
        System.out.println(" [RPCClient] Result:" + result);

        // 释放资源
        connection.close();
    }
}
