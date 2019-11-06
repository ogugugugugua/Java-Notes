package com.company;
import com.company.Hero;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Hero a = new Hero(90,300,"a");
        Hero b = new Hero(20,200,"b");
        Hero c = new Hero(30,250,"c");
        Hero d = new Hero(40,350,"d");

// Method_0
//        while (!b.isDead()){
//            a.attackHero(b);
//        }

// Method_1
//        multiThread thread1 = new multiThread(a,b);
//        thread1.start();
//        multiThread thread2 = new multiThread(b,c);
//        thread2.start();

// Method_2
//        Battle battle1 = new Battle(a,b);
//        Thread thread3 = new Thread(battle1);
//        thread3.start();
//
//        Battle battle2 = new Battle(b,c);
//        Thread thread4 = new Thread(battle2);
//        thread4.start();

// Method_3
//        Thread thread5 = new Thread(){
//            public void run(){
//                while(!a.isDead()&&!b.isDead()){
//                    a.attackHero(b);
//                }
//            }
//        };
//        Thread thread6 = new Thread(){
//            public void run(){
//                while(!c.isDead()&&!d.isDead()){
//                    c.attackHero(d);
//                }
//            }
//        };
//        thread5.start();
//        thread6.start();


        File file = new File("HelloWorld.java");
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        System.out.println(suffix);

    }
}

