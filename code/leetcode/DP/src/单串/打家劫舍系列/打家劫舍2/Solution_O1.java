package 单串.打家劫舍系列.打家劫舍2;

import java.util.Arrays;

//空间复杂度为O(1)
public class Solution_O1 {
    public int robMax(int[] nums) {
        int prev = 0, cur = 0, temp;
        for (int num : nums) {
            temp = cur;
            cur = Math.max(cur, prev + num);
            prev = temp;
        }
        return cur;
    }

    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[0];
        return Math.max(robMax(Arrays.copyOfRange(nums, 0, len-1)),
                        robMax(Arrays.copyOfRange(nums, 1, len)));
    }
}
