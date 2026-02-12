package com.liren.langchain4jdemo.ai;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AiAgentTest {
    @Resource
    private AiAgent aiAgent;

    @Test
    void chat() {
        aiAgent.chat("你好，我是lirendada！");
    }
}