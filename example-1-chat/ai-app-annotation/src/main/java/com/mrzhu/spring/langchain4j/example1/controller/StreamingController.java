package com.mrzhu.spring.langchain4j.example1.controller;

import cn.hutool.json.JSONUtil;
import com.mrzhu.spring.langchain4j.example1.aiservice.StreamingAssistant;
import dev.langchain4j.service.TokenStream;
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
@Tag(name = "流式接口")
public class StreamingController {
    
    @Resource
    StreamingAssistant streamingAssistant;
    
    /**
     * 请求当前时间时，会调用工具
     *
     * @param message 默认请求当前时间
     * @return
     */
    @Operation(summary = "默认会请求当前时间")
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
    @Operation(summary = "tokenStream返回")
    @GetMapping(value = "/tokenStreamAssistant", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<String> tokenStreamAssistant(
        @RequestParam(value = "message", defaultValue = "What is the current time?") String message) {
        TokenStream tokenStream = streamingAssistant.chatToken(message);
        return Flux.create(sink -> {
            tokenStream
                .onPartialResponse(sink::next) // 每接收到一个 token，就推送到前端
                .onCompleteResponse(chatResponse -> sink.complete()) // 完成流
                .onError(sink::error) // 异常处理
                .start(); // 最后一定要调用 start() 启动流式响应
        });
    }
    
    /**
     * 请求当前时间时，会调用工具
     *
     * @param message 默认请求当前时间
     * @return
     */
    @Operation(summary = "JSON包装数据，结束时返回done事件")
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
