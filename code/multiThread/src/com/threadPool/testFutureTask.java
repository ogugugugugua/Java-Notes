package com.threadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class testFutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(()->{
            System.out.println("enter future task");
            Thread.sleep(2000);
            return "test";
        });
        new Thread(task).start();
        System.out.println(task.get());
    }

}
