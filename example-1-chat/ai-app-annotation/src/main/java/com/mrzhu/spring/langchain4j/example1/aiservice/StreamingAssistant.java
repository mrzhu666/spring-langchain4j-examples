package com.mrzhu.spring.langchain4j.example1.aiservice;

import com.mrzhu.spring.langchain4j.example1.config.chatmodel.ChatModelConfig;
import com.mrzhu.spring.langchain4j.example1.tools.AssistantTools;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT, chatModel = ChatModelConfig.defaultChatModelName,streamingChatModel = ChatModelConfig.defaultStreamingChatModelName)
public interface StreamingAssistant {
    /**
     * 相比 {@link Assistant} 使用了流式接口
     */
    @SystemMessage("You are a polite assistant")
    Flux<String> chat(String userMessage);
    
    
    @SystemMessage("You are a polite assistant")
    TokenStream chatToken(String userMessage);
}
