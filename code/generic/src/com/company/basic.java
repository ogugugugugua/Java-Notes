package com.company;

import java.util.ArrayList;
import java.util.List;

public class basic {
    public static void main(String[] args) {
        Integer a = 1;
        Float b = 1.0f;
        Character c = 1;
        Double d = 1.0;
        String e = "string";
        List<Number> set = new ArrayList<>();
        try{
            set.add(a);
            set.add(b);
            set.add(d);
//            set.add(c);
//            set.add(e);
            System.out.println(set);
        }catch (Exception e1){
            e1.printStackTrace();
        }
    }
}
