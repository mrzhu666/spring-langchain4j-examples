package com.mrzhu.spring.langchain4j.example1.config;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatModelConfig {
    
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
