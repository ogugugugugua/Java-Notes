package com.interviewExercises;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
//真正可以用的代码，而且原理和理解上都没有问题
public class T05_NotifyFreeLock {
    List<Integer> list = new ArrayList<>();
    public void add(int num) {
        System.out.println("add");
        this.list.add(num);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        T05_NotifyFreeLock c = new T05_NotifyFreeLock();
        Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                if (c.size() != 5) {
                    try {
                        lock.wait();            //先启动，如果还不等于5的时候就休息着
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("size 5");
                lock.notify();                  //唤醒t1线程继续执行
            }
        }, "t2").start();

        new Thread(()->{
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    c.add(i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (c.size() == 5) {
                        lock.notify();          //唤醒t2线程
                        try {
                            lock.wait();        //必须让出锁给t2线程输出结果
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        },"t1").start();
    }
}
