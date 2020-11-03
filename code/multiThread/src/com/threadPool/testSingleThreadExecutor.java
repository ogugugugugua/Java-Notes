package com.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class testExecutors {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 9; i++) {
            int finalI = i;
            executor.submit(()->{
                System.out.println(Thread.currentThread().getName()+" "+finalI);
            });
        }
        executor.shutdown();
    }
}
