package com.example.boot.common.enumeration;

import com.example.boot.common.config.handler.LabelValue;
import lombok.Getter;

/**
 * @author dengjia on 2020/1/9
 */
@Getter
public enum Level implements LabelValue {

    /**
     * s
     */
    ONE(1, "一级"),
    TWO(2, "二级");
    private int code;

    private String desc;

    Level(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getLabel() {
        return name();
    }

    @Override
    public Integer getValue() {
        return code;
    }
}
