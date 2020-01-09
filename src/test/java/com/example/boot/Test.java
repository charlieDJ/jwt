package com.example.boot;

/**
 * @author dengjia on 2020/1/2
 */
public class Test {

    static final int HASH_BITS = 0x7fffffff; // usable bits of normal node hash
    static final int MAXIMUM_CAPACITY = 0x7fffffff; // usable bits of normal node hash

    public static void main(String[] args) {
        final int h = "hollischuang".hashCode();
        System.out.println("size: " + tableSizeFor(4));
    }

    static final int spread(int h) {
        System.out.println("0x7fffffff: " + Integer.toBinaryString(HASH_BITS));
        System.out.println("h: "+Integer.toBinaryString(h));
        System.out.println("h >>> 16: "+Integer.toBinaryString(h >>> 16));
        System.out.println("h ^ (h >>> 16): "+Integer.toBinaryString(h ^ (h >>> 16)));
        return (h ^ (h >>> 16)) & HASH_BITS;
    }

    private static final int tableSizeFor(int c) {
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }


}
