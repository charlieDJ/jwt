package com.example.boot.algorithm.maxDepth;


class Solution {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 求最大深度，使用深度优先算法
     *
     * @param root 根节点
     * @return 最大深度
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 把根节点算上
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        final TreeNode node = new TreeNode(3);

        node.left = new TreeNode(5);
        node.left.left = new TreeNode(6);
        node.left.right = new TreeNode(2);
        node.left.right.left = new TreeNode(7);
        node.left.right.right = new TreeNode(4);

        node.right = new TreeNode(1);
        node.right.left = new TreeNode(0);
        node.right.right = new TreeNode(8);
        System.out.println("solution.maxDepth(node) = " + solution.maxDepth(node));

    }

}
