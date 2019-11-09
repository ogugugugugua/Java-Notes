package com.company;

import java.text.ParseException;

class numberObject{
    public int number=100;
    public synchronized int getNumber(){
        return number;
    }
    public synchronized void add(){
        number+=3;
        this.notify();
    }
    public synchronized void subtract(){
        if(number<=20){
            try{
                this.wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        number--;
    }
}
public class WaitNotify {
    public static void main(String args[]){
        numberObject number = new numberObject();

        /*subtract*/
        Thread t1 = new Thread(){
            public void run(){
                while (true){
                    number.subtract();
                    System.out.println("subtract "+number.getNumber());
                    try{
                        Thread.sleep(100);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };

        /*add*/
        Thread t2 = new Thread(){
            public void run(){
                while (true){
                    number.add();
                    System.out.println("add "+number.getNumber());
                    //this.notify(); //This should be a method of Object, shouldn't be here.
                    try{
                        Thread.sleep(2000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };

        t1.start();
        t2.start();
    }
}
