package com.mrzhu.spring.langchain4j.example1.controller;

import com.mrzhu.spring.langchain4j.example1.aiservice.Assistant;
import com.mrzhu.spring.langchain4j.example1.aiservice.StreamingAssistant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
@Tag(name = "AI接口")
public class AIController {
    
    @Resource
    Assistant assistant;
    
    @Resource
    StreamingAssistant streamingAssistant;
    
    /**
     * 请求当前时间时，会调用工具
     * @param message 默认请求当前时间
     * @return
     */
    @Operation(summary = "默认会请求当前时间")
    @GetMapping("/assistant")
    public String assistant(@RequestParam(value = "message", defaultValue = "What is the current time?") String message) {
        return assistant.chat(message);
    }
    
    /**
     * 请求当前时间时，会调用工具
     * @param message 默认请求当前时间
     * @return
     */
    @Operation(summary = "默认会请求当前时间（流式接口）")
    @GetMapping(value = "/streamingAssistant", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamingAssistant(
        @RequestParam(value = "message", defaultValue = "What is the current time?") String message) {
        return streamingAssistant.chat(message);
    }
}
