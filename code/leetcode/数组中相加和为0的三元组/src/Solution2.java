import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2 {
    public static void main(String[] args) {
        int[] nums = new int[]{-2,0,1,1,2};
        System.out.println(new Solution2().threeSum(nums));
        System.out.println(new Solution2().twoSum(nums,0));
    }
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) return res;
        Arrays.sort(nums);
        int left = 0, right = len - 1;
        for (int i = 0; i < len; i++) {
            if (i>0 && nums[i]==nums[i-1]) continue;
            left = i + 1;
            right = len - 1;
            while (right > left) {
                if (nums[left] + nums[right] + nums[i] == 0) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i],nums[left],nums[right])));
                    left++;
                    right--;

                    while (right>left && nums[left] == nums[left - 1]) left++;
                    while (right>left && nums[right] == nums[right + 1]) right--;
                } else if (nums[left] + nums[right] + nums[i] < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }

    public List<List<Integer>> twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        List<List<Integer>> res = new ArrayList<>();
        while (right > left) {
            if (nums[right] + nums[left] == target) {
                List<Integer> temp = new ArrayList<>();
                temp.add(nums[left]);
                temp.add(nums[right]);
                res.add(temp);
                right--;
                left++;
                while (nums[right]==nums[right-1]) right--;
                while (nums[left]==nums[left+1]) left++;
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

}
