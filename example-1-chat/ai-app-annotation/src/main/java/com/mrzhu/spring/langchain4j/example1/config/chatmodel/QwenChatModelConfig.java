package com.mrzhu.spring.langchain4j.example1.config.chatmodel;


import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 创建不同的大模型
 */
@Configuration
@ConfigurationProperties(prefix = "langchain4j.open-ai.qwen-chat-model")
@Data
public class QwenChatModelConfig {
    
    public final static String chatModelName = "qwenChatModel";
    
    private String baseUrl;
    
    private String apiKey;
    
    private String modelName;
    
    private Integer maxTokens;
    
    private Double temperature;
    
    private Boolean logRequests = false;
    
    private Boolean logResponses = false;
    
    /**
     * 创建用于路由判断的ChatModel
     */
    @Bean(chatModelName)
    @Scope("prototype")
    public ChatModel chatModelPrototype() {
        return OpenAiChatModel.builder()
            .apiKey(apiKey)
            .modelName(modelName)
            .baseUrl(baseUrl)
            .maxTokens(maxTokens)
            .temperature(temperature)
            .logRequests(logRequests)
            .logResponses(logResponses)
            .build();
    }
}
