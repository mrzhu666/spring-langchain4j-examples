package com.mrzhu.spring.langchain4j.example1.aiservice;

import com.mrzhu.spring.langchain4j.example1.config.chatmodel.DeepSeekChatModelConfig;
import com.mrzhu.spring.langchain4j.example1.config.chatmodel.GeminiChatModelConfig;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT, chatModel = GeminiChatModelConfig.chatModelName)
public interface GeminiAssistant {
    @SystemMessage("You are a polite assistant")
    String chat(String userMessage);
}
