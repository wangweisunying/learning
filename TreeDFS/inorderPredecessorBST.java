// Inorder Predecessor in BST
// Given a binary search tree and a node in it, find the in-order predecessor of that node in the BST.

// Example
// Given root = {2,1,3}, p = 1, return null.

// Notice
// If the given node has no in-order predecessor in the tree, return null

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
// 1100,1000,#,900,#,8,#,#,100,#,101,#,200,190,#,183,#,172,#,161}
// 183
public class Solution {
    /**
     * @param root: the given BST
     * @param p: the given node
     * @return: the in-order predecessor of the given node in the BST
     */
    TreeNode res;
    public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        if(root == null){
            return null;
        }
        if(root.val == p.val){
            if(root.left != null){ //root left 一定是比他小的最大的一个点
                return root.left;
            }
            return res;
        }
        if(root.val < p.val){
            res = root;
            return inorderPredecessor(root.right, p);
        }
        else{
            return inorderPredecessor(root.left , p);
        }
        
        
    }
}