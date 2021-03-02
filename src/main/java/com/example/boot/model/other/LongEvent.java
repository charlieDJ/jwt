package com.example.boot.model.other;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author dj
 * @date 2021/2/24
 * 定义Event消息（事件）类
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
public class LongEvent {
    private long value;
    private String name;
}
