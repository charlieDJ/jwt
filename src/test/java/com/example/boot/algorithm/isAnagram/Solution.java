package com.example.boot.algorithm.isAnagram;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean isAnagram(String s, String t) {

        Map<Character, Integer> map1 = new HashMap<>();

        Map<Character, Integer> map2 = new HashMap<>();

        final char[] charsOne = s.toCharArray();
        final char[] charsTwo = t.toCharArray();
        for (char c : charsOne) {
            if (!map1.containsKey(c)) {
                map1.put(c, 1);
            } else {
                map1.put(c, map1.get(c) + 1);
            }
        }
        for (char c : charsTwo) {
            if (!map2.containsKey(c)) {
                map2.put(c, 1);
            } else {
                map2.put(c, map2.get(c) + 1);
            }
        }
        return map1.equals(map2);
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println("solution.isAnagram(\"anagram\",\"nagaram\") = " + solution.isAnagram("anagram", "nagaram"));
        System.out.println("solution.isAnagram(\"rat\",\"car\") = " + solution.isAnagram("rat", "car"));
    }
}
