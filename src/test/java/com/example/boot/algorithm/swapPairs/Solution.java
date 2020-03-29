package com.example.boot.algorithm.swapPairs;

class Solution {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode swapPairs(ListNode head) {
        //递归到底
        if (head == null || head.next == null) {
            return head;
        }
        //下面的任务便是交换这3个节点中的前两个节点
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        //根据第二步：返回给上一级的是当前已经完成交换后，即处理好了的链表部分
        return next;
    }


    public static void main(String[] args) {
        final Solution solution = new Solution();
        ListNode listNode = new ListNode(0);
        listNode.next = new ListNode(1);
        listNode.next.next = new ListNode(2);
        listNode.next.next.next = new ListNode(3);
        listNode.next.next.next.next = new ListNode(4);
        ListNode node = solution.swapPairs(listNode);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}