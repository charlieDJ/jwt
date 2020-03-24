package com.example.boot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dengjia on 2020/1/2
 */
public class Test {


    public static void main(String[] args) {
        final Test test = new Test();
        final int[] nums = {2, 7, 15, 19};
        final int[] ints = test.twoSum(nums, 9);
        System.out.println(Arrays.toString(ints));
    }


    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

}