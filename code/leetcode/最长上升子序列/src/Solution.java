import java.util.Arrays;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) {
        int[] arr = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(new Solution().lengthOfLIS(arr));
    }
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length]; //考虑前i个元素，以第i个数字结尾的最长上升子序列的长度
        dp[0] = 1;
        int maxLen = 1;
        for (int i = 0; i < dp.length; i++) {
            int maxval = 0;             //找到[0,i]区间中最长的子序列长度，而且这个最长子序列的最大值需要小于当前值nums[i]
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, dp[j]);
                }
            }

            dp[i] = maxval + 1;         //前面如果全部大于当前值，那么当前元素结尾的最长子序列长度为1。否则以当前元素结尾的最长子序列长度是之前最大长度+1
            maxLen = Math.max(maxLen, dp[i]);   //更新到maxLen中
        }
        return maxLen;
    }
}
