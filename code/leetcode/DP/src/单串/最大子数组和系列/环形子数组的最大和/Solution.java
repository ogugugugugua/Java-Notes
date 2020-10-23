package 单串.最大子数组和系列.环形子数组的最大和;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[] A = new int[]{-2,-3,-1};
        System.out.println(new Solution().maxSubarraySumCircular(A));
    }
    public int maxSubarraySumCircular(int[] A) {
        int[] dpMax = new int[A.length];
        int[] dpMin = new int[A.length];
        int max = A[0], sum = 0;
        for (int i : A) {
            sum += i;
        }
        dpMax[0] = A[0];
        dpMin[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            dpMax[i] = Math.max(dpMax[i - 1], 0) + A[i];
            dpMin[i] = (i == A.length - 1) ? dpMin[i - 1] : Math.min(dpMin[i - 1], 0) + A[i];
            max = Math.max(Math.max(max, dpMax[i]), sum - dpMin[i]);
        }
        return max;
    }
}
