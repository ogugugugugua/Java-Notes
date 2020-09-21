package com.consumerAndProducer;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//模拟生产和消费的对象
class buffer{
    Lock lock;
    Condition notFull;
    Condition notEmpty;
    int maxSize;
    List<Date> storage;
    buffer(int size){
        this.lock = new ReentrantLock();
        this.notEmpty = lock.newCondition();
        this.notFull = lock.newCondition();
        this.maxSize = size;
        this.storage = new LinkedList<>();
    }

    public void put(){
        lock.lock();                    //作用相当于synchronized，保证了buffer对象操作的原子性
        try {
            if (storage.size() >= maxSize) {
                System.out.println(Thread.currentThread().getName()+" :wait");
                notFull.await();        //阻塞生产线程
            }
            storage.add(new Date());
            System.out.println(Thread.currentThread().getName() + ": put:" + storage.size());
            Thread.sleep(100);
            notEmpty.signalAll();       //唤醒消费线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void take(){
        lock.lock();                    //作用相当于synchronized，保证了buffer对象操作的原子性
        try{
            if (storage.size()==0){
                System.out.println(Thread.currentThread().getName()+" :wait");
                notEmpty.await();       //阻塞消费线程
            }
            Date date = ((LinkedList<Date>) storage).poll();
            System.out.println(Thread.currentThread().getName() + ": take:" + storage.size());
            Thread.sleep(100);
            notFull.signalAll();        //唤醒生产线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

class producer implements Runnable{
    buffer b;
    producer(buffer buffer){
        this.b = buffer;
    }

    @Override
    public void run() {
        while (true){
            //所有执行了put方法的线程都有机会遇到notFull.await()这一行代码因而被阻塞
            //也就是说这些producer线程都可能会被notFull这个Condition阻塞
            //何时能被唤醒呢？当消费者线程执行了notFull.signalAll();这行代码的时候就可以啦
            this.b.put();
        }
    }
}

class consumer implements Runnable{
    buffer b;
    consumer(buffer buffer){
        this.b = buffer;
    }

    @Override
    public void run() {
        while (true){
            //所有执行了take方法的线程都有可能遇到notEmpty.await()这一行代码因而被阻塞
            //也就是说所有的consumer线程都有可能会被notEmpty这个Condition阻塞
            //何时能被唤醒呢？当生产者线程执行了notEmpty.signalAll();这行代码的时候就可以啦
            this.b.take();
        }
    }
}

public class useLockCondition {
    public static void main(String[] args) {
        buffer buffer = new buffer(5);
        producer producer = new producer(buffer);
        consumer consumer = new consumer(buffer);
        for (int i = 0; i < 5; i++) {
            new Thread(producer,"producer"+i).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(consumer,"consumer"+i).start();
        }
    }
}
