参考：[Spring Boot Integration | LangChain4j](https://docs.langchain4j.dev/tutorials/spring-boot-integration/)。官方给的案例`langchain4j-examples`使用纯注解开发的案例太少

- 提示词简单聊天，包括流式接口
- 记忆测试

`@AiSerivce` 注解里面有相关的字段，chatModel、chatMemory等，但怎么使用网上完全没有案例

```java
public @interface AiService {
    dev.langchain4j.service.spring.AiServiceWiringMode wiringMode() default dev.langchain4j.service.spring.AiServiceWiringMode.AUTOMATIC;

    java.lang.String chatModel() default "";

    java.lang.String streamingChatModel() default "";

    java.lang.String chatMemory() default "";

    java.lang.String chatMemoryProvider() default "";

    java.lang.String contentRetriever() default "";

    java.lang.String retrievalAugmentor() default "";

    java.lang.String moderationModel() default "";

    java.lang.String[] tools() default {};
}
```

