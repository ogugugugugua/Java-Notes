package com.interviewExercises;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
//可以用，但是有很奇怪的问题
public class T02_withVolatile {
    volatile List<Integer> list = new ArrayList<>();
    public void add(int num) {
        System.out.println("add");
        list.add(num);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        T02_withVolatile c = new T02_withVolatile();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                c.add(i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"t1").start();
        new Thread(()->{
            while (true) {
                if (c.size()==5)
                    break;
            }
            System.out.println("size 5");
        }, "t2").start();
    }
}
