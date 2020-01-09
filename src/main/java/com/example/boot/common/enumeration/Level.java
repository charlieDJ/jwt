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
    ONE(1),
    TWO(2);
    private int code;

    Level(int code) {
        this.code = code;
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
