package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MultiThreadStack<T>{
    LinkedList<T> stack = new LinkedList<T>();

    public synchronized void push(T t) {
        while (this.stack.size()==200){
            try{
                this.wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        this.notifyAll();
        this.stack.addLast(t);
    }
    public synchronized T pull(){
        while(this.stack.isEmpty()){
            try{
                this.wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
            this.notify();
            return this.stack.removeLast();
    }
    public synchronized T peek(){
        return this.stack.getLast();
    }
    public int size(){
        return this.stack.size();
    }

    /*main function*/
    public static void main(String args[]){
        MultiThreadStack<Character> stack = new MultiThreadStack<Character>();
        List<Thread> Producers = new ArrayList<>();
        List<Thread> Consumers = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            int finalI = i;
            Thread producer = new Thread(){
                public void run(){
                    while (true){
                        Character temp = (char) (Math.random ()*26+'A');
                        System.out.println("producer" + finalI + " 压入 " + temp + " stack size:" + stack.size());
                        stack.push(temp);
                        //当该容器是线程安全时，外部的线程调用该容器的函数不须关心是否synchronized之类的问题
                        try{
                            Thread.sleep(1000);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            };
            Producers.add(producer);
            producer.start();
        }

        for(int i =0;i<40;i++){
            int finalI = i;
            Thread consumer = new Thread(){
                public void run(){
                    while (true){
                        Character temp = stack.pull();
                        System.out.println("consumer" + finalI + " 弹出 " + temp + " stack size:" + stack.size());
                        try{
                            Thread.sleep(1000);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            };
            Consumers.add(consumer);
            consumer.start();
        }


    }
}
