package com.example.boot.other;

import org.apache.ibatis.parsing.GenericTokenParser;

public class TokenParseTest {
    public static void main(String[] args) {
        parse();
    }

    private static void parse() {
        String sql = "select * from jd_user where id = ${ text }";
        GenericTokenParser parser = new GenericTokenParser("${", "}", null);
        System.out.println("parseSql: " + parser.parse(sql));
    }
}
