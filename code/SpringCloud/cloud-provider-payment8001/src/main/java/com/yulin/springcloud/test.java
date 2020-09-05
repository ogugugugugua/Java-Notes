package com.yulin.springcloud;
import java.util.Scanner;
public class test {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String result = "";
        if (scan.hasNext()) {
            String str1 = scan.next();
            boolean reach_first = false;
            boolean reach_last = false;
            Character c1 = new Character('(');
            Character c2 = new Character(')');
            Character c3 = new Character('<');
            for(int i =0;i<str1.length();i++){
                if(!reach_first&&c1.equals(str1.charAt(i))){
                    reach_first = true;
                }
                if(c2.equals(str1.charAt(i))){
                    reach_last = true;
                    continue;
                }
                if(!reach_first || reach_last){
                    result += str1.charAt(i);
                }
                if(c3.equals(str1.charAt(i))){
                    result = result.substring(0,result.length()-1);
                }
            }
        }
        scan.close();
        System.out.println(result);
    }
}
