import java.util.Stack;

/**
 * 运行超时:您的程序未能在规定时间内运行结束，请检查是否循环有错或算法复杂度过大。
 * 用例通过率: 75.00% 运行时间: 4001ms 占用内存: 233560KB
 */
public class MySolution {
    /**
     *
     * @param head1 ListNode类
     * @param head2 ListNode类
     * @return ListNode类
     */
    public ListNode addInList (ListNode head1, ListNode head2) {
        Stack<Integer> h1 = new Stack<>();
        Stack<Integer> h2 = new Stack<>();
        Stack<Integer> sum = new Stack<>();

        while(head1!=null){
            h1.push(head1.val);
            head1 = head1.next;
        }
        while(head2!=null){
            h2.push(head2.val);
            head2 = head2.next;
        }
        int len = Math.max(h1.size(), h2.size());
        int up = 0, pop = 0;
        for (int i = 0; i < len; i++) {
            if (!h1.empty() && !h2.empty()) {
                pop = h1.pop() + h2.pop() + up;
                sum.add(pop % 10);
                up = pop / 10;
            } else if (!h1.empty() && h2.empty()) {
                pop = h1.pop() + up;
                sum.add(pop % 10);
                up = pop / 10;
            }else {
                pop = h2.pop() + up;
                sum.add(pop % 10);
                up = pop / 10;
            }
        }
        if (up!=0){
            sum.add(up);
        }

        ListNode head = new ListNode(0);
        ListNode res = head;
        while (!sum.isEmpty()){
            head.next = new ListNode(sum.pop());
            head = head.next;
        }
        return res.next;
    }
}
