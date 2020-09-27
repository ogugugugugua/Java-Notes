//屌炸天的解法！！！

public class Solution2 {
    public static void merge(int A[], int m, int B[], int n) {
        int indexA = m-1;
        int indexB = n-1;
        int end = m + n - 1;
        //如果indexA先到0，那么只需要把数组B中剩下的那些依次放进来即可：这就是最下面那个while做的事情
        //如果indexB先到0，那么数组A中剩下的那些本来位置就是对的，不需要动
        while (indexA >= 0 && indexB >= 0) {
            if (A[indexA] > B[indexB]) {
                A[end--] = A[indexA--];
            } else {
                A[end--] = B[indexB--];
            }
        }
        while (indexB >= 0) {
            A[end--] = B[indexB--];
        }
    }
}
