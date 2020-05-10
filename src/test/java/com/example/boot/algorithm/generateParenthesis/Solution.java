package com.example.boot.algorithm.generateParenthesis;

import java.util.ArrayList;
import java.util.List;

class Solution {

    /**
     * @param n 多少对括号
     * @return 生成的括号
     */
    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<>();
        return generateOneByOne("", results, n, n);
    }

    /**
     * @param result  符合输出的括号
     * @param results 最后的结果
     * @param left    左括号个数，一开始为 n
     * @param right   右括号个数
     * @return 成对括号
     */
    private List<String> generateOneByOne(String result, List<String> results, int left, int right) {
        //递归到底的情况，左右括号的个数都使用完毕，加入到最后的结果中
        if (left == 0 && right == 0) {
            results.add(result);
            return results;
        }
        // 如果还有左括号，继续增加左括号，并让左括号个数减1
        if (left > 0) {
            generateOneByOne(result + "(", results, left - 1, right);
        }
        // 如果还有右括号，继续增加右括号，并让右括号个数减1。并且右括号必须大于左括号才算合理
        if (right > left) {
            generateOneByOne(result + ")", results, left, right - 1);
        }
        return results;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("solution.generateParenthesis(3) = " + solution.generateParenthesis(3));
    }

}
