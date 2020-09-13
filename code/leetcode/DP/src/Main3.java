import javax.xml.stream.FactoryConfigurationError;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int S = scanner.nextInt();
        int n = scanner.nextInt();
        int[] arr = new int[n];


        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        Main3 test = new Main3();
        System.out.println(test.rec_subset(arr, arr.length-1, S));
        System.out.println(test.subset(arr,  S));
    }

    public boolean rec_subset(int[] arr, int i, int S){
        if (S==0)
            return true;
        else if (i==0)
            return arr[i] == S;
        else if (arr[i]>S)
            return rec_subset(arr, i - 1, S);
        else {
            boolean A = rec_subset(arr, i-1, S-arr[i]);
            boolean B = rec_subset(arr, i-1, S);
            return A||B;
        }
    }

    public boolean subset(int[] arr, int S){
        boolean[][] temp = new boolean[arr.length][S+1];
        for (int i = 0; i < S ; i++) {
            temp[0][i+1] = false;
        }
        for (int i = 0; i < arr.length; i++) {
            temp[i][0] = true;
        }
        temp[0][arr[0]] = true;

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < S+1; j++) {
                if (arr[i]>j)
                    temp[i][j] = temp[i-1][j];
                else {
                    boolean A = temp[i-1][j-arr[i]];
                    boolean B = temp[i-1][j];
                    temp[i][j] = A || B;
                }
            }
        }

        return temp[arr.length-1][S];
    }
}
