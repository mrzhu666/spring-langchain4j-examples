package com.mrzhu.spring.langchain4j.example1.aiservice;

import com.mrzhu.spring.langchain4j.example1.config.chatmodel.ChatModelConfig;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT, chatModel = ChatModelConfig.defaultChatModelName)
public interface StreamingAssistant {
    /**
     * 相比 {@link Assistant} 使用了流式接口
     */
    @SystemMessage("You are a polite assistant")
    Flux<String> chat(String userMessage);
}
