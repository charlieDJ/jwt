package com.example.boot.repository;

import com.example.boot.JwtApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class DeptTest extends JwtApplicationTests {

    @Autowired
    private DeptRepository deptRepository;

    @Test
    public void testCache() throws InterruptedException {
        deptRepository.getById(1).ifPresent(System.out::println);
        deptRepository.getById(1).ifPresent(System.out::println);
        TimeUnit.SECONDS.sleep(2);
        deptRepository.getById(1).ifPresent(System.out::println);
    }

}
