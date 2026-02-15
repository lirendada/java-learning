package com.liren.langchain4jdemo.ai;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

import java.util.List;

public interface AiAgentService {

    @SystemMessage(fromResource = "system-prompt.txt")
    String chat(@MemoryId int memoryId, @UserMessage String userMessage);


    // -------------- 结构化输出 ----------------
    record Report(String name, List<String> suggestions) {}

    @SystemMessage(fromResource = "system-prompt.txt")
    Report chatToReport(String userMessage);
    // -----------------------------------------


    // -------------- RAG ----------------
    @SystemMessage(fromResource = "system-prompt.txt")
    Result<String> chatWithRAG(@UserMessage String userMessage);
    // -----------------------------------------


    // ----------------工具调用------------------
    @SystemMessage("你是一个电商订单助手，如果涉及金额计算必须调用工具")
    String chatWithCalculatorTool(@UserMessage String userMessage);

    @SystemMessage("你是天气查询助手，需要调用高德地图天气 API 来回答用户问题")
    String chatWithWeatherTool(String userQuery);
    // -----------------------------------------


    // ----------------MCP调用------------------
    String chatWithMcp(@UserMessage String userMessage);
    // -----------------------------------------
}
