package com.efimchick.ifmo.collections;

import java.util.*;

public class Main {
    public static void main(String[] args){
        Queue <Integer> que = new MedianQueue();
        que.offer(0);
        que.offer(1);
        que.offer(2);
        System.out.println(que.peek());
    }
}
