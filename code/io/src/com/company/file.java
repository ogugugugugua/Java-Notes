package com.company;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Date;
public class file {

    private static long maxValue1 = Integer.MIN_VALUE;
    private static File maxName1 = null;
    private static long minValue1 = Integer.MAX_VALUE;
    private static File minName1 = null;

    public static void find(File file){
            if(file.isDirectory()){
                File[] temp = file.listFiles();
                if(temp!=null)
                for(File f:temp)
                    find(f);
            }

            if(file.isFile()){
                if(file.length()>maxValue1 && file.length()!=0)
                {
                    maxValue1 = file.length();
                    maxName1 = file;
                }
                if(file.length()<minValue1 && file.length()!=0)
                {
                    minValue1 = file.length();
                    minName1 = file;
                }
            }

    }
    static long minSize = Integer.MAX_VALUE;
    static long maxSize = 0;
    static File minFile = null;
    static File maxFile = null;
    public static void listFiles(File file){
        if(file.isFile()){
            if(file.length()>maxSize){
                maxSize = file.length();
                maxFile = file;
            }
            if(file.length()!=0 && file.length()<minSize){
                minSize = file.length();
                minFile = file;
            }
            return;
        }

        if(file.isDirectory()){
            File[] fs = file.listFiles();
            if(null!=fs)
                for (File f : fs) {
                    listFiles(f);
                }
        }
    }
    public static void main(String[] args) throws IOException {
        File test1 = new File("c:\\WINDOWS\\"); //ct.sym
        System.out.println("path: " + test1.getAbsolutePath());
        System.out.println("existence: " + test1.exists());
        System.out.println("directory: " + test1.isDirectory());
        System.out.println("file: " + test1.isFile());
        System.out.println("length: " + test1.length());
        System.out.println("last modify time: " + new Date((long)test1.lastModified()));
        //test1.renameTo(new File("texe.exe"));
        System.out.println("path: " + test1.getAbsolutePath());
        System.out.println("father: " + test1.getParentFile());

//        System.out.println(Arrays.toString(test1.list()));
//        File[]fs= test1.listFiles();
//        long maxValue = Integer.MIN_VALUE;
//        File maxName = null;
//        long minValue = Integer.MAX_VALUE;
//        File minName = null;
//        for(File f: fs){
//            if(f.length()>maxValue && f.length()!=0)
//            {
//                maxValue = f.length();
//                maxName = f;
//            }
//            if(f.length()<minValue && f.length()!=0)
//            {
//                minValue = f.length();
//                minName = f;
//            }
//        }
//        assert maxName != null;
//        System.out.println("Max: " + maxName.getAbsolutePath() + " " + maxValue);
//        assert minName != null;
//        System.out.println("Min: " + minName.getAbsolutePath() + " " + minValue);

//        find(test1);
//        System.out.println("Max: " + maxName1.getAbsolutePath() + " " + maxValue1);
//        System.out.println("Min: " + minName1.getAbsolutePath() + " " + minValue1);


//        listFiles(test1);
        System.out.printf("最大的文件是%s，其大小是%,d字节%n",maxFile.getAbsoluteFile(),maxFile.length());
        System.out.printf("最小的文件是%s，其大小是%,d字节%n",minFile.getAbsoluteFile(),minFile.length());
    }
}
