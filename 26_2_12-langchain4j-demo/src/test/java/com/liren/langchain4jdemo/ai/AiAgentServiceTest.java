package com.liren.langchain4jdemo.ai;

import dev.langchain4j.service.Result;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Test
    void chatWithCalculator() {
        String s = aiAgentService.chatWithCalculatorTool("单价 99.8 元，买 3 件，打 8 折，一共多少钱？");
        System.out.println(s);
    }

    @Test
    void chatWithWeather() {
        String s = aiAgentService.chatWithWeatherTool("今天汕头市的天气怎么样？");
        System.out.println(s);
    }

    @Test
    void chatWithMcp() {
        String result = aiAgentService.chatWithMcp("利刃大大的博客怎么样？");
        System.out.println(result);
    }

}