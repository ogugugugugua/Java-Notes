import java.util.Collections;
import java.util.Stack;

/**
 * 运行超时:您的程序未能在规定时间内运行结束，请检查是否循环有错或算法复杂度过大。
 * 用例通过率: 75.00% 运行时间: 4001ms 占用内存: 237700KB
 */
public class MySolution2 {
    /**
     *
     * @param head1 ListNode类
     * @param head2 ListNode类
     * @return ListNode类
     */
    public static ListNode addInList (ListNode head1, ListNode head2) {

        int lenH1 = 0, lenH2 = 0;
        ListNode l1 = head1;
        ListNode l2 = head2;
        while (l1 != null) {
            l1 = l1.next;
            lenH1++;
        }
        while (l2 != null) {
            l2 = l2.next;
            lenH2++;
        }

        int[] h1 = new int[lenH1];
        int[] h2 = new int[lenH2];

        for (int i = lenH1 - 1; i >= 0; i--) {
            h1[i] = head1.val;
            head1 = head1.next;
        }
        for (int i = lenH2 - 1; i >= 0; i--) {
            h2[i] = head2.val;
            head2 = head2.next;
        }

        int len = Math.max(lenH1, lenH2) + 1;
        int[] sum = new int[len];
        int up = 0, pop = 0;

        for (int i = 0; i < len - 1; i++) {
            if (i < lenH1 && i < lenH2) {
                pop = h1[i] + h2[i] + up;
                sum[i] = pop % 10;
                up = pop / 10;
            } else if (i < lenH1 && i >= lenH2) {
                pop = h1[i] + up;
                sum[i] = pop % 10;
                up = pop / 10;
            } else {
                pop = h2[i] + up;
                sum[i] = pop % 10;
                up = pop / 10;
            }
        }
        sum[sum.length-1] = up;

        ListNode head = new ListNode(0);
        ListNode res = head;
        for (int i = sum.length - 1; i >= 0; i--) {
            if (i == sum.length - 1 && sum[i] == 0) {
                continue;
            }
            head.next = new ListNode(sum[i]);
            head = head.next;
        }
        return res.next;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(9, (new ListNode(9, (new ListNode(9, (new ListNode(9)))))));
        ListNode head2 = new ListNode(8, (new ListNode(7, (new ListNode(6, (new ListNode(5)))))));
        System.out.println(addInList(head1, head2));
    }
}
