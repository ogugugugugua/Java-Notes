package com.company;

// Use this file alongside with TestSearchJavaFile.java

import java.io.File;
import java.io.IOException;
import java.io.FileReader;
public class Search_java_file extends Thread{
    private File javaFile;
    private String targetString;
    public Search_java_file(File javaFile,String targetString){
        this.javaFile = javaFile;
        this.targetString = targetString;
    }
    public void run(){
        try (FileReader fr = new FileReader(javaFile)) {
            char[] data = new char[(int) javaFile.length()];
            fr.read(data);
//            for(char b:data)
//                System.out.println(b);//print all the codes in the file
            if(new String(data).contains(targetString)){
                System.out.println(javaFile.getName()+" contains target String: "+targetString);
            }else {
                System.out.println(javaFile.getName()+" does not contain target String: "+targetString);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
