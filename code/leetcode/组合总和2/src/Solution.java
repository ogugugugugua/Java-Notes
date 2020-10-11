import java.util.*;

public class Solution {

    public static void main(String[] args) {
        int[] candidates = new int[]{10,1,2,7,6,1,5};
        int target = 8;
        System.out.println(new Solution().combinationSum2(candidates, target));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) return res;
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[len];
        int sum = 0;
        dfs(candidates, target, len, 0, sum, used, path, res);
        return res;
    }

    public void dfs(int[] candidates, int target, int len, int depth, int sum, boolean[] used, List<Integer> path, List<List<Integer>> res) {
        if (depth > len)
            return;
        if (sum == target) {
            List<Integer> temp = new ArrayList<>(path);
            Collections.sort(temp);
            if (!res.contains(temp))
                res.add(temp);
            return;
        }

        mark: for (int i = 0; i < len; i++) {
            if (!used[i]) {
                for (int j = 0; j < i; j++) {
                    if (!used[j] && candidates[i] == candidates[j]) {
                        continue mark;
                    }
                }
                sum += candidates[i];
                if (sum > target) {
                    sum -= candidates[i];
                    break;
                }
                used[i] = true;
                path.add(candidates[i]);
                dfs(candidates, target, len, depth + 1, sum, used, path, res);
                sum -= candidates[i];
                path.remove(path.size() - 1);
                used[i] = false;
            }
        }
    }
}
