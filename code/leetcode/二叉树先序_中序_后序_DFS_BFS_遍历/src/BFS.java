import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    //使用BFS来计算二叉树的最大深度
    public int maxDepth (TreeNode root) {
        if(root==null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        int maxlen = 0;
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i<size; i++){
                TreeNode temp = queue.poll();
                if(temp.left!=null)
                    queue.offer(temp.left);
                if(temp.right!=null)
                    queue.offer(temp.right);
            }
            maxlen++;
        }
        return maxlen;
    }
}
