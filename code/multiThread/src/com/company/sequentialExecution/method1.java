package com.company.sequentialExecution;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class print extends Thread{
    int num;
    print(int num){
        this.num = num;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(300);
            System.out.println("Thread --> "+this.num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class method1 {
    public static void main(String[] args) {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new print(i));
        }
        System.out.println("方法1：使用单线程的线程池来实现");
        ExecutorService executor = Executors.newSingleThreadExecutor(); //使用单线程的线程池来实现
        for (int i = 0; i < 10; i++) {
            executor.submit(list.get(i));
        }
        executor.shutdown();                                            //记得要关闭，不然的话程序不会停止
    }
}