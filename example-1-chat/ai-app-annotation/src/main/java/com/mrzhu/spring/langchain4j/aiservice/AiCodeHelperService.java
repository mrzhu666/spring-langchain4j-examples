package com.mrzhu.spring.langchain4j.aiservice;

import com.mrzhu.spring.langchain4j.config.chatmodel.ChatModelConfig;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT, chatModel = ChatModelConfig.defaultChatModelName)
public interface AiCodeHelperService {
    /**
     * 外部文件提示词
     */
    @SystemMessage(fromResource = "prompt/aicodehelper-system-prompt.txt")
    String chat(String userMessage);
}
