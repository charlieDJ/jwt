package com.example.boot.util;

import com.example.boot.common.enumeration.NumberOperate;

/**
 * @author dengjia on 2020/1/15
 */
public class NumberOperateTest {

    public static void main(String[] args) {
//        mul();
        div();
    }

    private static void div() {
        System.err.println(NumberOperate.DIV.getOperator().apply(20d, 5d));
    }

    private static void mul() {
        System.err.println(NumberOperate.MUL.getOperator().apply(4d, 5d));
    }
}
