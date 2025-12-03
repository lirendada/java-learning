package com.liren.springbootrabbitmq.controller;

import com.liren.springbootrabbitmq.constant.Constants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class PublishController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/work")
    public String work() {
        for(int i = 1; i <= 10; ++i) {
            rabbitTemplate.convertAndSend("", Constants.WORK_QUEUE, "hello spring amqp: work..." + i);
        }
        return "发送成功！";
    }

    @RequestMapping("/fanout")
    public String fanout() {
        rabbitTemplate.convertAndSend(Constants.FANOUT_EXCHANGE, "", "hello spring amqp: fanout...");
        return "发送成功！";
    }

    @RequestMapping("/direct/{routing_key}")
    public String dirct(@PathVariable("routing_key") String routing_key) {
        rabbitTemplate.convertAndSend(Constants.DIRECT_EXCHANGE, routing_key, "hello spring amqp: direct..." + routing_key);
        return "发送成功！";
    }

    @RequestMapping("/topics/{routing_key}")
    public String topics(@PathVariable("routing_key") String routing_key) {
        rabbitTemplate.convertAndSend(Constants.TOPIC_EXCHANGE, routing_key, "hello spring amqp: topics..." + routing_key);
        return "发送成功！";
    }
}
