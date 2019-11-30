package com.polytech;

import hero.Hero;

import java.lang.reflect.Constructor;

public class Main {

    public static void main(String[] args) {
        String className = "hero.Hero";
        try{
//            Class class1 = new Hero().getClass();
//            System.out.println(class1);
//            Class class2 = Hero.class;
//            System.out.println(class2);
//            Class class3 = Class.forName(className);
//            System.out.println(class3);
            Class class4 = Class.forName(className);
            Constructor c = class4.getConstructor();
            Hero hero = (Hero) c.newInstance();
            System.out.println(hero);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
