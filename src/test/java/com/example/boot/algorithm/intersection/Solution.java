package com.example.boot.algorithm.intersection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

class Solution {


    public int[] intersection(int[] nums1, int[] nums2) {
        final TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        final ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                list.add(nums2[i]);
                set.remove(nums2[i]);
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
