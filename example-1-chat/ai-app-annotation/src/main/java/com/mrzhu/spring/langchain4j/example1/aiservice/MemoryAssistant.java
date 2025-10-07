package com.mrzhu.spring.langchain4j.example1.aiservice;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface MemoryAssistant {
    
    /**
     * 相比 {@link Assistant} 增加了记忆
     * 如果没有指定记忆ID，似乎所有对话都是共用的。
     */
    @SystemMessage("You are a polite assistant")
    String chat(String userMessage);
    
    /**
     * 对话ID记忆
     */
    @SystemMessage("You are a polite assistant")
    String chat(@MemoryId int memoryId, @UserMessage String userMessage);
}
