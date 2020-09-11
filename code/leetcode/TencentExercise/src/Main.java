import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static int updatedIndex = 0;
    static int marker = 0;
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String input = sc.nextLine();
        String input = "HG[3|B[2|CAFSHH]]F";
        Main test = new Main();


        System.out.println(test.decode(0, input));

        test.decode2(input);

        System.out.println(test.decode3(input));
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

    /**
     * 这个解法使用到了直接替换
     * @param string
     * @return
     */
    public String decode2(String string){

        while (string.contains("[")){
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

    /**
     * 这个解法和decode2很相似，稍微引入了递归的思想，都是每次解码最内层的括号里面内容
     * @param string
     * @return
     */
    public String decode3(String string) {
        if (string.contains("[")){
            int right = string.indexOf(']');
            int left = string.lastIndexOf('[');
            int spilt = string.lastIndexOf('|');

            int repeatedNum = Integer.parseInt(string.substring(left+1,spilt));
            String content = string.substring(spilt+1,right);
            String newString = String.join("", Collections.nCopies(repeatedNum, content));
            string = string.substring(0, left) + newString + string.substring(right + 1, string.length());
            return decode3(string);
        }
        return string;
    }

    public String decode4(String string){
        String pattern = "\\[(\\d+)\\|(\\w+)\\]";
        Pattern pc = Pattern.compile(pattern);
        Matcher m = pc.matcher(string);
        while (m.find()) {
            int repeatedNum = Integer.valueOf(m.group(1));
            String newString = "";
            for (int i = 0; i < repeatedNum; i++) {
                newString += m.group(2);
            }
            string = m.replaceFirst(newString);
            m = pc.matcher(string);
        }
        return string;
    }
}
