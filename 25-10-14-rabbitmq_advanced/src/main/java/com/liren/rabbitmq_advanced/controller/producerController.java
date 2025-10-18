package com.liren.rabbitmq_advanced.controller;

import com.liren.rabbitmq_advanced.constant.Constants;
import jakarta.annotation.Resource;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
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

//    @RequestMapping("/ttl")
//    public String ttl() {
//        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
//            @Override
//            public Message postProcessMessage(Message message) throws AmqpException {
//                message.getMessageProperties().setExpiration(Constants.TTL_TIME);
//                return message;
//            }
//        };
//
//        rabbitTemplate.convertAndSend(Constants.TTL_EXCHANGE_NAME, "ttl", "ttl test...", messagePostProcessor);
//        return "发送成功！";
//    }

    @RequestMapping("/ttl")
    public String ttl() {
        rabbitTemplate.convertAndSend(Constants.TTL_EXCHANGE_NAME, "ttl", "ttl test...", message -> {
            message.getMessageProperties().setExpiration(Constants.TTL_TIME);
            return message;
        });
        return "发送成功！";
    }

    @RequestMapping("/dlx")
    public String dlx() {
        // 1. 测试过期时间, 当时间达到TTL, 消息自动进入到死信队列
        rabbitTemplate.convertAndSend(Constants.NORMAL_EXCHANGE, "normal", "dlx test...");

        // 2. 测试队列长度溢出，消息自动进入到死信队列
//        for(int i = 0; i < 20; ++i) {
//            rabbitTemplate.convertAndSend(Constants.NORMAL_EXCHANGE, "normal", "dlx test...");
//        }

        return "发送成功！";
    }
}