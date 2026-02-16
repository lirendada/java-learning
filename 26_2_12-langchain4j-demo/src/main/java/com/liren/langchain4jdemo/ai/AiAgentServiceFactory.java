package com.liren.langchain4jdemo.ai;

import com.liren.langchain4jdemo.ai.tools.AmapWeatherTool;
import com.liren.langchain4jdemo.ai.tools.OrderCalculatorTool;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiAgentServiceFactory {

    @Resource
    private ChatModel qwenChatModel;

    @Resource
    private ContentRetriever contentRetriever;

    @Resource
    private McpToolProvider mcpToolProvider;

    @Resource
    private StreamingChatModel qwenStreamingChatModel;

    @Bean
    public AiAgentService aiAgentService() {

        AiAgentService agentService = AiServices.builder(AiAgentService.class)
                .chatModel(qwenChatModel)
                .streamingChatModel(qwenStreamingChatModel)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
                .contentRetriever(contentRetriever) // rag检索功能增强
                .tools(new OrderCalculatorTool()   // 注册工具类
//                       new AmapWeatherTool()
                )
                .toolProvider(mcpToolProvider)     // 注册mcp服务
                .build();

        return agentService;
    }
}
