class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class Solution {
    public static void main(String[] args) {

    }
    public ListNode ReverseList(ListNode head) {
        if(head == null)
            return null;
        ListNode current = head;
        ListNode result = new ListNode(current.val);
        ListNode temp = result;
        while(current!=null){
            current = current.next;
            if(current!=null){
                result = new ListNode(current.val);
                result.next = temp;
                temp = result;
            }
        }
        return result;
    }
    public ListNode ReverseListOfficialSolution(ListNode head){
        // 判断链表为空或长度为1的情况
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;            // 当前节点的前一个节点
        ListNode current = head;
        ListNode next = null;           // 当前节点的下一个节点
        while (current != null) {
            current = current.next;     // 记录当前节点的下一个节点位置；
            current.next = pre;         // 让当前节点指向前一个节点位置，完成反转
            pre = current;              // pre 往右走
            current = next;             // 当前节点往右继续走
        }
        return pre;
    }
}
