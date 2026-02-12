package com.liren.langchain4jdemo.ai;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiAgentServiceFactory {

    @Resource
    private ChatModel qwenChatModel;

    @Bean
    public AiAgentService aiAgentService() {

        AiAgentService agentService = AiServices.builder(AiAgentService.class)
                .chatModel(qwenChatModel)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
                .build();

        return agentService;
    }
}
