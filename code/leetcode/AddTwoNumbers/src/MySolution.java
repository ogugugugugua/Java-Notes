import java.util.ArrayList;
import java.util.List;

public class MySolution {

    /**
     * The first solution in mind, doesn't really work due to limits of width of Integer/Character.
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int num1 = 0;
        int num2 = 0;
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        while (l1 != null) {
            list1.add(l1.val);
            l1 = l1.next;
        }
        for (int i = list1.size() - 1; i >= 0; i--) {
            num1 = num1 * 10 + list1.get(i);
        }
        while (l2 != null) {
            list2.add(l2.val);
            l2 = l2.next;
        }
        for (int i = list2.size() - 1; i >= 0; i--) {
            num2 = num2 * 10 + list2.get(i);
        }
        int sum = (char) num1 + num2;
        ListNode result = new ListNode(sum % 10);
        ListNode temp = result;
        while (sum != 0) {
            sum = sum / 10;
            if (sum != 0) {
                temp.next = new ListNode(sum % 10);
            }
            temp = temp.next;
        }
        return result;
    }

    /**
     * A workable solution, but a bit slow.
     * This is my own solution.
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        System.out.println(l1);
        System.out.println(l2);
        ListNode result = new ListNode();
        ListNode temp = result;
        int carry = 0;
        while (l1 != null && l2 != null) {
            temp.val = (l1.val + l2.val + carry) % 10;
            carry = (l1.val + l2.val + carry) / 10;
            l1 = l1.next;
            l2 = l2.next;
            if (l1 != null && l2 != null) {
                temp.next = new ListNode();
                temp = temp.next;
            }
        }
        while (l1 != null) {
            temp.next = new ListNode();
            temp = temp.next;
            temp.val = (l1.val + carry) % 10;
            carry = (l1.val + carry) / 10;
            l1 = l1.next;
        }
        while (l2 != null) {
            temp.next = new ListNode();
            temp = temp.next;
            temp.val = (l2.val + carry) % 10;
            carry = (l2.val + carry) / 10;
            l2 = l2.next;
        }
        if (carry != 0) {
            temp.next = new ListNode();
            temp = temp.next;
            temp.val = carry;
        }
        return result;
    }

    public static void main(String[] args) {
        MySolution test = new MySolution();
        ListNode l1 = new ListNode(9);
        ListNode l2 = new ListNode(1, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9
                , new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))))))))));

//        ListNode l1 = new ListNode(2,new ListNode(4,new ListNode(3)));
//        ListNode l2 = new ListNode(5,new ListNode(6,new ListNode(4)));

        System.out.println(test.addTwoNumbers3(l1, l2));
    }
}
