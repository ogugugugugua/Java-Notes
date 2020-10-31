package com.interviewExercises.ex1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;
//这里使用LockSupport来进行操作，注意需要在t1唤醒t2的时候把自己停住
public class T07_LockSupport {
    List<Integer> list = new ArrayList<>();
    static Thread t1 = null, t2 = null;

    public void add(int num) {
        System.out.println("add");
        this.list.add(num);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        T07_LockSupport c = new T07_LockSupport();
        t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                c.add(i);
                if (c.size() == 5) {
                    LockSupport.unpark(t2); //唤醒t2
                    LockSupport.park();     //停住自己
                }
            }
        });

        t2 = new Thread(() -> {
            LockSupport.park();             //一上来就停住自己
            System.out.println("size 5");
            LockSupport.unpark(t1);         //唤醒t1继续添加
        });

        t1.start();
        t2.start();
    }
}
