package com.example.boot.converter;


import com.example.boot.JwtApplicationTests;
import com.example.boot.common.converter.String2DateConverter;
import com.example.boot.common.util.SpringHolder;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class String2DateTest extends JwtApplicationTests {

    @Test
    public void convertTest(){
        String2DateConverter dateConverter = SpringHolder.getBean(String2DateConverter.class);
        LocalDate date = dateConverter.convert("2015-09-09");
        Assert.assertEquals(9, date.getDayOfMonth());
    }

}
