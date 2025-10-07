package com.mrzhu.spring.langchain4j.example1;

import com.mrzhu.spring.langchain4j.example1.aiservice.MemoryAssistant;
import com.mrzhu.spring.langchain4j.example1.config.MyChatModelListener;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


@SpringBootTest

public class MemoryTest {
    @Resource
    MemoryAssistant memoryAssistant;
    
    // 用于取消MyChatModelListener自动加载
    @MockitoBean
    private MyChatModelListener myChatModelListener;
    
    
    @Test
    public void testMemoryAssistant() {
        String chat = memoryAssistant.chat("你好，我是程序员Y");
        System.out.println(chat);
        String chat2 = memoryAssistant.chat("你好，我是谁来着？");
        System.out.println(chat2);
        
    }
    
    
    /**
     * 不同ID记忆测试
     */
    @Test
    public void testIdMemoryAssistant() {
        String userMessage;
        
        userMessage = "你好，我是程序员Q1";
        System.out.println(memoryAssistant.chat(1, userMessage));
        userMessage = "你好，我是程序员Y2";
        System.out.println(memoryAssistant.chat(2, userMessage));
        
        userMessage = "你好，我是谁来着？";
        System.out.println(memoryAssistant.chat(1, userMessage));
        userMessage = "你好，我是谁来着？";
        System.out.println(memoryAssistant.chat(2, userMessage));
    }
    
}
