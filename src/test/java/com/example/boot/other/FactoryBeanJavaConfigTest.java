package com.example.boot.other;

import com.example.boot.JwtApplicationTests;
import com.example.boot.dao.model.Tool;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FactoryBeanJavaConfigTest extends JwtApplicationTests {

    @Autowired
    private Tool tool;

    @Test
    public void testConstructWorkerByJava() {
        Assert.assertEquals(999, tool.getId().intValue());
    }
}
