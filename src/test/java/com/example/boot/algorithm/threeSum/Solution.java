package com.example.boot.algorithm.threeSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        final Map<Integer, Integer> map = new HashMap<>();
        /*for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[i]-nums[j])
            }
        }*/
        return null;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        final List<List<Integer>> lists = solution.threeSum(nums);
        System.out.println(Arrays.toString(lists.toArray()));
    }
}
