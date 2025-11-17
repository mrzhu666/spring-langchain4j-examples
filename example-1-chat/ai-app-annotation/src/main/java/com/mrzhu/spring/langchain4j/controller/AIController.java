package com.mrzhu.spring.langchain4j.controller;

import com.mrzhu.spring.langchain4j.common.BaseResponse;
import com.mrzhu.spring.langchain4j.common.ResultUtils;
import com.mrzhu.spring.langchain4j.aiservice.AiCodeHelperService;
import com.mrzhu.spring.langchain4j.aiservice.Assistant;
import com.mrzhu.spring.langchain4j.aiservice.StructuredAssistant;
import com.mrzhu.spring.langchain4j.aiservice.pojo.Report;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "AI接口")
public class AIController {
    
    @Resource
    Assistant assistant;
    

    
    @Resource
    AiCodeHelperService aiCodeHelperService;
    
    @Resource
    StructuredAssistant structuredAssistant;
    
    @Operation(summary = "获取学习建议")
    @GetMapping("/suggestion")
    public BaseResponse<Report> getSuggestion(
        @RequestParam(value = "message", defaultValue = "你好，我是程序员Y，如何学习Java") String message
    ){
        return ResultUtils.success(structuredAssistant.getSuggestion(message));
    }
    
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

    
}
