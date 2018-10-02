// Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

// Example
// Given binary tree {3,9,20,#,#,15,7},

//     3
//    / \
//   9  20
//     /  \
//    15   7
 

// return its bottom-up level order traversal as:

// [
//   [15,7],
//   [9,20],
//   [3]
// ]


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
            List<Integer> list = new ArrayList();
            for(int i = 0 ; i < size ; i++){
                TreeNode cur = que.poll();
                list.add(cur.val);
                if(cur.left != null){
                    que.offer(que.left);
                }
                if(cur.right != null){
                    que.offer(que.right);
                }
            }
            res.add(list);
        }
        return res;



    }
}




















public class Solution {
    /**
     * @param root: A tree
     * @return: buttom-up level order a list of lists of integer
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        if(root == null){
            return res;
        }
        Queue<TreeNode> que = new LinkedList();
        que.offer(root);
        bfs(res , que);
        Collections.reverse(res);
        return res;
    }
    private void bfs(List<List<Integer>> res , Queue<TreeNode> que){
        while(!que.isEmpty()){
            List<Integer> cur = new ArrayList();
            int size = que.size();
            for(int i = 0 ; i < size ; i ++){
                TreeNode tmp = que.poll();
                cur.add(tmp.val);
                if(tmp.left != null){
                    que.offer(tmp.left);
                }
                if(tmp.right != null){
                    que.offer(tmp.right);
                }
            }
            res.add(cur);
        }
    }
}