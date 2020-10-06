import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        String str = "n";
        System.out.println(removeInvalidParentheses(str));
    }
    public static boolean ok(char[] chars) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(' || chars[i] == ')') {
                if (chars[i] == '(') {
                    stack.push(chars[i]);
                } else if (chars[i] == ')' && !stack.isEmpty() && stack.peek() != '(') {
                    return false;
                } else if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        if (stack.isEmpty())
            return true;
        else
            return false;
    }

    public static boolean noParentheses(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                return false;
            }
        }
        return true;
    }

    public static List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (noParentheses(s) || ok(s.toCharArray())) {
            result.add(s);
        }
        for (int i = 0; i < s.length(); i++) {
            StringBuilder deleteChar = new StringBuilder(s);
            String deleteCharString = deleteChar.deleteCharAt(i).toString();
            if (ok(deleteCharString.toCharArray()) && !result.contains(deleteCharString) && !deleteCharString.equals("")) {
                result.add(deleteCharString);
            }
        }
        if (result.size() == 0) {
            System.out.println("yes");
            result.add("");
        }
        return result;
    }
}
