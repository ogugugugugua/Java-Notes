package com.interviewExercises.ex3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T03_LockCondition {
    static Lock lock = new ReentrantLock();
    static Condition number = lock.newCondition();
    static Condition letter = lock.newCondition();

    public static void main(String[] args) {
        Thread threadNumber = new Thread(()->{
            lock.lock();
            for (int i = 1; i <= 26; i++) {
                System.out.print(i);
                try {
                    letter.signal();
                    number.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            letter.signal();
            lock.unlock();
        },"number");

        Thread threadLetter = new Thread(()->{
            lock.lock();
            for (int i = 0; i < 26; i++) {
                System.out.print((char) ('A' + i));
                try {
                    number.signal();
                    letter.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            number.signal();
            lock.unlock();
        },"letter");

        threadNumber.start();
        threadLetter.start();
    }
}
