
// 480. Binary Tree Paths
// Given a binary tree, return all root-to-leaf paths.

// Example
// Given the following binary tree:

//    1
//  /   \
// 2     3
//  \
//   5
// All root-to-leaf paths are:

// [
//   "1->2->5",
//   "1->3"
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
Input
{1,2,3,#,5}
Output
["1->2","1->2->5","1->2->5","1->3","1->3"]
Expected
["1->2->5","1->3"]


public class Solution {
    /**
     * @param root: the root of the binary tree
     * @return: all root-to-leaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
        
        List<String> res = new ArrayList();
        if(root == null){
            return res;    
        }
        helper(res ,String.valueOf(root.val),root);
        return res;
    }
    private void helper(List<String> res ,String cur, TreeNode root){
        if(root.left == null && root.right == null){
            res.add(cur);
            return;
        }
        if(root.left != null){
            helper(res, cur + "->" + root.left.val , root.left);
        }
        if(root.right != null){
            helper(res, cur + "->" + root.right.val , root.right);
        }
    }
}