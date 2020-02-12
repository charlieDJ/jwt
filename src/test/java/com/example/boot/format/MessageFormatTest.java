package com.example.boot.format;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MessageFormatTest {
    public static void main(String[] args) {
        // 信息格式化串
        String pattern = "{0}，你好！你于{1}在工商银行存入{2}元";
        // 用于动态替换占位符的参数
        Object[] params = {"John", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 10.1};
        String formattedMsg = MessageFormat.format(pattern, params);
        System.out.println("格式化的信息：" + formattedMsg);
    }
}
