package com.company;

import org.w3c.dom.ls.LSOutput;

import javax.crypto.spec.PSource;

class hero{
    class heroDeadException extends Exception{
        public heroDeadException(){}
        public heroDeadException(String msg){
            super(msg);
        }
    }

    String name;
    int hp;
    public hero(String name, int hp){
        this.hp = hp;
        this.name = name;
    }
    public void attack(hero another) throws heroDeadException{
        if(another.hp<=0){
            throw new heroDeadException(another.name+" is dead, no more attack");
        }
        another.hp-=1;
        System.out.println(another.name+" rested hp:"+another.hp);
    }
}
public class Main {

    public static void main(String[] args) {
        hero a = new hero("a",99);
        hero b = new hero("b",2);
        try{
            while(true) {
                b.attack(a);
                a.attack(b);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
