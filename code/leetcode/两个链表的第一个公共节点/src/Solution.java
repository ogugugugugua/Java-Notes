import java.util.HashSet;

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
//使用Set的方法
public class Solution {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        HashSet<ListNode> set1 = new HashSet<>();
        while(pHead1!=null){
            set1.add(pHead1);
            pHead1 = pHead1.next;
        }
        while(pHead2!=null){
            if(set1.contains(pHead2))
                return pHead2;
            pHead2 = pHead2.next;
        }
        return null;
    }
}
