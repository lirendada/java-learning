package com.liren.user.listener;

import com.liren.common.constant.Constants;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class UserRegisterListener {
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = Constants.USER_QUEUE_NANE, durable = "true"),
        exchange = @Exchange(value = Constants.USER_EXCHANGE_NAME, type = ExchangeTypes.FANOUT)
    ))
    public void MailListenerQueue(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            // 处理用户注册消息
            String body = new String(message.getBody());
            log.info("用户注册消息处理成功，deliveryTag={}, message={}", deliveryTag, body);

            // 发送邮件TODO

            // 确认消息
            channel.basicAck(deliveryTag, true);
        }catch (Exception e) {
            // 异常拒绝消息，进行重发
            channel.basicNack(deliveryTag, true, true);
            log.error("用户注册消息处理失败，拒绝消息，deliveryTag={}", deliveryTag, e);
        }
    }
}
