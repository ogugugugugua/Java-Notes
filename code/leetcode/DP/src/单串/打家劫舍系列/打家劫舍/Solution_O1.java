package 单串.打家劫舍系列.打家劫舍;

// 进行了空间的优化，这个解法的空间复杂度是O(1)
public class Solution_O1 {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[0];
        if (len == 2) return Math.max(nums[0],nums[1]);

        int prev = nums[0], curr = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int temp = Math.max(prev + nums[i], curr);
            prev = curr;
            curr = temp;
        }
        return curr;
    }
}
