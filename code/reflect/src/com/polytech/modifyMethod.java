package com.polytech;

import hero.Hero;

import java.lang.reflect.Method;

public class modifyMethod {
    public static void main(String[] args) {
        Hero hero = new Hero();
        try {
            Method method = hero.getClass().getMethod("setName", String.class);
            System.out.println(hero);
            method.invoke(hero, "test");
            System.out.println(hero);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
