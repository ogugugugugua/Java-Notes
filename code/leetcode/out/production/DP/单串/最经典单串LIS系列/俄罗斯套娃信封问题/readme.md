# [354. 俄罗斯套娃信封问题](https://leetcode-cn.com/problems/russian-doll-envelopes/)

> 难度 困难

给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 `(w, h)` 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。

请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。

**说明:**
不允许旋转信封。

**示例:**

```
输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
输出: 3 
解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
```

---

## 题解：

用类似一维最长子序列的方法来做，只不过原来是去找**比当前位置数字小**的前序数字，现在是找**比当前长宽都要小**的前序数字对而已。而由于长宽之间并没有联系，所以我们需要先对其中的一维进行排序，这样方便遍历。

```java
public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length==0) {
            return 0;
        }
        int[] dp = new int[envelopes.length];
        dp[0] = 1;
        Arrays.sort(envelopes, new Comparator<int[]>() {	//先对第一维排序
            @Override
            public int compare(int[] a, int[] b){
                if(a[0]==b[0]){
                    return a[1] - b[1];
                }else {
                    return a[0] - b[0];
                }
            }
        });
    
        for (int i = 1; i < envelopes.length; i++) {
            int prevMaxLen = 0;
            for (int j = 0; j < i; j++) {
                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {	//长宽都比当前小
                    prevMaxLen = Math.max(prevMaxLen,dp[j]);								//找前序最多套娃数
                }
            }
            dp[i] = prevMaxLen + 1;														//当前信封套娃数
        }
        Arrays.sort(dp);
        return dp[dp.length - 1];
    }
```

