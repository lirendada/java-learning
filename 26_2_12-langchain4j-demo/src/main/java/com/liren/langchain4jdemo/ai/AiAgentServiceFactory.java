package com.liren.langchain4jdemo.ai;

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
        return AiServices.create(AiAgentService.class, qwenChatModel);
    }
}
