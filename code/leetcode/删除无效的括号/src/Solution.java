import java.util.*;

public class Solution {
    public static void main(String[] args) {
        String str = "n";
        System.out.println(removeInvalidParentheses(str));
    }

    public static boolean ok(char[] chars) {
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i]=='('){
                count++;
            }
            if (chars[i] == ')') {
                count--;
            }
            if (count < 0) {    //当右括号多于左括号时肯定无法匹配，故为false
                return false;
            }
        }
        return count==0;        //当左括号多于右括号时count不等于0
    }

    public static void dfs(String s, int index, int left, int right, HashSet<String> set) {
        //最开始的写法是if (left == 0 && right == 0 && ok(s.toCharArray()))，这样会把很多本来就应该退出的但不匹配的情况漏过去，导致耗时倍增
        if (left == 0 && right == 0) {
            if (ok(s.toCharArray()))
                set.add(s);
            return;
        }
        for (int i = index; i < s.length(); i++) {                      //遍历后面剩下的序列，先去除右括号，然后再去除左括号
            if (i!=index && s.charAt(i)==s.charAt(i-1)) continue;       //减少重复计算
            if (s.charAt(i) == ')' || s.charAt(i) == '(') {             //遇到字符直接跳过
                String cur = new StringBuffer(s).deleteCharAt(i).toString();    //去除当前括号
                if (right > 0 && s.charAt(i) == ')') {                  //优先去除右括号，这样有利于形成匹配序列
                    dfs(cur, i, left, right - 1, set);              //接下来需要去除的右括号数量减一，其余不变
                } else if (left > 0 && s.charAt(i) == '(') {            //然后去除左括号
                    dfs(cur, i, left - 1, right, set);              //接下来需要去除的左括号数量减一，其余不变
                }
            }
        }
    }

    public static List<String> removeInvalidParentheses(String s) {
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            }
            if (s.charAt(i) == ')') {
                if (left==0) {
                    right++;    //找不到匹配的左括号，意味着这个右括号一定是非法匹配，需要删除
                }
                else {
                    left--;     //可以找到匹配的左括号，那就需要删除的左括号数量减一
                }
            }
        }
        HashSet<String> set = new HashSet<>();
        dfs(s, 0, left, right, set);
        return new ArrayList<>(set);
    }
}
