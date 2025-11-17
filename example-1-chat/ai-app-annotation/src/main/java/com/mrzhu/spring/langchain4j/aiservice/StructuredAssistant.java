package com.mrzhu.spring.langchain4j.aiservice;


import com.mrzhu.spring.langchain4j.config.chatmodel.ChatModelConfig;
import com.mrzhu.spring.langchain4j.aiservice.pojo.Person;
import com.mrzhu.spring.langchain4j.aiservice.pojo.Report;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT, chatModel = ChatModelConfig.defaultChatModelName)
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
