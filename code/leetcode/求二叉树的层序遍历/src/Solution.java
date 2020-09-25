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
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> layer = new ArrayList<>();
        if(root == null) return new ArrayList<>();

        TreeNode temp;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                temp = queue.poll();
                layer.add(temp.val);
                if (temp.left!=null){
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
            res.add(layer);
            layer = new ArrayList<>();
        }

        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5, new TreeNode(6), new TreeNode(7))), new TreeNode(3));
//        TreeNode root = new TreeNode(1);
        ArrayList<ArrayList<Integer>> res = levelOrder(root);
        System.out.println(res);
    }
}
