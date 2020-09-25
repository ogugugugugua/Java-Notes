class ListNode {
  int val;
  ListNode next = null;

    @Override
    public String toString() {
        return "{" +val +
                "," + next +
                '}';
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Solution {
    //用于计算链表长度
    public static int lenListNode(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    //用于得到当前节点后第i个节点
    public static ListNode getNext(ListNode head, int i) {
        ListNode res = head;
        for (int j = 0; j < i; j++) {
            res = res.next;
        }
        return res;
    }

    //链表中的节点每K个一组翻转
    public static ListNode reverseK(ListNode head, int k) {
        if (head==null) return null;
        ListNode res = null;                //记录返回的结果头
        ListNode prev = null;               //记录每一次翻转片段的最后一个节点，用于和后面那组翻转进行连接
        if (lenListNode(head)<k)            //太短，不能翻转，直接返回head
            return head;
        int n = lenListNode(head) / k;      //计算翻转次数

        //初始化指针
        ListNode[] t = new ListNode[k+1];   //这里使用了k+1大小的数组，因为除了当前组需要用到的k个指针之外，还需要和后面一组进行连接，所以多一个指针指向下一组
        t[0] = head;
        for (int i = 1; i < t.length; i++) {
            t[i] = t[i-1].next;
        }

        //翻转n次
        for (int i = 0; i < n; i++) {
            if (i==0){
                //第一组翻转
                t[0].next = t[t.length-1];          //当前组的第0个的next接到下一组的第一个
                for (int j = 1; j < k; j++) {
                    t[j].next = t[j-1];             //分别把组内的节点翻转
                }
                prev = t[0];                        //对于第一组翻转而言，第二组的previous节点就是第一组翻转后的最后一个，即t[0]，所以先保存好
                res = t[t.length - 2];              //第一组时记录返回的头指针
            }else {
                t[0].next = t[t.length-1];          //当前组的第0个的next接到下一组的第一个
                for (int j = 1; j < k; j++) {
                    t[j].next = t[j-1];             //分别把组内的节点翻转
                }
                prev.next = t[t.length-2];          //上一组的最后一个节点，即上一个for循环保存好的previous，它的next应该接到当前组翻转后的第一个节点，即t[t.length-2]。（因为t[t.length-1]不算在当前组内，只是个工具人）
                prev = t[0];                        //同理，对于下一组而言，它的previous节点就是当前组翻转之后的最后一个，即t[0]，所以先保存好
            }

            //重新初始化
            if (i!=n-1){                            //最后一次翻转执行完之后不需要再次初始化，否则会指到数组外面导致NullPointerException
                t[0] = t[t.length - 1];             //先把t[0]移动到后面，数组内的其他指针就可以根据t[0]来移动了
                for (int j = 1; j < t.length; j++) {
                    t[j] = getNext(t[0], j);        //数组t整体在链表上往后移动，为了下一组操作
                }
            }
        }
        return res;
    }

    //普通的翻转链表联系
    public static ListNode reverseList(ListNode head){
        ListNode pre = head;
        ListNode cur = (pre == null) ? null : pre.next;
        ListNode post = (cur == null) ? null : cur.next;

        if (pre==null||cur==null) return head;
        if (post == null) {
            cur.next = pre;     //反向
            pre.next = null;    //将原来头结点中的next置为null，否则会有循环引用
            return cur;
        }

        head.next = null;       //首先将原来头结点中的next置为null，否则会有循环引用
        while (post != null) {
            cur.next = pre;     //反向

            pre = cur;          //整体往后移一位
            cur = post;         //整体往后移一位
            post = post.next;   //整体往后移一位
        }
        cur.next = pre;         //不能忘了最后一个节点要指向倒数第二个节点
        return cur;             //返回最后一个节点即可
    }

    public static void main(String[] args){
        ListNode list = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5, new ListNode(6,new ListNode(7,new ListNode(8,new ListNode(9)))))))));
        System.out.println(reverseK(list, 3));
    }
}
