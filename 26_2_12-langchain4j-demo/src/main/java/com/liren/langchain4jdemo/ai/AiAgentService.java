package com.liren.langchain4jdemo.ai;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

import java.util.List;

public interface AiAgentService {

    @SystemMessage(fromResource = "system-prompt.txt")
    String chat(@MemoryId int memoryId, @UserMessage String userMessage);

    // 结构化输出
    record Report(String name, List<String> suggestions) {}

    @SystemMessage(fromResource = "system-prompt.txt")
    Report chatToReport(String userMessage);
}
