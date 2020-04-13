package com.example.boot.algorithm.maxProfit;

class Solution {
    /**
     * 贪心算法
     *
     * @param prices 数组
     * @return 最大利润
     */
    public int maxProfit(int[] prices) {
        // 暂时保存上一支股票的价格
        int pre = 0;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            final int x = prices[i];
            if (i == 0) {
                pre = x;
                continue;
            }
            if (x > pre) {
                profit += x - pre;
            }
            pre = x;
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] nums = {7,6,4,3,1};
        final Solution solution = new Solution();
        System.out.println("solution.maxProfit(nums) = " + solution.maxProfit(nums));
    }
}
