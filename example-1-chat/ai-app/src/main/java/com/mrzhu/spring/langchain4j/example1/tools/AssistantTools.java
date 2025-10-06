package com.mrzhu.spring.langchain4j.example1.tools;

import dev.langchain4j.agent.tool.Tool;
import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * 工具获取当前时间
 * 它是怎么得知调用哪个工具的哪个方法？
 */
@Component
public class AssistantTools {
    /**
     * This tool is available to {@link com.mrzhu.spring.langchain4j.example1.aiservice.Assistant}
     */
    @Tool
    @Observed
    public String currentTime() {
        return LocalTime.now().toString();
    }
}
