class ListNode {
    int val;
    ListNode next = null;
}
public class Solution {

    public ListNode removeNthFromEnd (ListNode head, int n) {
        ListNode res = head;            //记录头结点以便于最后返回
        ListNode fast = head;           //快指针，用于探路找到链表末端
        ListNode slow = head;           //慢指针，即最后要被移除的节点
        for(int i =0;i<n;i++){
            fast = fast.next;           //拉开n步的差距，由于题目确保n有效，所以可以直接前进n步
        }
        while(fast!=null&&slow!=null){  //当还没到链表末端时，一起同速前进
            fast = fast.next;
            slow = slow.next;
        }
        if(head==slow){                 //这是防止slow指针从来没有移动过的情况
            return head.next;           //即移除头节点，返回head的下一个节点
        }
        while (head.next!=slow){        //从head开始找到slow的前一个节点，便于去除slow
            head = head.next;
        }
        head.next = slow.next;          //跨过slow，即删除slow指针所在的节点
        return res;                     //由于head已经移动，返回最开始记录的头结点
    }
}