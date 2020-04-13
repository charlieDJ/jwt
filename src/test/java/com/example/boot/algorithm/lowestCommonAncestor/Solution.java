package com.example.boot.algorithm.lowestCommonAncestor;

class Solution {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 递归到底有3种情况，第一是递归到叶子节点了或传入的节点就是null，第二和第三是到了指定查找的节点了
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 两个指定节点都不为空，说明他们的父节点就是我们需要找的节点
        if (left != null && right != null)
            return root;
        else
            // 走到这里，说明至少有一个节点为空
            // 如果左节点为空，返回右节点，如果右节点为空，返回左节点。下次递归的时候就可以放弃一边子树了
            return left != null ? left : right;
    }

    /**
     * root = [3,5,1,6,2,0,8,null,null,7,4]
     *
     * @param args
     */
    public static void main(String[] args) {
        final TreeNode node = new TreeNode(3);

        node.left = new TreeNode(5);
        node.left.left = new TreeNode(6);
        node.left.right = new TreeNode(2);
        node.left.right.left = new TreeNode(7);
        node.left.right.right = new TreeNode(4);

        node.right = new TreeNode(1);
        node.right.left = new TreeNode(0);
        node.right.right = new TreeNode(8);

        final Solution solution = new Solution();
        System.out.println("solution.lowestCommonAncestor = " + solution.lowestCommonAncestor(node, node.left, node.right));

    }
}