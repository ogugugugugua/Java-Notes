import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] w = new int[n];
        int[] visible = new int[n];
        int maxFront;
        int maxRear;
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            visible[i] = 1;
        }
        for (int i = 0; i < n; i++) {               // for all buildings
            maxFront = 0; maxRear = 0;
            // look at rear
            for (int j = i+1; j < n; j++) {
                if(w[j]>maxRear){
                    visible[i]++;
                    maxRear = w[j];
                }
            }
            //look at front
            for (int j = i-1; j >= 0; j--) {
                if (w[j]>maxFront){
                    visible[i]++;
                    maxFront = w[j];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(visible[i]+" ");
        }
    }
}
