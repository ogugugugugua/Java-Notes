package 单串.最经典单串LIS系列.最长递增子序列的个数;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,4,7};
        System.out.println(new Solution().findNumberOfLIS(nums));
    }
    public int findNumberOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int[] times = new int[nums.length];
        int maxLen = 0, result = 0;
        Arrays.fill(times,1);
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[j]<nums[i]){
                    if (dp[j] >= dp[i]) {
                        dp[i] = dp[j] + 1;              //当前可能的最长子序列的长度
                        times[i] = times[j];            //对应该长度的子序列出现次数
                    } else if (dp[j] + 1 == dp[i]) {
                        times[i] += times[j];           //对应该长度的子序列又出现了，次数加上去
                    }
                }
            }
        }
        System.out.println("Times: " + Arrays.toString(times));
        System.out.println("dp:    "+Arrays.toString(dp));
        for (int i = 0; i < dp.length; i++) {
            maxLen = Math.max(maxLen, dp[i]);
        }
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == maxLen) {
                result+=times[i];
            }
        }
        return result;
    }
}
