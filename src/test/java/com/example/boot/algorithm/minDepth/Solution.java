package com.example.boot.algorithm.minDepth;


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
     * 求最小深度，使用深度优先算法
     *
     * @param root 根节点
     * @return 最小深度
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 深度需要加上当前节点
        // 右子树为空，向左子树中查找
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        // 右子树为空，向左子树中查找
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        // 如果左子树与右子树都不为空，递归查找左右子树的最小高度
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;

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
        System.out.println("solution.maxDepth(node) = " + solution.minDepth(node));

    }

}
