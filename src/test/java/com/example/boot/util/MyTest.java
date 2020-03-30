package com.example.boot.util;

import org.springframework.data.convert.Jsr310Converters;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author dengjia on 2020/1/15
 */
public class MyTest {
    public static void main(String[] args) {
        final Date date = new Date();
        final Jsr310Converters.DateToLocalDateConverter instance = Jsr310Converters.DateToLocalDateConverter.INSTANCE;
        final LocalDate localDate1 = instance.convert(date);
        System.err.println(localDate1.getMonthValue());
    }
}
