package 单串.最长上升子序列;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,4,7};
        System.out.println(new Solution().lengthOfLIS(nums));
    }
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
        System.out.println(Arrays.toString(dp));
        Arrays.sort(dp);
        return dp[dp.length-1];
    }
}
