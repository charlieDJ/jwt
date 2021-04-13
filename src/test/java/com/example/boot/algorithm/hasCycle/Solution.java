package com.example.boot.algorithm.hasCycle;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Solution {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ListNode listNode = (ListNode) o;
            return val == listNode.val;
        }

        @Override
        public int hashCode() {
            return Objects.hash(val);
        }
    }

    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            ListNode next = head.next;
            if (set.contains(head)) {
                return true;
            } else {
                set.add(head);
            }
            head = next;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(0);
        listNode.next = new ListNode(1);
        listNode.next.next = new ListNode(2);
        listNode.next.next.next = new ListNode(3);
        listNode.next.next.next.next = new ListNode(4);
        listNode.next.next.next.next.next = new ListNode(0);
        final Solution solution = new Solution();
        System.out.println(solution.hasCycle(listNode));

    }

}
