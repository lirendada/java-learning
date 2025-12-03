package com.liren.rabbitmq_advanced.listener;

import com.liren.rabbitmq_advanced.constant.Constants;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component
public class AckListener {
    @RabbitListener(queues = Constants.ACK_QUEUE)
    public void ListenerQueue(Message message, Channel channel) throws IOException, InterruptedException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.printf("接收到消息: %s, deliveryTag: %d%n", new String(message.getBody(),"UTF-8"), deliveryTag);
        Thread.sleep(1000);

        try {
            // 模拟处理失败，会抛异常
            int a = 3 / 0;
            channel.basicAck(deliveryTag, false);
            System.out.println("处理完成");

        } catch (Exception e) {
            System.err.println("处理失败，重新入队：" + e.getMessage());
            try {
                channel.basicNack(deliveryTag, false, true); // 重新入队
            } catch (IOException ioException) {
                ioException.printStackTrace(); // catch 内单独处理 Nack 异常
            }
        }
    }
}