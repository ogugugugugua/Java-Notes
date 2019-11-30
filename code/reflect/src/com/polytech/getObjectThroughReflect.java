package com.polytech;

import hero.Hero;
import hero.ADHero;
import hero.APHero;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class getObjectThroughReflect {
    public static void main(String[] args) {
        System.out.println(getObjectThroughReflect.useObject());
//        System.out.println(hero);
    }

    public static List<Hero> useObject(){
        try{
            File file = new File("C:\\java笔记\\code\\reflect\\src\\com\\polytech\\object.config");
            FileReader reader = new FileReader(file);
            char[] content = new char[(int) file.length()];
            reader.read(content);
            String temp = new String(content);
            String[] classnames = temp.split("\r\n");
            List<Hero> heroes = new ArrayList<>();

            Class classType = Class.forName(classnames[0]);
            Constructor c = classType.getConstructor();
            Object o = c.newInstance();
            Field field = classType.getField("name");
            field.set(o,classnames[3]);
            heroes.add((Hero) o);

            classType = Class.forName(classnames[1]);
            c = classType.getConstructor();
            o = c.newInstance();
            field = classType.getField("name");
            field.set(o,classnames[4]);
            heroes.add((Hero) o);

            return heroes;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public static Hero getObject() {
        Hero result = null;
        try {
            File file = new File("C:\\java笔记\\code\\reflect\\src\\com\\polytech\\object.config");
            FileReader reader = new FileReader(file);
            char[] classname = new char[(int) file.length()];
            reader.read(classname);

            String className = new String(classname);
//            System.out.println(className);

            Class classType = Class.forName(className);
            Constructor c = classType.getConstructor();
            result = (Hero) c.newInstance();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
