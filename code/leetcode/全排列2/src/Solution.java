import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        int[] nums = {1, 1, 3};
        Solution solution = new Solution();
        List<List<Integer>> lists = solution.permuteUnique(nums);
        System.out.println(lists);
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        Set<List<Integer>> res = new HashSet<>();
        if (len==0) return new ArrayList<>(res);
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[len];
        dfs(nums, len, 0, used, path, res);
        return new ArrayList<>(res);
    }

    public void dfs(int[] nums, int len, int depth, boolean[] used, List<Integer> path, Set<List<Integer>> res) {
        if (len == depth) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;
                dfs(nums, len, depth + 1, used, path, res);
                path.remove(path.size() - 1);
                used[i] = false;
            }
        }
    }
}
