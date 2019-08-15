package com.example.boot.common.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class GenericConfig {

    @Bean
    public OkHttpClient httpClient() {
        return new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES).readTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES).retryOnConnectionFailure(true)
                .connectionPool(new ConnectionPool(Runtime.getRuntime().availableProcessors() * 2, 5, TimeUnit.MINUTES))
                .build();
    }

}
