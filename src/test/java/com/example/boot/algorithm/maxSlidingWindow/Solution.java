package com.example.boot.algorithm.maxSlidingWindow;

import java.util.ArrayDeque;
import java.util.Arrays;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        //双端队列存的是索引
        ArrayDeque<Integer> window = new ArrayDeque<>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            // x 是每次新进入的选手，也就是值
            int x = nums[i];
            // 假设窗口的移动，超出了window的范围，因为window只有k的长度，所以 i-k 就是window的左界
            // 如果这个元素超出了左界，就必须从window中移出
            if (i >= k && window.getFirst() <= i - k) {
                window.pop();
            }
            // 只要x大于nums数组左侧的数字，window中的索引就要退出去。去掉的这些值比x小，索引值又小于x，没必要留在滑动窗口中
            while (!window.isEmpty() && nums[window.peekLast()] < x) {
                window.removeLast();
            }
            // 清理完毕之后，把这次的下标加入到滑动窗口中
            window.add(i);
            // window最左侧的值就是当前窗口最大的值，
            if (i - k + 1 >= 0) {
                res[i - k + 1] = nums[window.getFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 1, 2, 0, 5};
        int k = 3;
        final Solution solution = new Solution();
        final int[] res = solution.maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(res));
    }
}
