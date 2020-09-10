import java.util.Scanner;

public class Main {
    static int updatedIndex = 0;
    static int marker = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        String input = "HG[3|B[2|CA]]F";
        String input = sc.nextLine();
        Main test = new Main();
        String output = test.decode(0, input);
        System.out.println(output);
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
}
