package com.mrzhu.spring.langchain4j.config.chatmodel;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ChatModelConfig {
    
    // langchain4j框架默认创建的ChatModel名字
    public final static String defaultChatModelName = "openAiChatModel";
    
    // langchain4j框架默认创建的StreamingChatModel名字
    public final static String defaultStreamingChatModelName = "openAiStreamingChatModel";
    
    
    /**
     * @return 对话记忆
     */
    @Bean
    ChatMemory chatMemory(){
        return MessageWindowChatMemory.withMaxMessages(10);
    }
    
    /**
     * @return 根据对话id，创建对应对话记忆
     */
    @Bean
    ChatMemoryProvider chatMemoryProvider() {
        return memoryId -> MessageWindowChatMemory.withMaxMessages(10);
    }
    
}
