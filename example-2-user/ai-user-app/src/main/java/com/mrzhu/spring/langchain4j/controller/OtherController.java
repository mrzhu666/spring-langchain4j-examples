package com.mrzhu.spring.langchain4j.controller;

import com.mrzhu.spring.langchain4j.service.CacheService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "其它")
@Slf4j
public class OtherController {
    @Resource
    private CacheService cacheService;
    
    
    @Operation(summary = "测试接口")
    @Parameters({
        @Parameter(name = "test", description = "测试参数", required = true, in = ParameterIn.PATH)
    })
    @GetMapping("/test")
    public String test(@RequestParam(value = "test", defaultValue = "测试接口") String test) {
        return test;
    }
    
    
    @Operation(summary = "缓存接口测试")
    @GetMapping("/getTest")
    @Cacheable(
        value = "user_cache",
        key = "userId"
    )
    public String getTest(@RequestParam(value = "test", defaultValue = "测试接口") String test,
                          @RequestParam(value = "userId", defaultValue = "1") Long userId) {
        return cacheService.getCache(userId);
    }
    
}
