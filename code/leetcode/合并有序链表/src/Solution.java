import java.util.*;


class ListNode {
    int val;
    ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }
}


public class Solution {
    /**
     *
     * @param l1 ListNode类 
     * @param l2 ListNode类 
     * @return ListNode类
     */
    public ListNode mergeTwoLists (ListNode l1, ListNode l2) {
        // write code here
        if(l1==null) return l2;
        if(l2==null) return l1;
        ListNode temp= new ListNode(0);
        ListNode head = temp;
        while(l1!=null || l2!=null){
            if(l1==null){
                temp.next = l2;
                temp = temp.next;
                l2 = l2.next;
                continue;
            }
            if(l2==null){
                temp.next = l1;
                temp = temp.next;
                l1 = l1.next;
                continue;
            }
            if(l1.val<l2.val){
                temp.next = l1;
                temp = temp.next;
                l1 = l1.next;
            }else{
                temp.next = l2;
                temp = temp.next;
                l2 = l2.next;
            }
        }
        return head.next;
    }
}