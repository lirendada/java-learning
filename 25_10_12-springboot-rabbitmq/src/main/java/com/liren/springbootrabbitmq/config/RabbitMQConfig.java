package com.liren.springbootrabbitmq.config;

import com.liren.springbootrabbitmq.constant.Constants;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;

@Configuration
public class RabbitMQConfig {
    // 工作队列模式
    @Bean("workQueue")
    public Queue WorkQueue() {
        return QueueBuilder.durable(Constants.WORK_QUEUE).build();
    }


    // 发布订阅模式（fanout广播模式）
    @Bean("fanoutQueue1")
    public Queue fanoutQueue1() {
        return QueueBuilder.durable(Constants.FANOUT_QUEUE1).build(); // 声明队列
    }
    @Bean("fanoutQueue2")
    public Queue fanoutQueue2() {
        return QueueBuilder.durable(Constants.FANOUT_QUEUE2).build(); // 声明队列
    }

    @Bean("fanoutExchange")
    public FanoutExchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange(Constants.FANOUT_EXCHANGE).build(); // 声明交换机
    }

    @Bean("fanoutBinding1")
    public Binding fanoutBinding1(@Qualifier("fanoutQueue1") Queue queue,
                                 @Qualifier("fanoutExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange); // 绑定交换机和队列
    }
    @Bean("fanoutBinding2")
    public Binding fanoutBinding2(@Qualifier("fanoutQueue2") Queue queue,
                                  @Qualifier("fanoutExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange); // 绑定交换机和队列
    }


    // 路由模式（direct模式）
    @Bean("directQueue1")
    public Queue directQueue1() {
        return QueueBuilder.durable(Constants.DIRECT_QUEUE1).build(); // 声明队列
    }
    @Bean("directQueue2")
    public Queue directQueue2() {
        return QueueBuilder.durable(Constants.DIRECT_QUEUE2).build(); // 声明队列
    }

    @Bean("directExchange")
    public DirectExchange directExchange() {
        return ExchangeBuilder.directExchange(Constants.DIRECT_EXCHANGE).build(); // 声明交换机
    }

    // 队列1绑定orange
    @Bean("directBinding1")
    public Binding directBinding1(@Qualifier("directQueue1") Queue queue,
                                  @Qualifier("directExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("orange"); // 绑定交换机和队列，以及bindingKey
    }
    // 队列2绑定green、black
    @Bean("directBinding2")
    public Binding directBinding2(@Qualifier("directQueue2") Queue queue,
                                  @Qualifier("directExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("green"); // 绑定交换机和队列，以及bindingKey
    }
    @Bean("directBinding3")
    public Binding directBinding3(@Qualifier("directQueue2") Queue queue,
                                  @Qualifier("directExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("black"); // 绑定交换机和队列，以及bindingKey
    }


    // 通配符模式（topics模式）
    @Bean("topicQueue1")
    public Queue topicQueue1() {
        return QueueBuilder.durable(Constants.TOPIC_QUEUE1).build(); // 声明队列
    }
    @Bean("topicQueue2")
    public Queue topicQueue2() {
        return QueueBuilder.durable(Constants.TOPIC_QUEUE2).build(); // 声明队列
    }

    @Bean("topicExchange")
    public TopicExchange topicExchange() {
        return ExchangeBuilder.topicExchange(Constants.TOPIC_EXCHANGE).build(); // 声明交换机
    }

    // 队列1绑定error, 仅接收error信息
    @Bean("topicBinding1")
    public Binding topicBinding1(@Qualifier("topicQueue1") Queue queue,
                                  @Qualifier("topicExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("*.error"); // 绑定交换机和队列，以及bindingKey
    }
    // 队列2绑定info, error: error,info信息都接收
    @Bean("topicBinding2")
    public Binding topicBinding2(@Qualifier("topicQueue2") Queue queue,
                                  @Qualifier("topicExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("*.error"); // 绑定交换机和队列，以及bindingKey
    }
    @Bean("topicBinding3")
    public Binding topicBinding3(@Qualifier("topicQueue2") Queue queue,
                                  @Qualifier("topicExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("#.info"); // 绑定交换机和队列，以及bindingKey
    }
}
