package com.liren.langchain4jdemo.ai;

import dev.langchain4j.service.SystemMessage;

public interface AiAgentService {

    @SystemMessage(fromResource = "system-prompt.txt")
    String chat(String message);
}
