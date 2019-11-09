package com.company;
class numberObjectInverse{
    public int number=900;
    public synchronized int getNumber(){
        return number;
    }

    public synchronized void add() {
        if(number>=1000){
            try {
                this.wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        number ++;
        this.notify();
    }
    public synchronized void subtract(){
        if(number==1){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number-=5;
        this.notify();
    }
}

public class WaitNotify2 {
    public static void main(String args[]){
        numberObjectInverse number = new numberObjectInverse();
        /*Quick add*/
        Thread t1 = new Thread() {
            public void run() {
                while (true) {
                    number.add();
                    System.out.println("Add: "+number.getNumber());
                    try{
                        Thread.sleep(100);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };

        /*Slow subtract*/
        Thread t2 = new Thread(){
            public void run(){
                while (true){
                    number.subtract();
                    System.out.println("Subtract: "+number.getNumber());
                    try{
                        Thread.sleep(600);
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
