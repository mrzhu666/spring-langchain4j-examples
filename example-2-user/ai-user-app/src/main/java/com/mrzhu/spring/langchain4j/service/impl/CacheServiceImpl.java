package com.mrzhu.spring.langchain4j.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.mrzhu.spring.langchain4j.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class CacheServiceImpl implements CacheService {
    /**
     * 缓存服务
     */
    private final Cache<Long, String> serviceCache = Caffeine.newBuilder()
        .maximumSize(1000)
        .expireAfterWrite(30, TimeUnit.MINUTES)
        .expireAfterAccess(10, TimeUnit.MINUTES)
        .removalListener((key, value, cause) -> {
            log.debug("缓存被移除，缓存键: {}, 原因: {}", key, cause);
        })
        .build();
    
    @Override
    public String getCache(Long userId) {
        String s = serviceCache.getIfPresent(userId);
        if (s != null) {
            log.info("缓存获取");
            return s;
        }
        log.info("缓存不存在");
        String value = String.valueOf(userId);
        serviceCache.put(userId, value);
        return value;
    }
}
