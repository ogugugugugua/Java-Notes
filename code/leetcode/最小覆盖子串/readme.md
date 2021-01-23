# [76. 最小覆盖子串](https://leetcode-cn.com/problems/minimum-window-substring/)

> 难度 困难

给你一个字符串 `s` 、一个字符串 `t` 。返回 `s` 中涵盖 `t` 所有字符的最小子串。如果 `s` 中不存在涵盖 `t` 所有字符的子串，则返回空字符串 `""` 。

**注意：**如果 `s` 中存在这样的子串，我们保证它是唯一的答案。

 

**示例 1：**

```
输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC"
```

**示例 2：**

```
输入：s = "a", t = "a"
输出："a"
```

 

**提示：**

- `1 <= s.length, t.length <= 105`
- `s` 和 `t` 由英文字母组成

 

**进阶：**你能设计一个在 `o(n)` 时间内解决此问题的算法吗？

```java
/**
 * 滑动窗口法
 * 在这个方法中的window表用于对比滑动窗口内有效字符个数与目标序列的关系，并不用于记录最后输出的结果
 * @param s source
 * @param t target
 * @return 最小覆盖子串
 */
public String slideWindow(String s, String t){
    int left = 0, right = 0, valid = 0, start = 0;
    int minLen = Integer.MAX_VALUE;
    Map<Character, Integer> window = new HashMap<>();
    Map<Character, Integer> needs = new HashMap<>();

    //记录目标序列的内容
    for (int i = 0; i < t.length(); i++) {
        needs.put(t.charAt(i), needs.getOrDefault(t.charAt(i), 0) + 1);
    }

    while (right < s.length()) {
        char c = s.charAt(right);
        right++;
        // 当当前字符在目标序列中时才记录下来
        if (needs.containsKey(c)){
            // window表中对应字符的数量+1
            window.put(c, window.getOrDefault(c, 0) + 1);
            // 若这个字符的数量等于目标序列中对应字符的数量，则有效的字符数量+1
            if (window.get(c).equals(needs.get(c))) {
                valid++;
            }
        }

        //当凑够足够的有效字符后，开始将滑动窗口的左侧进行收缩
        while (valid == needs.size()) {
            // 记录最小长度和开始位置，用于最后获取结果
            if (minLen > right - left) {
                start = left;
                minLen = right - left;
            }

            char c1 = s.charAt(left);
            left++;
            // 若最左侧收缩的那个字符数量等于目标序列中对应字符的数量，那么证明滑动窗口内的字符串不符合要求了，所以valid-1以退出当前while循环
            if (needs.containsKey(c1)){
                if (window.get(c1).equals(needs.get(c1))) {
                    valid--;
                }
                // 在window表中将该有效字符的个数-1
                window.put(c1, window.getOrDefault(c1, 0) - 1);
            }

        }
    }
    return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
}
```

