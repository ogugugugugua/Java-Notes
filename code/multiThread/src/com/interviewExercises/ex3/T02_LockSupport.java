package com.interviewExercises.ex3;

import java.util.concurrent.locks.LockSupport;

public class T02_LockSupport {
    static Thread number = null , letter = null;
    public static void main(String[] args) {
        number = new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                System.out.print(i);
                LockSupport.unpark(letter);
                LockSupport.park();
            }
            LockSupport.unpark(letter);//用于停止锁
        }, "number");

        letter = new Thread(()->{
            LockSupport.park(); //上来先停住，因为要先输出1再输出A
            for (int i = 0; i < 26; i++) {
                char l = (char) ('A' + i);
                System.out.print(l);
                LockSupport.unpark(number);
                LockSupport.park();
            }
            LockSupport.unpark(number);//用于停止锁
        },"letter");

        letter.start();
        number.start();
    }
}
