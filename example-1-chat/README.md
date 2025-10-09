参考：[Spring Boot Integration | LangChain4j](https://docs.langchain4j.dev/tutorials/spring-boot-integration/)。官方给的案例`langchain4j-examples`使用纯注解开发的案例太少

聚焦于LangChain4j提供的各种用法。

- 提示词简单聊天，包括流式接口
- 基于内存记忆测试

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

# 多个大模型同时使用

`com.mrzhu.spring.langchain4j.example1.config.chatmodel` 里配置不同的大模型，需要手动配置

默认的大模型由以下配置自动创建：

```yaml
langchain4j:
  open-ai:
    chat-model:
      base-url: http://langchain4j.dev/demo/openai/v1
      api-key: demo
      model-name: gpt-4o-mini
    streaming-chat-model:
      base-url: http://langchain4j.dev/demo/openai/v1
      api-key: demo
      model-name: gpt-4o-mini
```









