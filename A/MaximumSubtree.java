
//  Maximum Subtree
// Given a binary tree, find the subtree with maximum sum. Return the root of the subtree.

// Example
// Given a binary tree:

//      1
//    /   \
//  -5     2
//  / \   /  \
// 0   3 -4  -5 
// return the node with value 3.

// Notice
// LintCode will print the subtree which root is your return node.
// It's guaranteed that there is only one subtree with maximum sum and the given binary tree is not an empty tree.


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
     * @param root: the root of binary tree
     * @return: the maximum weight node
     */
    class Point{
        TreeNode node;
        int sum;
        Point(TreeNode node , int sum){
            this.node = node;
            this.sum = sum;
        }

    }
    Point max = new Point(root , Integer.MIN_VALUE);
    public TreeNode findSubtree(TreeNode root) {
        helper(TreeNode root);
        return max.node;
    }
    private Point helper(TreeNode root ){
        if(root == null){
            return new Point(null , 0);
        }
        Point left = helper(root.left);
        Point right = helper(root.right);
        Point mid = new Point(root ,left.sum + right.sum + root.val);
        if(max.sum < mid.sum){
            max = mid; 
        }
        return new Point(root ,left.sum + right.sum + root.val);
    }
}