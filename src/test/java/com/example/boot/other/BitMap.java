package com.example.boot.other;

/**
 * @author dengjia on 2020/3/26
 */

public class BitMap { // Java中char类型占16bit，也即是2个字节
    private char[] bytes;
    private int nbits;

    public BitMap(int nbits) {
        this.nbits = nbits;
        // char 是16位
        this.bytes = new char[nbits / 16 + 1];
    }

    /**
     * 将数字 A 的第 k 位设置为1：A = A | (1 << (k - 1))
     * 将数字 A 的第 k 位设置为0：A = A & ~(1 << (k - 1))
     * 检测数字 A 的第 k 位：A & (1 << (k - 1)) != 0
     * 用于理解bitmap中代码
     * @param k
     */
    public void set(int k) {
        if (k > nbits) return;
        int byteIndex = k / 16;
        int bitIndex = k % 16;
        bytes[byteIndex] |= (1 << bitIndex);
    }

    public boolean get(int k) {
        if (k > nbits) return false;
        int byteIndex = k / 16;
        int bitIndex = k % 16;
        return (bytes[byteIndex] & (1 << bitIndex)) != 0;
    }


    public static void main(String[] args) {
        final BitMap bitMap = new BitMap(64);
        bitMap.set(4);
        final boolean result = bitMap.get(4);
        System.out.println("result: " + result);
    }
}
