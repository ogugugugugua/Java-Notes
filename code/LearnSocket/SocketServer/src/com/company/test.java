package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


class A{
    public void bark(){
        System.out.println("bark");
    }
    public void eat(){
        System.out.println("eat");
    }
    static public void useA(){
        System.out.println("useA");
    }
}

class B extends A{
    public void bark(){
        System.out.println("bark b");
    }
    public void eat(){
        System.out.println("eat b");
    }
    public void more(){
        System.out.println("more");
    }
    static public void useA(){
        System.out.println("useA in B");
    }
    public void ex() throws Exception{
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




public class test {

    public static void main(String[] args) {
        method1();
    }
    private static void method1() {
        try {
            method2();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static void method2() throws FileNotFoundException {

        File f = new File("d:/LOL.exe");

        System.out.println("试图打开 d:/LOL.exe");
        new FileInputStream(f);
        System.out.println("成功打开");

    }
}
