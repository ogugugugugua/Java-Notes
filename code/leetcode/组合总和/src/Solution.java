import java.util.*;

public class Solution {

    public static void main(String[] args) {
        int[] candidates = new int[]{2,7,6,3,5,1,9};
        int target = 9;
        System.out.println(new Solution().combinationSum(candidates, target));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Set<List<Integer>> res = new HashSet<>();
        List<Integer> path = new ArrayList<>();
        int sum = 0;
        dfs(candidates, target, path, sum, res);
        return new ArrayList<>(res);
    }

    public void dfs(int[] candidates, int target, List<Integer> path, int sum, Set<List<Integer>> res) {
        if (sum == target) {
            List<Integer> temp = new ArrayList<>(path); //复制出一份来
            Collections.sort(temp);                     //要排序
            res.add(temp);                              //排序后放进set中，用于去重
            return;
        }
        for (int i = 0; i < candidates.length; i++) {
            sum += candidates[i];
            if (sum>target) {                           //超出target了，就不需要再进行下去了
                sum-=candidates[i];                     //要记得把多加上去的candidates[i]减出来
                continue;                               //继续看下一个元素
            }
            path.add(candidates[i]);                    //记录遍历了的那些元素
            dfs(candidates, target, path, sum, res);    //递归调用本函数
            sum -= candidates[i];                       //回溯
            path.remove(path.size()-1);           //回溯，从遍历记录中删除最新本元素
        }
    }

}
