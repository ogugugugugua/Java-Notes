package com.company;

import java.util.*;

class object{
    public int number = 10000;
    public synchronized void add(){
        this.number++;
    }
    public synchronized void subtract(){
        this.number--;
    }
}
public class Synchronize {
    public static void main(String args[]){

//        for(int temp = 0;temp<100;temp++){
//            object ob = new object();
//
//            int threads = 100;
//            Thread[] add = new Thread[threads];
//            Thread[] subtract = new Thread[threads];
//
//            List<Thread> addList = new ArrayList<>();
//            List<Thread> subtractList = new ArrayList<>();
//
//            for(int i=0;i<threads;i++){
//                Thread t = new Thread(){
//                    public void run(){
//                        try{
//                            Thread.sleep(100);
//                            ob.add();
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
//                };
//                t.start();
//                addList.add(t);
//            }
//
//            for(int i=0;i<threads;i++){
//                Thread t = new Thread(){
//                    public void run(){
//                        try{
//                            Thread.sleep(100);
//                            ob.subtract();
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
//                };
//                t.start();
//                subtractList.add(t);
//            }
//
//
//            for(Thread t:addList){
//                try{
//                    t.join();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//
//            for(Thread t:subtractList){
//                try{
//                    t.join();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//
//            System.out.println(ob.number);
//
//        }

        List<Hero> list = Collections.synchronizedList(new LinkedList<>());
        list.add(new Hero(90,300,"a"));
        list.add(new Hero(90,300,"a"));
        System.out.println(list.size());
        System.out.println(list.get(list.size()-1));
        list.remove(0);
    }
}

