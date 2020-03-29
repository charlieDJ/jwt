package com.example.boot.algorithm.parenthesis;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class Solution {

    public boolean isValid(String s) {
        Map<String, String> map = new HashMap<>();
        Stack<String> stack = new Stack<>();
        map.put(")", "(");
        map.put("}", "{");
        map.put("]", "[");
        for (int i = 0; i < s.length(); i++) {
            // 假设是左括号，那么它肯定需要加入stack，否则就不加入
            final String param = String.valueOf(s.charAt(i));
            if (!map.containsKey(param)) {
                stack.push(param);
            } else if (stack.empty() || !map.get(param).equals(stack.pop())) {
                // 第一个判断，不能让右括号先进来，第二个判断，是看栈顶的元素与map中对应元素是否一致
                return false;
            }
        }
        return stack.empty();
    }


    public static void main(String[] args) {
        final Solution solution = new Solution();
        final boolean valid = solution.isValid("]");
        System.out.println(valid);
    }

}
