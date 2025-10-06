package com.mrzhu.spring.langchain4j.example1.aiservice;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface AiCodeHelperService {
    /**
     * 外部文件提示词
     */
    @SystemMessage(fromResource = "prompt/aicodehelper-system-prompt.txt")
    String chat(String userMessage);
}
