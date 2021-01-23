import java.util.*;

/**
 * @author XIE Yulin
 */
public class Solution {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(new Solution().slideWindow(s , t));
    }

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

    /**
     * 暴力法
     * @param s source
     * @param t target
     * @return 最小覆盖子串
     */
    public String brute(String s, String t){
        int minLen = Integer.MAX_VALUE;
        String result = "";
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (t.contains(""+s.charAt(j))){
                    set.add(s.charAt(j));
                }
                if (set.size()==t.length()){
                    set.clear();
                    minLen = Math.min(minLen, j - i +1  );
                    result = s.substring(i,j+1);
                    break;
                }
            }
        }
        return result;
    }
}
