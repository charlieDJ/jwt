package com.example.boot.common.enumeration;

import lombok.Getter;

/**
 * @author dengjia
 * @date 2019/8/14 9:40
 */
@Getter
public enum ImsiFlag {
    /**
     * imsi状态
     * 1：可用
     * 2：已占用
     */
    AVAILABLE(1, "可用"),
    OCCUPY(2, "已占用"),
    ;


    ImsiFlag(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;


    /*@JsonCreator
    public static ImsiFlag from(int code) {
        for (ImsiFlag imsiFlag : values()) {
            if (imsiFlag.code == code) {
                return imsiFlag;
            }
        }
        return null;
    }

    @JsonValue
    public int getValue() {
        return code;
    }*/


}
