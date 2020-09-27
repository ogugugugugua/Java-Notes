import java.util.ArrayList;
import java.util.Arrays;
//爽快的一次AC！
public class Solution {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1,new ListNode(3,new ListNode(6)));
        ListNode l2 = new ListNode(2,new ListNode(4,new ListNode(7)));
        ListNode l3 = new ListNode(3,new ListNode(5,new ListNode(8)));
        ArrayList<ListNode> lists = new ArrayList<>(Arrays.asList(l1, l2, l3));
        System.out.println(lists);
        System.out.println(mergeKLists(lists));
    }
    public static ListNode findMin(ArrayList<ListNode> lists) {
        ListNode min = new ListNode(Integer.MAX_VALUE);
        for (ListNode node : lists) {
            if (node==null)
                continue;
            min = (min.val < node.val) ? min : node;
        }
        return  min;
    }

    public static int countListNode(ArrayList<ListNode> lists) {
        int num = 0;
        for (ListNode node : lists) {
            if (node != null) {
                num++;
            }
        }
        return num;
    }

    public static ListNode mergeKLists(ArrayList<ListNode> lists) {
        if (lists==null) return null;
        ListNode res = new ListNode(-1);
        ListNode cur = res;

        while (countListNode(lists) > 0) {
            ListNode min = findMin(lists);
            cur.next = min;
            cur = cur.next;
            ListNode temp = min.next;
            int indexMin = lists.indexOf(min);
            lists.set(indexMin, temp);
        }
        return res.next;
    }
}
