package com.liren.user;

import com.liren.common.constant.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RabbitmqTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test() {
        rabbitTemplate.convertAndSend(Constants.USER_EXCHANGE_NAME, "", "lirendadaiubqiwd库bask的");
    }
}
