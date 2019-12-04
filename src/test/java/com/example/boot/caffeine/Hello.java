package com.example.boot.caffeine;

import com.example.boot.JwtApplicationTests;
import com.example.boot.dao.mapper.DeptMapper;
import com.example.boot.dao.model.Dept;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Hello extends JwtApplicationTests {

    @Autowired
    private DeptMapper deptMapper;

    @Test
    public void getDept() throws InterruptedException {
        Cache<String, List<Dept>> cache = Caffeine.newBuilder().maximumSize(1000)
                .expireAfterWrite(Duration.ofSeconds(3))
                .build();
        String key = "dept";
        cache.put(key, deptMapper.selectAll());
        System.out.println(cache.get(key, k -> new ArrayList<>()).size());
        System.out.println(cache.get(key, k -> new ArrayList<>()).size());
        TimeUnit.SECONDS.sleep(3);
        System.out.println(cache.get(key, k -> new ArrayList<>()).size());
    }

}
