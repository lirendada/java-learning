package com.liren.springbootrabbitmq.listener;

import com.liren.springbootrabbitmq.constant.Constants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectListener {
    @RabbitListener(queues = Constants.DIRECT_QUEUE1)
    public void directQueue1(String message) {
        System.out.println("directQueue1 [" + Constants.DIRECT_QUEUE1 + "]收到消息：" + message);
    }

    @RabbitListener(queues = Constants.DIRECT_QUEUE2)
    public void directQueue2(String message) {
        System.out.println("directQueue2 [" + Constants.DIRECT_QUEUE2 + "]收到消息：" + message);
    }
}
