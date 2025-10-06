package com.mrzhu.spring.langchain4j.example1.controller;

import cn.hutool.json.JSONUtil;
import com.mrzhu.spring.langchain4j.common.BaseResponse;
import com.mrzhu.spring.langchain4j.common.ResultUtils;
import com.mrzhu.spring.langchain4j.example1.aiservice.AiCodeHelperService;
import com.mrzhu.spring.langchain4j.example1.aiservice.Assistant;
import com.mrzhu.spring.langchain4j.example1.aiservice.StreamingAssistant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
@Tag(name = "AI接口")
public class AIController {
    
    @Resource
    Assistant assistant;
    
    @Resource
    StreamingAssistant streamingAssistant;
    
    @Resource
    AiCodeHelperService aiCodeHelperService;
    
    
    @Operation(summary = "编程领域助手")
    @GetMapping("/aiCodeHelper")
    public BaseResponse<String> aiCodeHelper(
        @RequestParam(value = "message", defaultValue = "如何学习Java") String message) {
        return ResultUtils.success(aiCodeHelperService.chat(message));
    }
    
    
    /**
     * 请求当前时间时，会调用工具
     *
     * @param message 默认请求当前时间
     * @return
     */
    @Operation(summary = "默认会请求当前时间")
    @GetMapping("/assistant")
    public BaseResponse<String> assistant(@RequestParam(value = "message", defaultValue = "What is the current time?") String message) {
        return ResultUtils.success(assistant.chat(message));
    }
    
    /**
     * 请求当前时间时，会调用工具
     *
     * @param message 默认请求当前时间
     * @return
     */
    @Operation(summary = "默认会请求当前时间（流式接口）")
    @GetMapping(value = "/streamingAssistant", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamingAssistant(
        @RequestParam(value = "message", defaultValue = "What is the current time?") String message) {
        return streamingAssistant.chat(message);
    }
    
    /**
     * 请求当前时间时，会调用工具
     *
     * @param message 默认请求当前时间
     * @return
     */
    @Operation(summary = "默认会请求当前时间（流式接口），采用事件")
    @GetMapping(value = "/streamingSSEAssistant", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> streamingSSEAssistant(
        @RequestParam(value = "message", defaultValue = "What is the current time?") String message) {
        
        // 调用服务生成代码（SSE 流式返回）
        Flux<String> contentFlux = streamingAssistant.chat(message);
        return contentFlux
            .map(chunk -> {
                Map<String, String> wrapper = Map.of("d", chunk);
                String jsonData = JSONUtil.toJsonStr(wrapper);
                return ServerSentEvent.<String>builder()
                    .data(jsonData)
                    .build();
            })
            .concatWith(Mono.just(
                // 发送结束事件
                ServerSentEvent.<String>builder()
                    .event("done")
                    .data("")
                    .build()
            ));
        
    }
    
}
