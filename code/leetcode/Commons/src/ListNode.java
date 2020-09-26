public class ListNode {
    int val;
    ListNode next = null;

    @Override
    public String toString() {
        return "{" +val +
                ", " + next +
                '}';
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(int val) {
        this.val = val;
    }
}
