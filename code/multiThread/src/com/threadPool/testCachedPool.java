package com.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class testCachedPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        System.out.println(executorService);
        for (int i = 0; i < 9; i++) {
            int finalI = i;
            executorService.submit(()->{
                System.out.println(Thread.currentThread().getName()+" "+finalI);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println(executorService);
        executorService.shutdown();
        System.out.println(executorService);
    }
}
