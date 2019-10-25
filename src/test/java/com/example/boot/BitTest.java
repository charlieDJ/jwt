package com.example.boot;

/**
 * @author dengjia
 * @date 2019/10/24 13:19
 */
public class BitTest {
    public static void main(String[] args) {
        System.out.println("#############################java 右移###################################");
        int a = -5;
        System.out.println(a + "的二进制形式：" + Integer.toBinaryString(a));
        // 有符号右移
        System.out.println(a + "有符号右移：" +Integer.toBinaryString(a >> 2)+",符号存在");
        // 无符号右移
        System.out.println(a + "无符号右移：" +Integer.toBinaryString(a >>> 2)+"，符号消失，被0填充，0被省略");
        System.out.println();

        System.out.println("################################java 负数 左移###############################");
        int b = Integer.MIN_VALUE;
        System.out.println(b + "的二进制形式：" + Integer.toBinaryString(b));
        System.out.println(b + "左移：" + Integer.toBinaryString(b << 2));
        System.out.println("换行符1");

        System.out.println("##################################java 正数 左移##############################");
        int c = 5;
        System.out.println(c + "的二进制形式：" + Integer.toBinaryString(c));
        System.out.println(c + "左移：" + Integer.toBinaryString(c << 2));
    }
}
