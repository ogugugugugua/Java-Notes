import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int L = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }


        boolean hasFront = false, hasRear = false;
        for (int i = 0; i < n; i++) {
            if (x[i]<=0)
                hasFront = true;
            if (y[i]>=L)
                hasRear = true;
        //////////////////////////////////


        }
        if (!hasFront || !hasRear)
            System.out.println(-1);
    }
}
