package com.sequentialExecution;

import java.util.ArrayList;
import java.util.List;

class printWithJoin extends Thread{
    int num;
    Thread previous;
    printWithJoin(int num, Thread previous){
        this.num = num;
        this.previous = previous;
    }

    @Override
    public void run() {
        try {
            if (previous!=null){
                previous.join();
            }
            Thread.sleep(300);
            System.out.println("Thread --> "+this.num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
public class method3 {
    public static void main(String[] args) {
        List<Thread> list = new ArrayList<>();
        list.add(new printWithJoin(0,null));
        for (int i = 1; i < 10; i++) {
            list.add(new printWithJoin(i,list.get(i-1)));
        }
        System.out.println("方法3：在线程中使用join方法");
        for (int i = 0; i < 10; i++) {
            list.get(i).start();
        }
    }
}
