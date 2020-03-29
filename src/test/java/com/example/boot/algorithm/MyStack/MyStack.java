package com.example.boot.algorithm.MyStack;

import java.util.LinkedList;
import java.util.Queue;

class MyStack {

    Queue<Integer> q1 = null;
    Queue<Integer> q2 = null;

    /** Initialize your data structure here. */
    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        q1.offer(x);
        while (!q2.isEmpty()){
            q1.offer(q2.poll());
        }

        Queue temp = q1;
        q1 = q2;
        q2 = temp;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return q2.poll();
    }

    /** Get the top element. */
    public int top() {
        return q2.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q2.isEmpty();
    }

    public static void main(String[] args) {
        final MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        System.out.println("stack.pop() = " + stack.pop());
    }
}