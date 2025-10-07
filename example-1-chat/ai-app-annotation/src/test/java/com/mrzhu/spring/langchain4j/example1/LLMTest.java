package com.mrzhu.spring.langchain4j.example1;

import com.mrzhu.spring.langchain4j.example1.aiservice.Assistant;
import com.mrzhu.spring.langchain4j.example1.aiservice.MemoryAssistant;
import com.mrzhu.spring.langchain4j.example1.aiservice.QwenAssistant;
import com.mrzhu.spring.langchain4j.example1.config.MyChatModelListener;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
public class LLMTest {
    
    // 用于取消MyChatModelListener自动加载
    @MockitoBean
    private MyChatModelListener myChatModelListener;
    
    @Resource
    Assistant assistant;
    
    @Resource
    QwenAssistant qwenAssistant;
    
    /**
     * 测试同时使用不同大模型
     */
    @Test
    public void test(){
        String chat = assistant.chat("请问你使用的是什么大模型?");
        System.out.println(chat);
        
        String chat1 = qwenAssistant.chat("请问你使用的是什么大模型?");
        System.out.println(chat1);
    }
    
}
