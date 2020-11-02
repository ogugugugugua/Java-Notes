package com.threadPool;

import java.util.concurrent.*;

public class testCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("enter callable");
                Thread.sleep(1000);
                return "test";
            }
        };

        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(callable);//异步的
        System.out.println(future.get());//阻塞的
        service.shutdown();
    }
}
