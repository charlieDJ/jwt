package com.example.boot.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@Component
public class CacheConfig {
    @Bean
    public CacheManager cacheManager(){
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<CaffeineCache> caches = new ArrayList<>();
        CaffeineCache dept = new CaffeineCache("dept", Caffeine.newBuilder().recordStats()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .maximumSize(1000)
                .build());
        caches.add(dept);
        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
