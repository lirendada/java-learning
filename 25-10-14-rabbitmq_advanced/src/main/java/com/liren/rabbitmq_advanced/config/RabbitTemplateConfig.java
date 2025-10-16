package com.liren.rabbitmq_advanced.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTemplateConfig {
    @Bean("ackRabbitTemplate")
    public RabbitTemplate AckRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }

    @Bean("confirmRabbitTemplate")
    public RabbitTemplate ConfirmRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if(ack) {
                    System.out.printf("消息接收成功, id:%s", correlationData.getId());
                } else {
                    System.out.printf("消息接收失败, id:%s cause:%s", correlationData.getId(), cause);
                }
            }
        });
        return rabbitTemplate;
    }
}
