package com.example.boot.algorithm.kthLargest;

import java.util.PriorityQueue;

class KthLargest {

    private PriorityQueue<Integer> queue;
    private int k;

    public KthLargest(int k, int[] nums) {
        queue = new PriorityQueue<>(k);
        this.k = k;
        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    public int add(int val) {
        // 维护小顶堆的顺序，而且只能有k个元素，才能在小顶堆找到第k个最大元素
        if (queue.size() < k) {
            queue.add(val);
        } else if (queue.peek() < val) {
            queue.poll();
            queue.add(val);
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        int k = 3;
        int[] arr = {4, 5, 8, 2};
        KthLargest kthLargest = new KthLargest(3, arr);
        kthLargest.add(3);
        kthLargest.add(5);
        kthLargest.add(10);
        kthLargest.add(9);
        System.out.println("kthLargest.add(4) = " + kthLargest.add(4));

    }
}
