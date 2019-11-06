package com.company;

import java.io.File;

// Use this file alongside with Search_java_file.java
// This is the main function specific for testing the file described aboved

public class TestSearchJavaFile {
    public static void find(File file){
        if(file.isDirectory()){
            File[] temp = file.listFiles();
            if(temp!=null)
                for(File f:temp)
                    find(f);
        }
        if(file.isFile()){
            String name = file.getName();
            if(name.substring(name.lastIndexOf(".")+1).equals("java")){
                System.out.println(name);
                Thread thread = new Search_java_file(file,"import");
                thread.start();
            }
        }
    }

    public static void main(String args[]){
        File f = new File("C:\\Users\\11437\\javaNotes");
        find(f);
    }
}
