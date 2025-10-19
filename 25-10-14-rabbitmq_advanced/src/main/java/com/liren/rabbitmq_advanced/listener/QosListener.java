package com.liren.rabbitmq_advanced.listener;

import com.liren.rabbitmq_advanced.constant.Constants;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class QosListener {
    @RabbitListener(queues = Constants.QOS_QUEUE)
    public void qosQueue1(Message message, Channel channel) throws IOException, InterruptedException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.printf("qosQueue1 接收到消息：%s，deliveryTag：%d%n", new String(message.getBody()), deliveryTag);

        Thread.sleep(1000); // 模拟快业务处理，1s

        channel.basicAck(deliveryTag, true);
    }

    @RabbitListener(queues = Constants.QOS_QUEUE)
    public void qosQueue2(Message message, Channel channel) throws IOException, InterruptedException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.printf("qosQueue2 接收到消息：%s，deliveryTag：%d%n", new String(message.getBody()), deliveryTag);

        Thread.sleep(2000); // 模拟慢业务处理，2s

        channel.basicAck(deliveryTag, true);
    }
}
