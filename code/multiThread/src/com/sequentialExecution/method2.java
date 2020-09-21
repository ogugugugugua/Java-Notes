package com.sequentialExecution;

import java.util.ArrayList;
import java.util.List;


public class method2 {

    public static void main(String[] args) {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new print(i));
        }
        System.out.println("方法2：在主线程中使用join()");
        list.get(0).start(); //先启动第一个线程，作为开始
        for (int i = 1; i < 10; i++) {
            try {
                list.get(i - 1).join(); //main线程等待前一个print线程执行完毕
                list.get(i).start();    //再开始新的print线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
