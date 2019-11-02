package com.company;

import java.sql.SQLOutput;
import java.util.ArrayList;

class Hero{
    public String name;
    public float hp;
    public int damage;
    public Hero() {}
    // 增加一个初始化name的构造方法
    public Hero(String name) {
        this.name = name;
    }
    // 重写toString方法
    public String toString() {
        return name;
    }
}
public class Main {
     public static void main(String[] args) {
         ArrayList heros = new ArrayList();
         heros.add(new Hero("a"));
         System.out.println(heros.size());
         heros.add(new Hero("b"));
         System.out.println(heros.size());

         for (int i = 0; i < 5; i++) {
             heros.add(new Hero("hero " + i));
         }
         System.out.println(heros);
         // 在指定位置增加对象
         Hero specialHero = new Hero("special hero");
         heros.add(3, specialHero);
         System.out.println(heros.toString());

         //contains
         System.out.println("contain a: "+heros.contains(new Hero("a")));
         System.out.println("contain special: "+heros.contains(specialHero));

         try{//get
             System.out.println(heros.get(1));
             System.out.println(heros.get(heros.size()+1));
         }catch (Exception e){
             e.printStackTrace();
         }

         try{//indexof
             System.out.println("position: "+heros.indexOf(specialHero));
             System.out.println("position: "+heros.indexOf(new Hero("a")));
         }catch (Exception e){
             e.printStackTrace();
         }

         try{ //remove
             System.out.println(heros);
             heros.remove(2);
             System.out.println(heros);
             heros.remove(specialHero);
             System.out.println(heros);
         }catch (Exception e){
             e.printStackTrace();
         }

         try{
             System.out.println("-------------------");
             System.out.println(heros);
             heros.set(3,specialHero);
             System.out.println(heros);
         }catch (Exception e){
             e.printStackTrace();
         }

         try{//wrong casting
             Hero[] array = (Hero[]) heros.toArray();
//             Hero[] array = (Hero[]) heros.toArray(new Hero[]{});// wrong as well
             System.out.println(array);
         }catch (Exception e){
             e.printStackTrace();
         }

         try{//right casting
             Hero hs[] = (Hero[])heros.toArray(new Hero[]{});
             System.out.println("array: "+hs);
         }catch (Exception e){
             e.printStackTrace();
         }

        try{//addAll
            System.out.println("................");
            ArrayList anotherHeros = new ArrayList();
            for (int i = 0; i < 5; i++) {
                anotherHeros.add(new Hero("hero " + i*10));
            }
            System.out.println(anotherHeros);
            anotherHeros.addAll(4,heros);
            System.out.println(anotherHeros);
        }catch (Exception e){
            e.printStackTrace();
        }

//        try{//clear
//            System.out.println("================");
//            heros.clear();
//            System.out.println(heros);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
         try {

         }
     }
}
