package com.example.boot.other;

import com.example.boot.dao.model.Tool;

public class BlockTest {
    public static void main(String[] args) {
        Tool tool = new Tool(2);
        System.out.println("tool.id = " + tool.getId());
        tool = new Tool();
    }
}
