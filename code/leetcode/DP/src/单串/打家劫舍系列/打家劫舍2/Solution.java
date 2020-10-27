package 单串.打家劫舍系列.打家劫舍2;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{0};
        System.out.println(new Solution().rob(nums));
    }
    public int rob(int[] nums) {
        int len = nums.length;
        if (len==0) return 0;
        if (len==1) return nums[0];
        if (len==2) return Math.max(nums[0],nums[1]);

        int[] dp = new int[len];
        int tempMax;
        //case 1
        dp[0] = 0;
        dp[1] = nums[1];
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        tempMax = dp[len - 1];
        System.out.println("case1: "+ Arrays.toString(dp));

        //case 2
        dp[0] = nums[0];
        dp[1] = dp[0];
        for (int i = 2; i < len-1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        System.out.println("case2: "+ Arrays.toString(dp));
        return Math.max(tempMax, dp[len - 2]);
    }
}
