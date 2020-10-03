import javax.jws.soap.SOAPBinding;
import java.util.LinkedList;
import java.util.Objects;

public class Solution {
    public static void main(String[] args) {
        String s = "(2+6* 3+5- (3*14/7+2)*5)+3";
        System.out.println(new Solution().calculate(s));
    }

    public int calculate(String s) {
        return this.f(s.toCharArray(),0)[0];
    }

    //0:    结果
    //1:    i的位置
    public int[] f(char[] str, int i) {
        LinkedList<String> queue = new LinkedList<>();
        int cur = 0;
        int[] recursive = null;
        while (i < str.length && str[i] != ')') {
            if (str[i] == ' ') {
                i++;
                continue;
            }
            if (str[i] >= '0' && str[i] <= '9') {   //数字
                cur = cur * 10 + Integer.parseInt(String.valueOf(str[i++]));
            } else if (str[i] != '(') {             //运算符
                addNum(queue, cur);
                queue.offerLast(String.valueOf(str[i++]));
                cur = 0;
            } else {                                //左括号
                recursive = f(str, i + 1);
                i = recursive[1];
                cur = recursive[0];
            }
        }
        addNum(queue,cur);
        return new int[]{getNum(queue), i + 1};
    }

    public void addNum(LinkedList<String> queue, int num) {
        if (!queue.isEmpty()) {
            String lastOperator = queue.pollLast();
            if (lastOperator.equals("+") || lastOperator.equals("-")) {
                queue.offerLast(lastOperator);
                queue.offerLast(String.valueOf(num));
            } else {
                int lastNum = Integer.parseInt(Objects.requireNonNull(queue.pollLast()));
                lastNum = lastOperator.equals("*") ? lastNum * num : lastNum / num;
                queue.offerLast(String.valueOf(lastNum));
            }
        } else {
            queue.offerLast(String.valueOf(num));
        }
    }

    public int getNum(LinkedList<String> queue) {
        int result = Integer.parseInt(Objects.requireNonNull(queue.pollFirst()));
        int tempNum = 0;
        String tempOp = "";
        while (!queue.isEmpty()) {
            String s = queue.pollFirst();
            if (!s.equals("+") && !s.equals("-")) {
                tempNum = Integer.parseInt(s);
                result = tempOp.equals("+") ? result + tempNum : result - tempNum;
            } else {
                tempOp = s;
            }
        }
        return result;
    }
}
