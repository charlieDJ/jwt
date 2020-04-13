package com.example.boot.algorithm.majorityElement;

import java.util.Arrays;

class Solution {


    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        final Solution solution = new Solution();
        System.out.println("solution.majorityElement(nums) = " + solution.majorityElement(nums));
    }
}
