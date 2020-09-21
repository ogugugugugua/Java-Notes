package com.company.sequentialExecution;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class printWithCondition extends Thread{
    int num;
    Lock lock;
    /**
     * Condition是一个多线程间协调通信的工具类，使得某个，或者某些线程一起等待某个条件（Condition）,只有当该条件具备( signal 或者 signalAll方法被带调用)时 ，这些等待线程才会被唤醒，从而重新争夺锁。
     */
    Condition condition;        //对当前线程的监视器
    Condition nextCondition;    //下一个被唤醒的线程
    boolean preRun;             //前序线程已完成的标记
    boolean run;                //当前线程已完成的标记

    printWithCondition(int num, Lock lock, Condition condition, Condition nextCondition,boolean preRun, boolean run){
        this.num = num;
        this.lock = lock;
        this.condition = condition;
        this.nextCondition = nextCondition;
        this.preRun = preRun;
        this.run = run;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
            lock.lock();
            if (!preRun)                //若前序线程尚未跑完
                condition.await();      //当前线程先等着，等待被唤醒
            System.out.println("Thread --> " + this.num);
            run = true;                 //标记当前线程已跑完
            nextCondition.signal();     //通过Condition唤醒下一个正在等待中的线程
            lock.unlock();              //最后释放锁
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class method4 {
    static boolean[] run = new boolean[10]; //这里使用static是因为需要在多线程中作为传入参数且需要被修改

    public static void main(String[] args) throws InterruptedException {

        List<Condition> listCondition = new ArrayList<>();
        Lock lock = new ReentrantLock();
        for (int i = 0; i < 10; i++) {
            listCondition.add(lock.newCondition());
            run[i] = false;
        }

        List<Thread> threadList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            threadList.add(new printWithCondition(i, lock, listCondition.get(i - 1), listCondition.get(i), run[i - 1], run[i]));
        }

        //第一个输出的线程
        Thread thread0 = new printWithCondition(0, lock, null, listCondition.get(0), true, run[0]);

        for (int i = 0; i < 9; i++) {
            threadList.get(i).start();    //开启threadList里面的所有线程
        }

        Thread.sleep(10);           //确保第1-9线程都已被开启且处于等待状态，以防有些线程尚未处于等待，这样就会错过前序线程的唤醒通知，从而导致在中间某个线程停下来
        thread0.start();                  //可以开始第0个线程
    }
}
