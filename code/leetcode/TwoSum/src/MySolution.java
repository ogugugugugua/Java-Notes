import java.util.HashMap;
import java.util.Map;

public class MySolution {
    public int[] twoSum(int[] nums, int target) {
        int result_i = 0;
        int result_j = 0;
        for(int i=0; i<nums.length; i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    result_i = i;
                    result_j = j;
                }
            }
        }
        return new int[]{result_i,result_j};
    }

    /**
     * Inspired by official solution
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target){
        Map<Integer, Integer> table = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            table.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int another = target - nums[i];
            if (table.containsKey(another) && table.get(another)!=i){
                return new int[]{i, table.get(another)};
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Inspired by official solution
     * 在添加HashMap过程中进行寻找，就可以只遍历一次数组啦
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum3(int[] nums, int target){
        Map<Integer, Integer> table = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int another = target - nums[i];
            if (table.containsKey(another) && table.get(another)!=i){
                return new int[]{i, table.get(another)};
            }
            table.put(nums[i], i);
        }
        throw new IllegalArgumentException();
    }
}
