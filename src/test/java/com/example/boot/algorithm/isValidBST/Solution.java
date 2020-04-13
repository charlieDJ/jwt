package com.example.boot.algorithm.isValidBST;


class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private long pre = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (isValidBST(root.left)) {
            // 中序遍历是升序的，前一个节点肯定要大于当前节点，保存前一个节点
            if (pre < root.val) {
                pre = root.val;
                return isValidBST(root.right);
            }
        }
        return false;
    }


}
