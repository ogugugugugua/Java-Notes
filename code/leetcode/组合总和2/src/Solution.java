import java.util.*;

public class Solution {

    public static void main(String[] args) {
        int[] candidates = new int[]{10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        System.out.println(new Solution().combinationSum2(candidates, target));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) return res;
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        int sum = 0;
        dfs(candidates, target, len, sum, 0, path, res);
        return res;
    }

    public void dfs(int[] candidates, int target, int len, int sum, int index, List<Integer> path, List<List<Integer>> res) {
        if (sum == target) {
            List<Integer> temp = new ArrayList<>(path);
            Collections.sort(temp);
            res.add(temp);
            return;
        }

        for (int i = index; i < len; i++) {
            //小剪枝：同一层相同数值的节点，从第2个开始候选数更少，结果一定发生重复，因此用continue跳过当前数
            if (i > index && candidates[i] == candidates[i - 1])  //对于已经排序的有重复数组是可以这样操作的
                continue;
            sum += candidates[i];
            //大剪枝：如果加上当前数就已经大于target，那对于后面的数更加如此，不必继续尝试，直接返回return
            if (sum > target) {
                sum -= candidates[i];
                return;                                     //对于已经排序的升序数组是可以这样操作的
            }
            path.add(candidates[i]);
            dfs(candidates, target, len, sum, i + 1, path, res);  //递归调用
            sum -= candidates[i];                           //回溯
            path.remove(path.size() - 1);               //回溯
        }
    }
}