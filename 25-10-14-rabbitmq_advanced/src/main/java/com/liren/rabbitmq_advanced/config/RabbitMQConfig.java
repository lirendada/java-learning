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


    // 重试机制
    @Bean("retryQueue")
    public Queue retryQueue() {
        return QueueBuilder.durable(Constants.RETRY_QUEUE).build();
    }

    @Bean("retryExchange")
    public DirectExchange retryExchange() {
        return ExchangeBuilder.directExchange(Constants.RETRY_EXCHANGE_NAME).durable(true).build();
    }

    @Bean("retryBinding")
    public Binding retryBinding(@Qualifier("retryQueue")Queue queue,
                                  @Qualifier("retryExchange")Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("retry").noargs();
    }


    // TTL
    @Bean("ttlQueue")
    public Queue ttlQueue() {
        return QueueBuilder.durable(Constants.TTL_QUEUE).build();
    }

    @Bean("ttlExchange")
    public DirectExchange ttlExchange() {
        return ExchangeBuilder.directExchange(Constants.TTL_EXCHANGE_NAME).durable(true).build();
    }

    @Bean("ttlBinding")
    public Binding ttlBinding(@Qualifier("ttlQueue")Queue queue,
                                @Qualifier("ttlExchange")Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("ttl").noargs();
    }

    @Bean("ttlQueue2")
    public Queue ttlQueue2() {
        return QueueBuilder.durable(Constants.TTL_QUEUE).ttl(Integer.valueOf(Constants.TTL_TIME)).build();
    }
}