
// Check Full Binary Tree
// A full binary tree is defined as a binary tree in which all nodes have either zero or two child nodes.
//  Conversely, there is no node in a full binary tree, which has one child node. More information about full binary trees can be found here.

// Full Binary Tree
//       1
//      / \
//     2   3
//    / \
//   4   5

// Not a Full Binary Tree
//       1
//      / \
//     2   3
//    / 
//   4   
// Example
// Given tree {1,2,3}, return true
// Given tree {1,2,3,4}, return false
// Given tree {1,2,3,4,5} return true
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
     * @param root: the given tree
     * @return: Whether it is a full tree
     */
    boolean res ;
    public boolean isFullTree(TreeNode root) {
        res = true;
        helper(root);
        return res;
    }
    private TreeNode helper(TreeNode root){
        if(root == null || !res){
            return null;
        }
        TreeNode left = helper(root.left);
        TreeNode right = helper(root.right);
        if((left != null && right == null) || (left == null && right != null) ){
            res = false;
            return null;
        }
        return root;

    }
}