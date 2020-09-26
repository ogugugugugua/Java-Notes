public class Solution2 {
    //使用重复遍历pHead2链表的方法，可能由于上一个方法中Set的操作时间过长，所以反而这个更快一点
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        while (pHead1!=null){
            ListNode temp = pHead2;
            while (temp != null) {
                if (temp == pHead1) {
                    return temp;
                }
                temp = temp.next;
            }
            pHead1 = pHead1.next;
        }
        return null;
    }
}
