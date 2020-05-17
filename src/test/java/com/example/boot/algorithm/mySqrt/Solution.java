package com.example.boot.algorithm.mySqrt;

class Solution {


    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        int l = 1, r = x;
        int res = 0;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (m == x /  m) {
                // 如果 m 的平方等于 x，直接返回
                return m;
            } else if (m > x / m) {
                // 如果 m 的平方大于 x，说明 x 的平方根在 m 的左侧，更新 r 的值
                r = m - 1;
            } else {
                l = m + 1;
                res = m;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("solution.mySqrt() = " + solution.mySqrt(17));
    }

}
