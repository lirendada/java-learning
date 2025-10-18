package com.liren.rabbitmq_advanced.controller;

import com.liren.rabbitmq_advanced.constant.Constants;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/producer")
@RestController
public class producerController {
    @Resource(name = "rabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    @Resource(name = "confirmRabbitTemplate")
    private RabbitTemplate confirmRabbitTemplate;

    @RequestMapping("/ack")
    public String ack() {
        rabbitTemplate.convertAndSend(Constants.ACK_EXCHANGE_NAME, "ack", "consumer ack test...");
        return "发送成功！";
    }

    @RequestMapping("/confirm")
    public String confirm() {
        CorrelationData correlationData = new CorrelationData("1");
        confirmRabbitTemplate.convertAndSend(Constants.CONFIRM_EXCHANGE_NAME, "confirm", "consumer confirm test...", correlationData);
        return "发送成功！";
    }

    @RequestMapping("/returns")
    public String returns() {
        CorrelationData correlationData = new CorrelationData("5");
        confirmRabbitTemplate.convertAndSend(Constants.CONFIRM_EXCHANGE_NAME, "confirm", "consumer returns test...", correlationData);
        confirmRabbitTemplate.convertAndSend(Constants.CONFIRM_EXCHANGE_NAME, "confirm11", "consumer returns test...", correlationData);
        return "发送成功！";
    }

    @RequestMapping("/retry")
    public String retry() {
        rabbitTemplate.convertAndSend(Constants.RETRY_EXCHANGE_NAME, "retry", "retry test...");
        return "发送成功！";
    }
}