package com.previousCourse;

public class Methods {
}

//Method_1_extends_Thread
class multiThread extends Thread{
    private Hero h1;
    private Hero h2;
    public multiThread(Hero h1, Hero h2){
        this.h1 = h1;
        this.h2 = h2;
    }
    @Override
    public void run() {
        while(!h2.isDead()&&!h1.isDead()){
            h1.attackHero(h2);
        }
    }
}

//Method_2_implements_Runnable
class Battle implements Runnable{
    private Hero h1;
    private Hero h2;
    public Battle(Hero h1, Hero h2){
        this.h1 = h1;
        this.h2 = h2;
    }
    @Override
    public void run() {
        while(!h2.isDead()&&!h1.isDead()){
            h1.attackHero(h2);
        }
    }
}