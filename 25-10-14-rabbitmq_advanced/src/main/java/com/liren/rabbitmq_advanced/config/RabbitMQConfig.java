package com.liren.rabbitmq_advanced.config;

import com.liren.rabbitmq_advanced.constant.Constants;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    // 消息确认
    @Bean("ackQueue")
    public Queue ackQueue() {
        return QueueBuilder.durable(Constants.ACK_QUEUE).build();
    }

    @Bean("ackExchange")
    public DirectExchange ackExchange() {
        return ExchangeBuilder.directExchange(Constants.ACK_EXCHANGE_NAME).durable(true).build();
    }

    @Bean("ackBinding")
    public Binding ackBinding(@Qualifier("ackQueue") Queue queue,
                              @Qualifier("ackExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("ack");
    }


    // 发布确认机制
    @Bean("confirmQueue")
    public Queue confirmQueue() {
        return QueueBuilder.durable(Constants.CONFIRM_QUEUE).build();
    }

    @Bean("confirmExchange")
    public DirectExchange confirmExchange() {
        return ExchangeBuilder.directExchange(Constants.CONFIRM_EXCHANGE_NAME).durable(true).build();
    }

    @Bean("confirmBinding")
    public Binding confirmBinding(@Qualifier("confirmQueue")Queue queue,
                                  @Qualifier("confirmExchange")Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("confirm").noargs();
    }
}