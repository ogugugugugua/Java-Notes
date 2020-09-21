package com.previousCourse;

public class Hero {
    int damage;
    float hp;
    String name;

    public Hero() {

    }

    public void attackHero(Hero h){
        if(!h.isDead()&&!this.isDead()){
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            h.hp -= this.damage;
            System.out.format("%s 正在攻击 %s, %s的血变成了 %.0f%n",name,h.name,h.name,h.hp);
        }
        if(h.isDead()){
            System.out.println("Hero "+h.name+" is dead.");
        }
    }
    public Hero(int damage, float hp, String name){
        this.name = name;
        this.hp = hp;
        this.damage = damage;
    }
    public boolean isDead(){
        return hp<0;
    }
}
