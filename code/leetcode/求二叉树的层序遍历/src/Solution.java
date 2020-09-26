import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
  int val = 0;
  TreeNode left = null;
  TreeNode right = null;


    @Override
    public String toString() {
        return "{" + val +
                ", " + left +
                ", " + right +
                '}';
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(int val) {
        this.val = val;
    }
}

public class Solution {

    public static ArrayList<ArrayList<Integer>> levelOrder (TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();  //结果集
        ArrayList<Integer> layer = new ArrayList<>();           //用来放置每一层的元素
        if(root == null) return new ArrayList<>();              //空直接返回

        TreeNode temp;                      //用于存放队列弹出的元素
        Queue<TreeNode> queue = new LinkedList<>();             //队列
        queue.offer(root);                  //先将头结点放进去

        while (!queue.isEmpty()) {
            int size = queue.size();        //在这里记录当前queue的大小非常重要，因为它会不断变化，就无法知道该层的元素个数
            while (size-- > 0) {
                temp = queue.poll();
                layer.add(temp.val);        //弹出的这个元素属于当前层，可以直接放进结果
                if (temp.left!=null){
                    queue.offer(temp.left); //左子树非空则进队列
                }
                if (temp.right != null) {
                    queue.offer(temp.right);//右子树非空则进队列
                }
            }
            res.add(layer);                 //把当前层放进结果集
            layer = new ArrayList<>();      //初始化当前层
        }

        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5, new TreeNode(6), new TreeNode(7))), new TreeNode(3));
        ArrayList<ArrayList<Integer>> res = levelOrder(root);
        System.out.println(res);
    }
}
