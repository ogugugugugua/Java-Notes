package com.polytech;

import hero.Hero;

import java.io.FileReader;
import java.lang.reflect.Field;

public class modifyAttribute {
    public static void main(String[] args) {
        try{
            Hero hero = new Hero();
            System.out.println(hero);
            Field field = hero.getClass().getDeclaredField("name");
            System.out.println(field);
            field.set(hero, "changeName");
            System.out.println(hero);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
