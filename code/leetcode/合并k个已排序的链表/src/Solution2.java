import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
//这里使用了分治的思想，把多个链表的合并分解成每两个链表的合并
public class Solution2 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(3, new ListNode(6)));
        ListNode l2 = new ListNode(2, new ListNode(4, new ListNode(7)));
        ListNode l3 = new ListNode(3,new ListNode(5,new ListNode(8)));
        ArrayList<ListNode> lists = new ArrayList<>(Arrays.asList(l1, l2, l3));
        System.out.println(mergeKLists(lists));
    }

    public static ListNode mergeKLists(ArrayList<ListNode> lists) {
        Stack<ListNode> stack = new Stack<>();
        if (lists.size()%2!=0)                                          //凑个偶数
            lists.add(null);
        for (int i = 0; i < lists.size(); i+=2) {
            stack.push(merge2Lists(lists.get(i), lists.get(i + 1)));    //把数组里面的链表，每两条先合并一次，放到栈里
        }
        while (stack.size()>=2) {                                       //在这里使用栈来代替递归的操作
            ListNode temp1 = stack.pop();                               //弹出来两个
            ListNode temp2 = stack.pop();
            stack.push(merge2Lists(temp1, temp2));                      //把合并好的那个链表重新塞回去
        }
        return stack.pop();                                             //这时候栈里面只剩一个，就是结果啦
    }

    public static ListNode merge2Lists(ListNode list1, ListNode list2) {//合并两个链表
        ListNode temp = new ListNode(-1);
        ListNode res = temp;
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                temp.next = list2;
                list2 = list2.next;
            } else {
                temp.next = list1;
                list1 = list1.next;
            }
            temp = temp.next;
        }
        temp.next = (list1 == null) ? list2 : list1;                    //记得把剩下的那段连上去
        return res.next;
    }
}
