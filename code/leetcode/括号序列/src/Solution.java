import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        System.out.println(isValid("([)]"));
    }
    public static boolean isValid(String s) {
        ArrayList<Character> left = new ArrayList<>(Arrays.asList('{', '[', '('));
        ArrayList<Character> right = new ArrayList<>(Arrays.asList('}', ']', ')'));
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (left.contains(s.charAt(i))) {
                stack.push(s.charAt(i));
            }else {
                int index = right.indexOf(s.charAt(i));
                if (stack.isEmpty()) return false;
                if (stack.peek().equals(left.get(index))){
                    stack.pop();
                }else {
                    return false;
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }
}
