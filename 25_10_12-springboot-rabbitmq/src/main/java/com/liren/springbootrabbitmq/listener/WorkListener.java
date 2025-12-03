package com.liren.springbootrabbitmq.listener;

import com.liren.springbootrabbitmq.constant.Constants;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WorkListener {
    // 工作队列模式
    @RabbitListener(queues = Constants.WORK_QUEUE)
    public void workqueue1(Message message) {
        System.out.println("workqueue1 [" + Constants.WORK_QUEUE + "]收到消息：" + message);
    }

    @RabbitListener(queues = Constants.WORK_QUEUE)
    public void workqueue2(String message) {
        System.out.println("workqueue2 [" + Constants.WORK_QUEUE + "]收到消息：" + message);
    }

    @RabbitListener(queues = Constants.WORK_QUEUE)
    public void workqueue3(String message, Channel channel) {
        System.out.println("workqueue3 [" + Constants.WORK_QUEUE + "]收到消息：" + message + "，channel：" + channel);
    }
}