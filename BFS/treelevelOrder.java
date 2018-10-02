/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: A Tree
     * @return: Level order a list of lists of integer
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        if(root == null){
            return res;
        }
        
        Queue<TreeNode> que = new LinkedList();
        que.offer(root);
        while(!que.isEmpty()){
            int size = que.size();
            for(int i = 0 ; i < size ; i++){
                TreeNode cur = que.poll();
                res.add(new ArrayList(Arrays.asList(cur.val)));
                if(res.left != null){
                    que.offer(res.left);
                }
                if(res.right != null){
                    que.offer(res.right);
                }

            }
        }
        return res;
    }
   
}