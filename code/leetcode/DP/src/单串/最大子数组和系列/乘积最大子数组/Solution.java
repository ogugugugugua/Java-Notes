package 单串.最大子数组和系列.乘积最大子数组;

public class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length==0) return 0;
        int[] minDP = new int[nums.length];
        int[] maxDP = new int[nums.length];
        minDP[0] = nums[0];
        maxDP[0] = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxDP[i] = Math.max(Math.max(maxDP[i - 1] * nums[i], nums[i]), minDP[i - 1] * nums[i]);
            minDP[i] = Math.min(Math.min(maxDP[i - 1] * nums[i], nums[i]), minDP[i - 1] * nums[i]);
            result = Math.max(maxDP[i], result);
        }
        return result;
    }
}
