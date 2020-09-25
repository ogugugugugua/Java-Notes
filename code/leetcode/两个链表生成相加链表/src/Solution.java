class ListNode {
  int val;
  ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "{" +
                "" + val +
                "," + next +
                '}';
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Solution {
    static ListNode addInList(ListNode head1, ListNode head2) {
        ListNode newh1 = null;
        ListNode newh2 = null;
        ListNode p1 = head1;
        ListNode p2 = head2;
        int len1 = 0, len2 = 0;

        reverseList(p1);
        reverseList(p2);

        //TODO

        return  null;
    }
    static ListNode reverseList(ListNode head) {
        if (head == null)
            return head;
        ListNode pre = head;// 上一结点
        ListNode cur = head.next;// 当前结点
        ListNode tmp;// 临时结点，用于保存当前结点的指针域（即下一结点）
        while (cur != null) {// 当前结点为null，说明位于尾结点
            tmp = cur.next;
            cur.next = pre;// 反转指针域的指向

            // 指针往下移动
            pre = cur;
            cur = tmp;
        }
        // 最后将原链表的头节点的指针域置为null，还回新链表的头结点，即原链表的尾结点
        head.next = null;
        return pre;
    }
}
