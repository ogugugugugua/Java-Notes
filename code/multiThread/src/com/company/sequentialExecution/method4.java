package com.company.sequentialExecution;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class printWithCondition extends Thread{
    int num;
    Lock lock;
    Condition preCondition;
    Condition nextCondition;
    boolean preRun;
    boolean nextRun;

    printWithCondition(int num, Lock lock, Condition preCondition, Condition nextCondition,boolean preRun, boolean nextRun){
        this.num = num;
        this.lock = lock;
        this.preCondition = preCondition;
        this.nextCondition = nextCondition;
        this.preRun = preRun;
        this.nextRun = nextRun;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(300);
            lock.lock();
            if (!preRun)
                preCondition.await();
            System.out.println("Thread --> " + this.num);
            nextRun = true;
            nextCondition.signal();
            lock.unlock();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class method4 {
    static boolean[] run = new boolean[10]; //这里使用static是因为需要在多线程中作为传入参数且需要被修改

    public static void main(String[] args) {

        List<Condition> listCondition = new ArrayList<>();
        Lock lock = new ReentrantLock();
        for (int i = 0; i < 10; i++) {
            listCondition.add(lock.newCondition());
            run[i] = false;
        }
        run[0] = true;

        List<Thread> threadList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            threadList.add(new printWithCondition(i, lock, listCondition.get(i - 1), listCondition.get(i), run[i - 1], run[i]));
        }

        //第一个输出的线程
        Thread thread0 = new printWithCondition(0, lock, null, listCondition.get(0), run[0], run[1]);
        thread0.start();                  //可以开始第一个线程

        for (int i = 0; i < 9; i++) {
            threadList.get(i).start();    //开启threadList里面的所有线程
        }

    }
}
