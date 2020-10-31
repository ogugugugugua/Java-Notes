package com.interviewExercises.ex2;

import java.util.LinkedList;
import java.util.List;

public class T00_synchronizedWait {
    List<Integer> list = new LinkedList<>();
    static final int size = 10;

    public synchronized void put(int num) throws InterruptedException {
        while (list.size() >= size) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("put "+num);
        list.add(num);
        this.notifyAll();
    }

    public synchronized int get() throws InterruptedException {
        while (list.size() <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        System.out.println("get "+list.get(0));
        return list.remove(0);
    }
    public static void main(String[] args) {
        T00_synchronizedWait c = new T00_synchronizedWait();
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                try {
                        c.put(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                try {
                        c.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(r2).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(r1).start();
        }

    }
}
