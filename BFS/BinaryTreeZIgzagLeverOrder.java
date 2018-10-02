// Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

// Example
// Given binary tree {3,9,20,#,#,15,7},

//     3
//    / \
//   9  20
//     /  \
//    15   7
 

// return its zigzag level order traversal as:

// [
//   [3],
//   [20,9],
//   [15,7]
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
     * @return: A list of lists of integer include the zigzag level order traversal of its nodes' values.
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        if(root == null){
            return res;
        }
        Queue<TreeNode> que = new LinkedList();
        que.offer(root);
        bfs(res, que);
        return res;
    }
    private void bfs(List<List<Integer>> res , Queue<TreeNode> que ){
        boolean flag = true;
        while(!que.isEmpty()){
            List<Integer> cur = new ArrayList();
            int size = que.size();
            for(int i = 0 ; i < size ; i++ ){
                TreeNode tmp = que.poll();
                cur.add(tmp.val);  
                if(tmp.right != null){
                    que.offer(tmp.right);
                }
                if(tmp.left != null){
                    que.offer(tmp.left);
                }
                
            }
            if(!flag){
                res.add(cur);
                flag = true; 
            }
            else{
                Collections.reverse(cur);
                res.add(cur);
                flag = false;
            }
        }
    }
}