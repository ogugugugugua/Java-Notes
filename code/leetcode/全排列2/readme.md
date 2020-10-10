# [47. 全排列 II](https://leetcode-cn.com/problems/permutations-ii/)

> 难度中等

给定一个可包含重复数字的序列，返回所有不重复的全排列。

**示例:**

```
输入: [1,1,2]
输出:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
```



解法

- [低效率版本](https://github.com/ogugugugugua/Java-Notes/blob/755406fb19f1e0287ab0cdcfdabf0531be976fe5/code/leetcode/%E5%85%A8%E6%8E%92%E5%88%972/src/Solution.java)：使用和[全排列1](https://github.com/ogugugugugua/Java-Notes/tree/fe5d8f27ae9ed189b7095ff058e82ed2dfb2c858/code/leetcode/%E5%85%A8%E6%8E%92%E5%88%97)相同的套路，只是把结果集类型换乘`HashSet`来规避重复数组，这样并没有进行剪枝，所以会有很多无用的重复计算。
- [高效率版本](https://github.com/ogugugugugua/Java-Notes/blob/755406fb19f1e0287ab0cdcfdabf0531be976fe5/code/leetcode/%E5%85%A8%E6%8E%92%E5%88%972/src/Solution2.java)：剪枝。我们寻找的是那些 1.已经被撤销了标记的 2.相同内容的 3.前序节点。因为正是因为其撤销了标记，所以我们下面的搜索中还会使用到它，因此会产生重复，剪掉的就应该是这样的枝:
```java 
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
```

