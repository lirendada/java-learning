package com.liren.rabbitmq_advanced.listener;

import com.liren.rabbitmq_advanced.constant.Constants;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RetryListener {
    @RabbitListener(queues = Constants.RETRY_QUEUE)
    public void ListenerQueue(Message message, Channel channel) throws IOException, InterruptedException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.printf("接收到消息：%s，deliveryTag：%d \n", new String(message.getBody()), deliveryTag);

        try {
            // 模拟出现异常
            int a = 3 / 0;
            System.out.println("处理完成！");
            channel.basicAck(deliveryTag, true); // 手动确认一下
        } catch (Exception e) {
            System.out.println("出现异常！");
            Thread.sleep(1000);
            channel.basicNack(deliveryTag, true, true); // 设置重新入队
        }
    }
}
