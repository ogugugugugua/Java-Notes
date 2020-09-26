import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
  int val = 0;
  TreeNode left = null;
  TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }

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
}


public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5, new TreeNode(6), new TreeNode(7))), new TreeNode(3));
        ArrayList<ArrayList<Integer>> lists = zigzagLevelOrder(root);
        System.out.println(lists);
    }
    public static ArrayList<ArrayList<Integer>> zigzagLevelOrder (TreeNode root) {
        if(root==null) return new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        queue.offer(root);
        boolean direction = true;
        TreeNode temp;

        while(!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> layer = new ArrayList<>();
            direction = !direction;
            while(size-- > 0){
                temp = queue.poll();
                layer.add(temp.val);
                if(direction){
                    if(temp.left!=null)
                        queue.offer(temp.left);
                    if(temp.right!=null)
                        queue.offer(temp.right);
                }else{
                    if(temp.right!=null)
                        queue.offer(temp.right);
                    if(temp.left!=null)
                        queue.offer(temp.left);
                }
            }
            res.add(layer);
        }
        return res;
    }
}
