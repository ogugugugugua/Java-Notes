package 单串.打家劫舍系列.打家劫舍3;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

    @Override
    public String toString() {
        return "{" +val +
                "," + left +
                "," + right +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return val == treeNode.val &&
                Objects.equals(left, treeNode.left) &&
                Objects.equals(right, treeNode.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, left, right);
    }
}

public class Solution {
    public int rob(TreeNode root) {
        HashMap<TreeNode, Integer> map = new HashMap<>();
        return robInternal(root, map);
    }

    public int robInternal(TreeNode root, HashMap<TreeNode, Integer> map) {
        if (root==null) return 0;
        if (map.containsKey(root)) return map.get(root);
        int money = root.val;
        if (root.left != null) {
            money += robInternal(root.left.left, map) + robInternal(root.left.right, map);
        }
        if (root.right != null) {
            money += robInternal(root.right.left, map) + robInternal(root.right.right, map);
        }
        int result = Math.max(money, robInternal(root.left, map) + robInternal(root.right, map));
        map.put(root, result);
        return result;
    }
}
