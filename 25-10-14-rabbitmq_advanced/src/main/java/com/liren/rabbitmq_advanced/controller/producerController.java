package com.liren.rabbitmq_advanced.controller;

import com.liren.rabbitmq_advanced.constant.Constants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/producer")
@RestController
public class producerController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/ack")
    public String ack() {
        rabbitTemplate.convertAndSend(Constants.ACK_EXCHANGE_NAME, "ack", "consumer ack test...");
        return "发送成功！";
    }
}