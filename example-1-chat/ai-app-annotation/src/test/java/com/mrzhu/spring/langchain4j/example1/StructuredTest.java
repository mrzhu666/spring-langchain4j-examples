package com.mrzhu.spring.langchain4j.example1;

import com.mrzhu.spring.langchain4j.example1.aiservice.StructuredAssistant;
import com.mrzhu.spring.langchain4j.example1.aiservice.pojo.Person;
import com.mrzhu.spring.langchain4j.example1.config.chatmodel.MyChatModelListener;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
public class StructuredTest {
    
    @Resource
    StructuredAssistant structuredAssistant;
    
    // 用于取消MyChatModelListener自动加载
    @MockitoBean
    private MyChatModelListener myChatModelListener;
    
    /**
     * 提取个人信息，结构化输出测试
     */
    @Test
    public void test(){
        String text = """
            In 1968, amidst the fading echoes of Independence Day,
            a child named John arrived under the calm evening sky.
            This newborn, bearing the surname Doe, marked the start of a new journey.
            He was welcomed into the world at 345 Whispering Pines Avenue
            a quaint street nestled in the heart of Springfield
            an abode that echoed with the gentle hum of suburban dreams and aspirations.
            """;
        
        Person person = structuredAssistant.extractPersonFrom(text);
        
        System.out.println(person);
        // Person { firstName = "John", lastName = "Doe", birthDate = 1968-07-04, address = Address { ... } }
    }
    
}
