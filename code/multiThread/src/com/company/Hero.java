package com. company;

public class Hero {
    public float hp;
    public String name;
    public int damage;

    public void attackHero(Hero h){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        h.hp -= this.damage;
        System.out.println("Rest hp: " + h.hp);
        if(isDead(h)){
            System.out.println("Dead!");
        }
    }
    public static boolean isDead(Hero h){
        return h.hp<0;
    }
    public static void main(String args[]){

    }
}
