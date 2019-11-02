package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Stream {
    static public void read(File f){
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(f);
            byte[] data = new byte[(int) f.length()];
            fis.read(data);
            for(byte b:data)
                System.out.println(b);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fis!=null){
                try {
                    fis.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }

    static public void read_avanced(File f){
        //把流定义在try()里,try,catch或者finally结束的时候，会自动关闭
        try(FileInputStream fis = new FileInputStream(f)){
            byte[] data = new byte[(int) f.length()];
            fis.read(data);
            for(byte b:data)
                System.out.println(b);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String args[]){
        File f = new File("D:/a/b/c/d.txt");
        try {
            if(!f.getParentFile().exists()){
                f.getParentFile().mkdirs();
            }
            byte[] data = {89,90};
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(data);
            fos.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        read(f);
        System.out.println("----------------");
        read_avanced(f);
    }
}
