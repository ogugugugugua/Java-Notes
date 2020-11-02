package com.container;

import java.util.concurrent.LinkedTransferQueue;

public class testLinkedTransferQueue {
    static LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            try {
                queue.transfer("test");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        System.out.println("execution over.");
    }
}
