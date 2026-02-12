package com.liren.langchain4jdemo.ai;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AiAgentServiceFactoryTest {

    @Resource
    private AiAgentService aiAgentService;

    @Test
    void chat() {
        String chat = aiAgentService.chat("你好我是lirendada！");
        System.out.println(chat);
    }
}