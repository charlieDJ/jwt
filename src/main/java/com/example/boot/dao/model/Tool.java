package com.example.boot.dao.model;

import lombok.Data;

@Data
public class Tool {
    private Integer id;

    public Tool(Integer id) {
        System.out.println("普通构造函数");
        this.id = id;
    }

    public Tool() {
        System.out.println("构造默认函数");
    }

    {
        System.out.println("构造代码块");
    }
}
