import java.util.*;

public class Solution3 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Set<List<Integer>> res = new HashSet<>();
        List<Integer> path = new ArrayList<>();
        int sum = 0;
        Arrays.sort(candidates);
        dfs(candidates, target, 0, path, sum, res);
        return new ArrayList<>(res);
    }

    public void dfs(int[] candidates, int target, int index, List<Integer> path, int sum, Set<List<Integer>> res) {
        if (sum == target) {
            List<Integer> temp = new ArrayList<>(path); //复制出一份来
            Collections.sort(temp);                     //要排序
            res.add(temp);                              //排序后放进set中，用于去重
            return;
        }
        for (int i = index; i < candidates.length; i++) {   //剪枝：从index开始，是因为对于每个元素都可以重复选择，所以这个元素的所有可能都已经被探索完，故一旦遍历过这个元素便不必再回头
            sum += candidates[i];
            if (sum>target) {                           //超出target了，就不需要再进行下去了
                sum-=candidates[i];                     //要记得把多加上去的candidates[i]减出来
                break;                             //继续看下一个元素
            }
            path.add(candidates[i]);                    //记录遍历了的那些元素
            dfs(candidates, target, i, path, sum, res);    //递归调用本函数 [Debug:记得这里第三个参数是i而不是index，否则等于没有剪枝]
            sum -= candidates[i];                       //回溯
            path.remove(path.size()-1);           //回溯，从遍历记录中删除最新本元素
        }
    }
}
