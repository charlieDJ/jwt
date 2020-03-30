package com.example.boot.other;

/**
 * @author dengjia on 2020/3/25
 * 多态、对象引用
 */
public class Human {

    static class A extends Exception {

    }

    static class B extends A {
    }


    public static void main(String[] args) {
        try {
            try {
                throw new B();
            } catch (A a) {
                System.out.println("caught A");
                throw a;
            }
        } catch (B b) {
            System.out.println("caught B");
            return;
        } finally {
            System.out.println("hello world");
        }
    }
}
