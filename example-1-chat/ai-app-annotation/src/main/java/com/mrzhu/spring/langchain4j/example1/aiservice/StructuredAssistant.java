package com.mrzhu.spring.langchain4j.example1.aiservice;


import com.mrzhu.spring.langchain4j.example1.aiservice.pojo.Person;
import com.mrzhu.spring.langchain4j.example1.aiservice.pojo.Report;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService()
public interface StructuredAssistant {
    
    /**
     * 获取学习建议
     */
    @SystemMessage(fromResource = "prompt/aicodehelper-system-prompt.txt")
    Report getSuggestion(String message);
    
    
    /**
     * 从文本中提取个人信息
     */
    @UserMessage("Extract information about a person from {{it}}")
    Person extractPersonFrom(String text);
}
