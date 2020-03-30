package com.example.boot.common.enumeration;

import com.example.boot.common.util.ArithmeticUtils;
import lombok.Getter;

import java.util.function.BinaryOperator;

/**
 * @author dengjia on 2020/1/15
 */
@Getter
public enum NumberOperate {
    /**
     * 计算
     */
    MUL(ArithmeticUtils::mul),
    DIV(ArithmeticUtils::div),;

    private BinaryOperator<Double> operator;

    NumberOperate(BinaryOperator<Double> operator) {
        this.operator = operator;
    }
}
