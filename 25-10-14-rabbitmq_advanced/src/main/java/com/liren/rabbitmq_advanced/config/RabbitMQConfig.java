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

//    @Bean("ttlQueue2")
//    public Queue ttlQueue2() {
//        return QueueBuilder.durable(Constants.TTL_QUEUE).ttl(10000).build();
//    }


    // 死信
    @Bean("normalQueue")
    public Queue normalQueue() {
        return QueueBuilder
                .durable(Constants.NORMAL_QUEUE)
                .deadLetterExchange(Constants.DL_EXCHANGE) // 绑定死信交换机
                .deadLetterRoutingKey("dlk")          // 绑定死信路由键
                .ttl(10000)                                // 过期时间设置10s，方便测试
                .maxLength(10L)                     // 队列最大长度设为10，方便测试
                .build();
    }

    @Bean("normalExchange")
    public DirectExchange normalExchange() {
        return ExchangeBuilder.directExchange(Constants.NORMAL_EXCHANGE).durable(true).build();
    }

    @Bean("normalBinding")
    public Binding normalBinding(@Qualifier("normalQueue")Queue queue,
                              @Qualifier("normalExchange")Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("normal").noargs();
    }

    @Bean("dlQueue")
    public Queue dlQueue() {
        return QueueBuilder.durable(Constants.DL_QUEUE).build();
    }

    @Bean("dlExchange")
    public DirectExchange dlExchange() {
        return ExchangeBuilder.directExchange(Constants.DL_EXCHANGE).durable(true).build();
    }

    @Bean("dlBinding")
    public Binding dlBinding(@Qualifier("dlQueue")Queue queue,
                                 @Qualifier("dlExchange")Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("dlk").noargs();
    }
}