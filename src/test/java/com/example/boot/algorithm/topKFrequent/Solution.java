package com.example.boot.algorithm.topKFrequent;

import java.util.*;

class Solution {

    private class Freq implements Comparable<Freq> {

        int e, freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another) {
            if (this.freq < another.freq) {
                return -1;
            } else if (this.freq > another.freq) {
                return 1;
            }
            return 0;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            final int x = nums[i];
            if (map.containsKey(x)) {
                map.put(x, map.get(x) + 1);
            } else {
                map.put(x, 1);
            }
        }

        PriorityQueue<Freq> pq = new PriorityQueue<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (pq.size() < k) {
                pq.add(new Freq(entry.getKey(), entry.getValue()));
            } else if (entry.getValue() > pq.peek().freq) {
                pq.poll();
                pq.add(new Freq(entry.getKey(), entry.getValue()));
            }
        }
        List<Integer> list = new LinkedList<>();
        while (!pq.isEmpty()) {
            list.add(pq.poll().e);
        }
        return list;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println("solution.topKFrequent = " + solution.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2));
    }


}
