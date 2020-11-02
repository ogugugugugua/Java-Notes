package com.interviewExercises.ex3;

public class T01_syncWaitNotify {
    static final Object lock = new Object();
    private static boolean numberRun = false;

    public static void main(String[] args) {
        Thread number = new Thread(() -> {
            numberRun = true;
            synchronized (lock) {
                for (int i = 1; i <= 26; i++) {
                    System.out.print(i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();//用于停止锁
            }
        }, "number");

        Thread letter = new Thread(() -> {
            synchronized (lock) {
                if (!numberRun) {
                    try {
                        lock.wait();//保证先打印1再打印A
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 26; i++) {
                    char l = (char) ('A' + i);
                    System.out.print(l);
                    lock.notify();
                    try {
                            lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();//用于停止锁
            }
        }, "letter");

        letter.start();
        number.start();
    }
}
