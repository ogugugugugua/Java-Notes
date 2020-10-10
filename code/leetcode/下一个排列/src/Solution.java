import java.util.Arrays;

public class Solution {
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if (len==0 || len==1) return;
        if (len == 2) {
            int temp = nums[0];
            nums[0] = nums[1];
            nums[1] = temp;
            return;
        }

        int i = len - 2;
        //目的是要找一个尽可能小的[大数]和前面的[小数]进行交换，所以对于那些降序的就直接跳过
        while (i>=0 && nums[i]>=nums[i+1]) i--;     //遇到相邻升序的才停下来，否则继续往左寻找
        if (i == -1) {              //全逆序
            Arrays.sort(nums);      //按照升序进行一次排序即可
            return;
        }

        Arrays.sort(nums, i + 1, len);      //后半段的最长降序给调整为升序
        int j = i + 1;
        while (nums[j] <= nums[i]) j++;               //寻找那个尽可能小的[大数]
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;

    }
}
