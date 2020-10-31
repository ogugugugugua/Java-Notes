package com.interviewExercises;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T04_NotifyHoldingLock {
    List<Integer> list = new ArrayList<>();
    public void add(int num) {
        System.out.println("add");
        this.list.add(num);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        T04_NotifyHoldingLock c = new T04_NotifyHoldingLock();
        Object lock = new Object();
        new Thread(() -> {
            synchronized (lock){
                if (c.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("size 5");
            }
        }, "t2").start();

        new Thread(()-> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    c.add(i);
                    if (c.size() == 5) {
                        lock.notify();  //这里不会释放锁，导致了t2线程无法获取锁，因此不能及时在size==5的时候就输出结果
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();
    }
}
