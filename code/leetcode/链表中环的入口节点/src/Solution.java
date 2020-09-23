import java.util.HashSet;

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
     }
 }

public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && slow != null) {
            ////利用快慢指针找相遇点
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ////设置以相同速度的新指针从起始位置出发
                ListNode slow2 = head;
                while (slow != slow) {      //未相遇循环。
                    slow = slow.next;
                    slow2 = slow2.next;
                }
                return slow2;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        //TODO
    }
}
