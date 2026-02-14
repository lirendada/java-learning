package com.liren.langchain4jdemo.ai;

import dev.langchain4j.service.Result;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AiAgentServiceTest {
    @Resource
    private AiAgentService aiAgentService;

    @Test
    void chatToReport() {
        AiAgentService.Report report = aiAgentService.chatToReport("你好我是lirendada，请给我制定一个职业规划，我的目标是java开发工程师");
        System.out.println(report.toString());
    }

    @Test
    void chatWithRAG() {
        Result<String> s = aiAgentService.chatWithRAG("我想学习shell编程，都有什么常见的概念和命令吗？");
        System.out.println(s.content());
        System.out.println(s.tokenUsage());
        System.out.println(s.sources());
    }
}