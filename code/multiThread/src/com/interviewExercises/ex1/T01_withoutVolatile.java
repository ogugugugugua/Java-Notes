package com.interviewExercises.ex1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T01_withoutVolatile {
    List<Integer> list = new ArrayList<>();
    public void add(int num) {
        System.out.println("add");
        list.add(num);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        T01_withoutVolatile c = new T01_withoutVolatile();
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
