package com.example.boot.algorithm.threeSum;

import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        Set<List<Integer>> set = new HashSet<>();
        //要先对数组进行排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            //重复的过滤掉，上一轮找到解了
            final int x = nums[i];
            if (i > 0 && x == nums[i - 1]){
                continue;
            }
            Map<Integer, Integer> targetMap = new HashMap<>();
            for (int j = i + 1; j < nums.length; j++) {
                final int y = nums[j];
                // 如果这个值在当前map中已经存在，说明至少循环了一次，只要它的值等于上一次的 -(x+y) 就说明存在解
                // a + b + c = 0，那么 a = -(b + c)
                if (targetMap.containsKey(y) && targetMap.get(y) == 0) {
                    // 去重
                    set.add(Arrays.asList(x, y, -(x + y)));
                    targetMap.put(y, 1);
                } else {
                    targetMap.put(-(x + y), 0);
                }
            }
        }
        return new ArrayList<>(set);
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        final List<List<Integer>> lists = solution.threeSum(nums);
        System.out.println("Arrays.toString(lists.toArray()) = " + Arrays.toString(lists.toArray()));
    }
}
