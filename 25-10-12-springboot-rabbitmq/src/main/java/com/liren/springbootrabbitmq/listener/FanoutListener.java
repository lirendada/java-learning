package com.liren.springbootrabbitmq.listener;

import com.liren.springbootrabbitmq.constant.Constants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutListener {
    @RabbitListener(queues = Constants.FANOUT_QUEUE1)
    public void fanoutQueue1(String message) {
        System.out.println("fanoutQueue1 [" + Constants.FANOUT_QUEUE1 + "]收到消息：" + message);
    }

    @RabbitListener(queues = Constants.FANOUT_QUEUE2)
    public void fanoutQueue2(String message) {
        System.out.println("fanoutQueue2 [" + Constants.FANOUT_QUEUE2 + "]收到消息：" + message);
    }
}
