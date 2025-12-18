package com.liren.user;

import com.liren.common.constant.Constants;
import com.liren.common.utils.JsonUtil;
import com.liren.user.dataobject.UserInfo;
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
        // 构建一个合法的 UserInfo 对象
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("testUser");
        userInfo.setEmail("2916776007@qq.com"); // 确保有邮箱，否则业务逻辑可能会出问题

        // 转换为 JSON 字符串发送
        String msg = JsonUtil.toJson(userInfo);

        rabbitTemplate.convertAndSend(Constants.USER_EXCHANGE_NAME, "", msg);
    }
}