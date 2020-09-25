import java.util.Stack;

class TreeNode {
  int val = 0;
  TreeNode left = null;
  TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "{" +val +
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
    //两个栈分别用来存储
    static Stack<TreeNode> s1 = new Stack<>();
    static Stack<TreeNode> s2 = new Stack<>();

    //核心函数，用来在二叉树中根据target寻找其各代的父节点
    public static boolean dfs_find(TreeNode root, Stack<TreeNode> stack, int target) {
        if (root == null) {                             //找到叶子节点也没有找到，返回false
            return false;
        }
        if (root.val == target) {                       //如果找到了target就不需要继续往下找了，返回true，以便于上层压栈
            return true;
        }
        if (dfs_find(root.left, stack,target)) {        //如果左子树找到，证明target在左边，所以把左节点加进s1中并返回
            stack.push(root.left);
            return true;
        } else if (dfs_find(root.right, stack,target)){ //如果右子树找到，证明target在右边，所以把右节点加进s1中并返回
            stack.push(root.right);
            return true;
        }
        return false;
    }

    public static boolean DfsFind(TreeNode root, Stack<TreeNode> stack, int target) {
        //上面的dfs_find函数没有把根节点root压栈，所以需要在这里压进去
        boolean found = dfs_find(root,stack,target);
        stack.push(root);
        return found;
    }

    //用于寻找两个栈的最晚的那个相同点，即最近共同祖先
    public static TreeNode getSameAncestors(Stack<TreeNode> s1, Stack<TreeNode> s2) {
        int len = Math.min(s1.size(), s2.size());   //只需要遍历短栈的长度，长出来的那部分肯定不一样
        TreeNode res = null;
        for (int i = 0; i < len; i++) {
            if (s1.peek() == s2.peek()) {           //只要还相同，就pop
                res = s1.pop();                     //记录一下当前的最近共同祖先
                s2.pop();
            }else
                break;                              //遇到不同的祖先了，可以停下来
        }
        return res;                                 //这里返回的便是最近共同祖先
    }

    public static int lowestCommonAncestor (TreeNode root, int o1, int o2) {
        DfsFind(root, s1, o1);  //先分别寻找一次，把数据压入static的栈内
        DfsFind(root, s2, o2);  //先分别寻找一次，把数据压入static的栈内
        return getSameAncestors(s1,s2).val; //调用函数找到最近公共祖先
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5, new TreeNode(6), new TreeNode(7))), new TreeNode(3));
        System.out.println(lowestCommonAncestor(root, 7, 4));
    }
}
