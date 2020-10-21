package 单串.最大子数组和系列.最大子序和;

public class Solution {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];   //只要包含dp[i-1]的任何子数组和小于0，就会被直接抛弃
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
