package com.mrzhu.spring.langchain4j.example1.tools;

import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * 如果不利用注解写注释，则会根据方法名来调用
 */
@Component
public class AssistantTools {
    
    /**
     * This tool is available to {@link com.mrzhu.spring.langchain4j.example1.aiservice.Assistant}
     */
    @Tool(name = "currentTime",value = """
        当用户询问当前时，请使用此工具获取当前时间
        """)
    @Observed
    public String currentTime() {
        return LocalTime.now().toString();
    }
}
