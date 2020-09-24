import java.util.ArrayList;
import java.util.List;

class TreeNode {
  int val = 0;
  TreeNode left = null;
  TreeNode right = null;
  public TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
  }
}

public class Solution {
    /**
     *
     * @param root TreeNode类 the root of binary tree
     * @return int整型二维数组
     */
    static List<Integer> Pre = new ArrayList<>();
    static List<Integer> Mid = new ArrayList<>();
    static List<Integer> Pos = new ArrayList<>();
    public static void pre(TreeNode root){
        if(root!=null){
            Pre.add(root.val);
            pre(root.left);
            pre(root.right);
        }
    }
    public static void mid(TreeNode root){
        if(root!=null){
            mid(root.left);
            Mid.add(root.val);
            mid(root.right);
        }
    }
    public static void pos(TreeNode root){
        if(root!=null){
            pos(root.left);
            pos(root.right);
            Pos.add(root.val);
        }
    }
    public int[][] threeOrders (TreeNode root) {
        pre(root);
        mid(root);
        pos(root);
        int[][] result = new int[3][Pre.size()];
        for(int i =0;i<Pre.size();i++){
            result[0][i] = Pre.get(i);
            result[1][i] = Mid.get(i);
            result[2][i] = Pos.get(i);
        }
        return result;
    }
}
