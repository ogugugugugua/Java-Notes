package com.container;

import java.util.concurrent.SynchronousQueue;

public class testSynchronousQueue {
    static SynchronousQueue<String> queue = new SynchronousQueue<>();

    public static void main(String[] args) throws InterruptedException {
        Thread threadTake = new Thread(() -> {
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadTake.start();     //若注释掉则程序无法正常结束，因为下面的put阻塞了
        queue.put("test");
        System.out.println("execution over");

    }
}
