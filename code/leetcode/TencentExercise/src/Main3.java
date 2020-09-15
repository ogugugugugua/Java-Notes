import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] gym = new int[n];
        int[] work = new int[n];
        boolean worked =false;
        boolean gymed = false;
        int busy = 0;

        for (int i = 0; i < n; i++) {
            work[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            gym[i] = sc.nextInt();
        }
        /////////////////////////////////////////
        for (int i = 0; i < n; i++) {
            if (work[i] == 1 && gym[i] == 0 && !worked) {
                worked = true;
                gymed = false;
                busy++;
            } else {
                if (work[i] == 0 && gym[i] == 1 && !gymed) {
                    gymed = true;
                    worked = false;
                    busy++;
                } else {
                    if (work[i] == 0 && gym[i] == 0) {
                        gymed = false;
                        worked = false;
                        System.out.println("free");
                    } else {
                        if (work[i] == 1 && gym[i] == 1 && !gymed && worked) {
                            gymed = true;
                            worked = false;
                            busy++;

                        if (work[i] == 1 && gym[i] == 1 && !worked && gymed) {
                            worked = true;
                            gymed = false;
                            busy++;
                        }

                        }
                    }
                }
            }
        }
        System.out.println(n-busy);
    }

//    public static int minDay(int[] work, int[] gym){
//        int n = work.length;
//        int[][] dp = new int[n][3];
//        dp[0][0] = (work[0] == 0 && gym[0] == 0) ? 1 : 0;   //0: 休息
//    }
}

