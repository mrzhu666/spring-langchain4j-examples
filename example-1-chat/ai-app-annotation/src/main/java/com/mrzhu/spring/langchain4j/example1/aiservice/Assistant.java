package com.mrzhu.spring.langchain4j.example1.aiservice;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService()
public interface Assistant {
    /**
     * 硬编码提示词
     */
    @SystemMessage("You are a polite assistant")
    String chat(String userMessage);
}
