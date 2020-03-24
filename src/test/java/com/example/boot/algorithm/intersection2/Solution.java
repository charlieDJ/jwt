package com.example.boot.algorithm.intersection2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

class Solution {


    public int[] intersection(int[] nums1, int[] nums2) {
        final TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums1.length; i++) {
            final int i1 = nums1[i];
            if (!map.containsKey(i1)) {
                map.put(i1, 1);
            } else {
                map.put(i1, map.get(i1) + 1);
            }
        }

        final ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            final int i2 = nums2[i];
            if (map.get(i2) != null) {
                list.add(i2);
                map.put(i2, map.get(i2) - 1);
                if (map.get(i2) == 0) {
                    map.remove(i2);
                }
            }
        }


        final int[] ints = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ints[i] = list.get(i);
        }
        return ints;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        final int[] nums1 = {4, 9, 5};
        final int[] nums2 = {9, 4, 9, 8, 4};
        final int[] intersection = solution.intersection(nums1, nums2);
        System.out.println(Arrays.toString(intersection));
    }


}
