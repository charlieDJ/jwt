package com.example.boot.algorithm.myQueue;

import java.util.Stack;

class MyQueue {

    private Stack<Integer> s1 = null;
    private Stack<Integer> s2 = null;

    /** Initialize your data structure here. */
    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        s1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(s2.isEmpty()){
            while (!s1.empty()){
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }

    /** Get the front element. */
    public int peek() {
        if(s2.isEmpty()){
            while (!s1.empty()){
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        if(s2.isEmpty()){
            while (!s1.empty()){
                s2.push(s1.pop());
            }
        }
        return s2.isEmpty();
    }

    public static void main(String[] args) {
    }
}
