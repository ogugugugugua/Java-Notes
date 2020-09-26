public class Solution {
    public static ListNode reverseBetween (ListNode head, int m, int n) {
        if (m==n) return head;

        ListNode res = head;
        ListNode[] t = new ListNode[n - m + 2];     //指针数组
        for (int i = 1; i < m-1; i++) {
            head = head.next;                       //移到m的前一位
        }
        if (m != 1) {
            t[0] = head.next;                       //t[0]的位置就是第m个节点
            for (int i = 1; i < t.length; i++) {
                t[i] = t[i - 1].next;               //分别将数组里的指针 指向需要翻转的节点。其中最后一个指针指向n+1的位置
            }

            head.next = t[t.length - 2];            //翻转组的前一个节点的next应该指向翻转组的最后一个节点即t[t.length - 2]
            t[0].next = t[t.length - 1];            //翻转组的第一个节点的next应该指向翻转组的后一个节点即t[t.length - 1]
            for (int i = 1; i < t.length - 1; i++) {
                t[i].next = t[i - 1];               //组内翻转
            }
        } else {
            t[0] = head;                            //当m==1时，t[0]就是头节点head
            for (int i = 1; i < t.length; i++) {
                t[i] = t[i - 1].next;               //分别将数组里的指针 指向需要翻转的节点。其中最后一个指针指向n+1的位置
            }
            res = t[t.length - 2];                  //输出的root应该指向翻转组的最后一个节点即t[t.length - 2]
            t[0].next = t[t.length-1];              //翻转组的第一个节点的next应该指向翻转组的后一个节点即t[t.length - 1]
            for (int i = 1; i < t.length - 1; i++) {
                t[i].next = t[i - 1];               //组内翻转
            }
        }
        return res;
    }
}
