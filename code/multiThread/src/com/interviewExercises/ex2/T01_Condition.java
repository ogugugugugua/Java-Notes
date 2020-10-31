package com.interviewExercises.ex2;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class T01_Condition {
    List<Integer> list = new LinkedList<>();
    static final int size = 10;
    ReentrantLock lock = new ReentrantLock();
    Condition producer = lock.newCondition();
    Condition consumer = lock.newCondition();


    public void put(int num) throws InterruptedException {
        try {
            lock.lock();                    //先锁上
            while (list.size() == size) {
                producer.await();           //到最大容量限制了，把当前线程归类到producer这个队列中并等待
            }
            System.out.println("put "+num);
            list.add(num);                  //添加
            consumer.signalAll();           //只唤醒consumer类型的线程队列中在等待的线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();                  //释放锁
        }
    }

    public synchronized int get() throws InterruptedException {
        try {
            lock.lock();                    //先锁上
            while (list.size() == 0) {
                consumer.await();           //容器空了，把当前线程归类到consumer这个队列中并等待
            }
            System.out.println("get " + list.get(0));
            producer.signalAll();           //只唤醒producer类型的线程队列中在等待的线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();                  //释放锁
        }
        return list.remove(0);       //返回值
    }

    public static void main(String[] args) {
        T01_Condition c = new T01_Condition();
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
