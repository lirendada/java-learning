package com.liren.langchain4jdemo;

import com.liren.langchain4jdemo.ai.AiAgentService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Resource
    private AiAgentService aiAgentService;

    @Test
    void contextLoads() {
        String chat1 = aiAgentService.chat(1, "你好我是liren");
        System.out.println(chat1);

        String chat2 = aiAgentService.chat(2, "你好我是lirendada");
        System.out.println(chat2);

        String chat3 = aiAgentService.chat(1, "我叫什么？");
        System.out.println(chat3);

        String chat4 = aiAgentService.chat(2, "我的名字是什么？");
        System.out.println(chat4);
    }

}
