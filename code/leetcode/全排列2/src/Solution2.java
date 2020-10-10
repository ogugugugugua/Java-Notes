import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 3};
        Solution2 solution = new Solution2();
        List<List<Integer>> lists = solution.permuteUnique(nums);
        System.out.println(lists);
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();    //这里可以用List，因为下面的剪枝操作直接去除了重复项
        if (len==0) return new ArrayList<>(res);
        List<Integer> path = new ArrayList<>();         //临时保存路径
        boolean[] used = new boolean[len];              //标记数组中哪些元素已经被遍历过
        dfs(nums, len, 0, used, path, res);
        return res;
    }

    public void dfs(int[] nums, int len, int depth, boolean[] used, List<Integer> path, List<List<Integer>> res) {
        if (len == depth) {
            res.add(new ArrayList<>(path));             //已经遍历完，添加并返回
            return;
        }
        A: for (int i = 0; i < len; i++) {
            if (!used[i]) {
                //剪枝操作在这：
                for (int j = 0; j < i; j++) {
                    //若前面有兄弟节点和当前节点一样，而且其使用标记还已被撤销，那么当前搜索结果肯定和之前一样，重复，故剪枝
                    if (!used[j] && nums[i] == nums[j]) {
                        continue A;
                    }
                }
                path.add(nums[i]);
                used[i] = true;
                dfs(nums, len, depth + 1, used, path, res);
                path.remove(path.size() - 1);
                used[i] = false;
            }
        }
    }
}
