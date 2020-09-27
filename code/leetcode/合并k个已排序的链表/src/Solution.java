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
    //找出当前ArrayList中链表头节点值最小的那个链表
    public static ListNode findMin(ArrayList<ListNode> lists) {
        ListNode min = new ListNode(Integer.MAX_VALUE);
        for (ListNode node : lists) {
            if (node==null)
                continue;
            min = (min.val < node.val) ? min : node;
        }
        return  min;
    }

    //找出当前ArrayList有多少个非null链表
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
            ListNode min = findMin(lists);      //找到最小的头结点所在链表
            cur.next = min;                     //将其接到结果链表的末端
            cur = cur.next;                     //当前节点往后移一位，方便下轮操作
            ListNode temp = min.next;           //相当于把min链表的头砍掉
            int indexMin = lists.indexOf(min);  //找到min所在ArrayList中的位置
            lists.set(indexMin, temp);          //用砍掉头的链表替换原来位置的min链表
        }
        return res.next;                        //返回结果
    }
}
