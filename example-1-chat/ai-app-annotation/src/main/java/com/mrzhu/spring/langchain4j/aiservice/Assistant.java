package com.mrzhu.spring.langchain4j.aiservice;

import com.mrzhu.spring.langchain4j.config.chatmodel.ChatModelConfig;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT, chatModel = ChatModelConfig.defaultChatModelName)
public interface Assistant {
    /**
     * 硬编码提示词
     */
    @SystemMessage("You are a polite assistant")
    String chat(String userMessage);
}
