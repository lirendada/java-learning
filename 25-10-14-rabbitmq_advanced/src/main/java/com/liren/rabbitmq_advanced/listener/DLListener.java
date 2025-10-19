package com.liren.rabbitmq_advanced.listener;

import com.liren.rabbitmq_advanced.constant.Constants;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class DLListener {
    /*
    // 监听正常队列
    @RabbitListener(queues = Constants.NORMAL_QUEUE)
    public void normalQueue(Message message, Channel channel) throws InterruptedException, IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            System.out.printf("接收到消息: %s, deliveryTag: %d\n", new String(message.getBody()), deliveryTag);

            // 模拟处理失败
            int num = 3/0;
            System.out.println("处理完成");

            // 手动确认
            channel.basicAck(deliveryTag, true);
        }catch (Exception e){
            // 第三个参数requeue决定是否重新入队，如果为true，则会重新发送；若为false，则直接丢弃，若此时设置了死信，会进入到死信队列
            channel.basicNack(deliveryTag, true,false);
        }
    }

    // 监听死信队列
    @RabbitListener(queues = Constants.DL_QUEUE)
    public void dlQueue(Message message) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.printf("死信队列接收到消息: %s, deliveryTag: %d\n", new String(message.getBody()), deliveryTag);
    }
     */


    // 监听死信队列
    @RabbitListener(queues = Constants.DL_QUEUE)
    public void dlQueue(Message message) {
        System.out.printf("%tc 死信队列接收到消息: %s\n", new Date(), new String(message.getBody()));
    }
}
