package com.example.boot.algorithm.myPow;

class Solution {

    private double fastPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        // 折半算法
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            // 奇数还要乘以自己
            return half * half * x;
        }
    }

    public double myPow(double x, int n) {
        // 防止溢出
        long N = n;
        // 如果n是负数，使用倒数，然后把n变为正数
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        return fastPow(x, N);
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println("solution = " + solution.myPow(2d, 4));
    }


}
