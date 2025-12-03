package com.liren.rabbitmq_advanced.listener;

import com.liren.rabbitmq_advanced.constant.Constants;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DelayListener {
    @RabbitListener(queues = Constants.DELAY_QUEUE)
    public void delayQueue(Message message) {
        System.out.printf("%tc 延迟队列接收到消息: %s\n", new Date(), new String(message.getBody()));
    }
}
