package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static void filter(List<Hero> heros){
        for(Hero h:heros){
            if(h.damage<50 && h.hp>100){
                System.out.println(h);
            }
        }
    }
    private static void filter_anonyme(List<Hero>heros, HeroChecker heroChecker){
        for(Hero h : heros){
            if(heroChecker.herochecker(h)){
                System.out.println(h);
            }
        }
    }
    public static void main(String[] args) {
        List<Hero> heros = new ArrayList<>();
        Random r = new Random();
        for(int i = 0;i<100;i++){
            heros.add(new Hero("h"+i,r.nextInt(1000),r.nextInt(100)));
        }
//        filter(heros);

        //====================================================

        System.out.println("-------------------------------");
        HeroChecker heroChecker = new HeroChecker() {
            @Override
            public boolean herochecker(Hero h) {
                return h.damage < 50 && h.hp > 100;
            }
        };
//        filter_anonyme(heros,heroChecker);

        filter_anonyme(heros , h->h.damage<50 && h.hp>100);
    }
}
