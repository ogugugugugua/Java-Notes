package com.container;

import java.util.concurrent.ConcurrentLinkedQueue;

public class testConcurrentLinkedQueue {
    static private ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 1000; i++) {
            queue.add("ticket "+i);
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while (true) {
                    String poll = queue.poll();
                    if (poll == null) {
                        break;
                    } else {
                        System.out.println("sold " + poll);
                    }
                }
            }).start();
        }
    }
}
