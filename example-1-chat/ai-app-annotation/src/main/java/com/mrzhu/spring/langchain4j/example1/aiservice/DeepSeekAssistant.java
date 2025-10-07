package com.mrzhu.spring.langchain4j.example1.aiservice;

import com.mrzhu.spring.langchain4j.example1.config.DeepSeekChatModelConfig;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT, chatModel = DeepSeekChatModelConfig.chatModelName)
public interface DeepSeekAssistant {
    @SystemMessage("You are a polite assistant")
    String chat(String userMessage);
}
