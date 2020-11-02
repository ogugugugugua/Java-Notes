package com.threadPool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class testCompletableFuture {
    //这里模拟的是使用三个线程去三个网站拉取数据，全部完成之后继续主线程
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Long start = System.currentTimeMillis();

        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(() -> priceTM());
        CompletableFuture<Double> futureWP = CompletableFuture.supplyAsync(() -> priceWP());
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(() -> priceJD());
        CompletableFuture.allOf(futureJD, futureWP, futureTM).join();       //上述3个任务都执行完毕后，join到主进程里
//        CompletableFuture.anyOf(futureJD, futureWP, futureTM).join();       //上述3个任务任意一个执行完毕后，join到主进程里

        Long end = System.currentTimeMillis();
        System.out.println("execution in " + (end - start));
        System.out.println(futureJD.get() + futureTM.get() + futureWP.get());
    }

    private static Double priceTM() {           //天猫价格
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1.0;
    }
    private static Double priceWP() {           //唯品会价格
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1.0;
    }
    private static Double priceJD() {           //京东价格
        try {
            Thread.sleep(1300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1.0;
    }

}
