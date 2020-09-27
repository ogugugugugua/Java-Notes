import java.util.Arrays;

public class Solution {
    static int[] A = new int[10];
    static int[] B = new int[5];
    public static void main(String[] args) {
        A[0] = 2;
        A[1] = 4;
        A[2] = 6;
        A[3] = 8;
        B[0] = 1;
        B[1] = 3;
        B[2] = 5;

        merge(A, 4, B, 3);
        System.out.println(Arrays.toString(A));
    }
    public static void merge(int A[], int m, int B[], int n) {
        if (n==0) return;
        if (m==0){
            for (int i = 0; i < n; i++) {
                A[i] = B[i];
            }
        }
        int[] res = new int[A.length];
        int indexA = 0;
        int indexB = 0;
        for (int i = 0; i < m + n; i++) {
            if (indexA>=m) {
                res[i] = B[indexB++];
            } else if (indexB>=n) {
                res[i] = A[indexA++];
            } else if (A[indexA] <= B[indexB]) {
                res[i] = A[indexA];
                indexA++;
            } else if (A[indexA] > B[indexB]){
                res[i] = B[indexB];
                indexB++;
            }
        }
        for (int i = 0; i < A.length; i++) {
            A[i] = res[i];
        }
    }
}
