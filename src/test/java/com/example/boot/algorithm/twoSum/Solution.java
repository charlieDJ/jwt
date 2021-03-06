package com.example.boot.algorithm.twoSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 数组内的数字为key，数组的下标为value
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        final Solution test = new Solution();
        final int[] nums = {2, 7, 15, 19};
        final int[] ints = test.twoSum(nums, 9);
        System.out.println(Arrays.toString(ints));
    }
}
