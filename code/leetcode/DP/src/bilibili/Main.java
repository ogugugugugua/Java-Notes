package bilibili;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] time = new int[n+1][2];     time[0][0] = time[0][1] = 0;
        int[] v = new int[n+1];             v[0] = 0;
        int[] prev = new int[n+1];          prev[0] = 0;
        int[] opt = new int[n+1];           opt[0] = 0;

        for (int i = 1; i <= n; i++) {
            v[i] = scanner.nextInt();
            time[i][0] = scanner.nextInt(); //begin time
            time[i][1] = scanner.nextInt(); //end time
            prev[i] = 0;
            opt[i] = 0;
        }

        //求 prev[i]
        for (int i = n ; i > 0; i--) {
            for (int j = i - 1; j > 0; j--) {
                if (time[i][0]>=time[j][1]){
                    prev[i] = j;
                    break;
                }
            }
        }

        //求OPT[i]
        for (int i = 1; i <= n; i++) {
            opt[i] = Math.max(v[i] + opt[prev[i]], opt[i - 1]);
        }

        //最大值即为OPT[n+1]
        System.out.println(Arrays.toString(opt));
    }
}