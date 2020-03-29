package com.example.boot.algorithm.reverseList;


class Solution {


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;

        while (head != null) {
            // 暂存头节点的下一个节点
            next = head.next;
            // 此时链表关系为 pre --> head --> next
            // 没变更前，pre 的 下一个指针是 head，为了达到两个指针互换的目的，head 的下一个指针肯定要反过来指向 pre
            head.next = pre;
            // 向后推进，重新进入循环
            pre = head;
            head = next;
        }
        return pre;
    }


    public static void main(String[] args) {
        final Solution solution = new Solution();
        ListNode listNode = new ListNode(0);
        listNode.next = new ListNode(1);
        listNode.next.next = new ListNode(2);
        listNode.next.next.next = new ListNode(3);
        listNode.next.next.next.next = new ListNode(4);
        ListNode node = solution.reverseList(listNode);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

}