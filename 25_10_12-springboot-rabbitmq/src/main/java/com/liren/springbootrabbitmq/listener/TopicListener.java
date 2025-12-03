package com.liren.springbootrabbitmq.listener;

import com.liren.springbootrabbitmq.constant.Constants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicListener {
    @RabbitListener(queues = Constants.TOPIC_QUEUE1)
    public void topicQueue1(String message) {
        System.out.println("topicQueue1 [" + Constants.TOPIC_QUEUE1 + "]收到消息：" + message);
    }

    @RabbitListener(queues = Constants.TOPIC_QUEUE2)
    public void topicQueue2(String message) {
        System.out.println("topicQueue2 [" + Constants.TOPIC_QUEUE2 + "]收到消息：" + message);
    }
}
