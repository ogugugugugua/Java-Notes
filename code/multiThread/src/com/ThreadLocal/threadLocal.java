package com.ThreadLocal;

import java.util.HashSet;
import java.util.Set;

public class threadLocal {
    private final Set<String> set = new HashSet<>();
    private final  int n = 1;
    private final int i = 1;
    public threadLocal(){
        set.add("1");
        set.add("2");
        set.add("3");
        set.add("4");
    }
    public static void main(String[] args) {
        ThreadLocal<String> tl = new ThreadLocal<String>(){
            @Override
            protected String initialValue() {
                return "initial value";
            }
        };
        Thread t1 = new Thread(()->{
            System.out.println(tl.get());
            tl.remove();
        },"t1");
        Thread t2 = new Thread(()->{
            tl.set("t2");
            System.out.println(tl.get());
            tl.remove();
        },"t2");

        t2.start();
        t1.start();

        threadLocal t = new threadLocal();
        t.set.add("6");
         System.out.println(t.set);
    }
}
