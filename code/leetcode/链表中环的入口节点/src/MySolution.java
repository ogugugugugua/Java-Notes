import java.util.HashSet;

public class MySolution {
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> list = new HashSet<>();
        while(head!=null){
            if(list.contains(head)){
                return head;
            }
            list.add(head);
            head = head.next;
        }
        return null;
    }

    public static void main(String[] args) {
        //TODO
    }
}
