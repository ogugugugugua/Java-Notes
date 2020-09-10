import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int updatedIndex = 0;
    static int marker = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = "HG[3|B[2|CAFSHH]]F";
//        String input = sc.nextLine();
        Main test = new Main();
//        String output = test.decode(0, input);
//        System.out.println(output);
        test.decode2(input);
    }

    public String decode(int startIndex, String string) {
        String temp = "";
        String middle = "";
        int multiple = 0;
        for (int i = startIndex; i < string.length();) {
            if (string.charAt(i) == '[') {
                marker++;
                int k = i;
                while (string.charAt(k++)!='|');
                multiple = Integer.parseInt(string.substring(i+1,k-1));
                middle = decode(k, string);
//                System.out.println("enter: multiple: "+multiple+" marker: "+marker+" middle: "+middle);
                for (int j = 0; j < multiple; j++) {
                    temp = temp.concat(middle);
                }
                i = updatedIndex+1;
            }
            if (string.charAt(i) == ']') {
                marker--;
                updatedIndex = i;
//                System.out.println("enter2: marker: " + marker);
                if (marker != 0){
                    break;
                }
            }else{
                temp += string.charAt(i++);
            }
        }
        return temp;
    }

    public String decode2(String string){

        while (string.contains("]")){
            int right = string.indexOf(']');
            int left = string.lastIndexOf('[');
            int spilt = string.lastIndexOf('|');

            int repeatedNum = Integer.parseInt(string.substring(left + 1, spilt));
            String newString = String.join("",Collections.nCopies(repeatedNum, string.substring(spilt + 1, right)));
            String oldString = string.substring(left, right + 1);
            string = string.replace(oldString, newString);
        }

        System.out.println(string);
        return string;
    }

//    public String decode3(String string) {
//        int left = 0, right = 0, split = 0;
//        for (int i = 0; i < string.length(); i++) {
//            if (string.charAt(i) == '[')
//                left = i;
//
//        }
//    }

}
