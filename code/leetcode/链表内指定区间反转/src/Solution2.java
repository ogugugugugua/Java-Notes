public class Solution2 {

    //这个解法更加清晰简洁，但是需要搞懂其中的指针转换逻辑
    // 在输入链表的前面加一个-1节点：  -1 ==> 1 ==> 2 ==> 3 ==> 4 ==> 5
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head==null) return null;
        ListNode res = new ListNode(-1);
        res.next = head;
        ListNode pre = res;

        for (int i = 1; i < m; i++) {
            pre = pre.next;             //找到自己的位置后，位置始终不变，用来指向m的前一个节点，方便翻转过程的连接
        }
        ListNode cur = pre.next;        //指向当前要操作的节点
        for (int i = m; i < n; i++) {
            ListNode temp = cur.next;   //临时指针，指向当前节点的下一个

            cur.next = temp.next;       //把当前节点的next连接到还没标记的后两个节点（即本轮循环结束后紧邻的下一个节点）
            temp.next = pre.next;       //把目前已标记的最后一个指针指向pre的下一个指针，即放到最前面
            pre.next = temp;            //pre的下一个指针应该指向最新的那个“最前面节点”，即temp
        }
        return res.next;                //去除-1节点的影响，返回结果
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1,new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode reversed = reverseBetween(head, 2, 4);
        System.out.println(reversed);
    }
}
