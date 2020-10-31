package com.interviewExercises;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
//使用两个countdownlatch，分别控制两个线程的执行
public class T06_CountDownLatch {
    List<Integer> list = new ArrayList<>();

    public void add(int num) {
        System.out.println("add");
        this.list.add(num);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        T06_CountDownLatch c = new T06_CountDownLatch();
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);

        new Thread(()->{
                if (c.size()!=5) {
                    try {
                        latch1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("size 5");
                latch2.countDown();
        },"t2").start();

        new Thread(()->{
                for (int i = 0; i < 10; i++) {
                    c.add(i);
                    if (c.size() == 5) {
                        try {
                            latch1.countDown();
                            latch2.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
        },"t1").start();
    }
}
