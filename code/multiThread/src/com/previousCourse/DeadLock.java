package com.previousCourse;


class testObject{
    String name;
    public testObject(String name){
        this.name = name;
    }
}
public class DeadLock {
    public static void main(String args[]){
        final testObject a = new testObject("a");
        final testObject b = new testObject("b");
        final testObject c = new testObject("c");

        Thread t1 = new Thread(){
            public void run(){
                System.out.println("t1 enter");
                try{
                    synchronized (a){
                        System.out.println("t1 take a");
                        Thread.sleep(1000);
                        synchronized (b){
                            System.out.println("t1 take b");
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        t1.start();

        Thread t2 = new Thread(){
            public void run(){
                System.out.println("t2 enter");
                try{
                    synchronized (b){
                        System.out.println("t2 take b");
                        Thread.sleep(1000);
                        synchronized (c){
                            System.out.println("t2 take c");
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        t2.start();

        Thread t3 = new Thread(){
            public void run(){
                System.out.println("t3 enter");
                try{
                    synchronized (c){
                        System.out.println("t3 take c");
                        Thread.sleep(1000);
                        synchronized (a){
                            System.out.println("t3 take a");
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        t3.start();

    }
}
