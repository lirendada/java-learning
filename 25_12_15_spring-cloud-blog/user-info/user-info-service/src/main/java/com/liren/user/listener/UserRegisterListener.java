package com.liren.user.listener;

import com.liren.common.constant.Constants;
import com.liren.common.utils.JsonUtil;
import com.liren.common.utils.MailUtil;
import com.liren.user.dataobject.UserInfo;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class UserRegisterListener {
    @Autowired
    private MailUtil mailUtil;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = Constants.USER_QUEUE_NANE, durable = "true"),
            exchange = @Exchange(value = Constants.USER_EXCHANGE_NAME, type = ExchangeTypes.FANOUT)
    ))
    public void MailListenerQueue(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            String body = new String(message.getBody());
            // 尝试解析
            UserInfo user = JsonUtil.toObject(body, UserInfo.class);

            // 只有解析成功且内容不为空才发送邮件
            if (user != null && user.getEmail() != null) {
                log.info("用户注册消息处理成功，发送邮件给: {}", user.getEmail());
                mailUtil.sendMail(user.getEmail(), "用户注册成功", "欢迎注册，请点击链接完成激活");
            }

            // 确认消息
            channel.basicAck(deliveryTag, true);
        } catch (Exception e) {
            log.error("用户注册消息处理失败，放弃该消息。deliveryTag={}, error={}", deliveryTag, e.getMessage());
            // 核心修改：第三个参数改为 false，表示不重新入队 (Don't Requeue)
            // 这种解析错误的“毒药消息”如果重试永远会错，必须丢弃
            channel.basicNack(deliveryTag, true, false);
        }
    }
}