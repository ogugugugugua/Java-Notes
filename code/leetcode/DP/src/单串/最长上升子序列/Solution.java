package 单串.最长上升子序列;

import java.util.Arrays;

public class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length==0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for(int i = 1; i<nums.length; i++){
            int prevMaxLen = 0;
            for(int j = 0; j<i; j++){
                if(nums[j]<nums[i]) {
                    prevMaxLen = Math.max(prevMaxLen, dp[j]);
                }
            }
            dp[i] = prevMaxLen+1;
        }
        Arrays.sort(dp);
        return dp[dp.length-1];
    }
}
