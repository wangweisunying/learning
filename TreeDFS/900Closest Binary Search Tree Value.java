
// 900Closest Binary Search Tree Value
// Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

// Example
// Given root = {1}, target = 4.428571, return 1.

// Notice
// Given target value is a floating point.
// You are guaranteed to have only one unique value in the BST that is closest to the target.


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
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
    public int closestValue(TreeNode root, double target) {
        if(root.val < target && root.right != null){
            int right = closestValue(root.right , target);
            return right - target > target - root.val ? root.val : right;
        }
        if(root.val > target && root.left != null ){
            int left = closestValue(root.left , target);
            return root.val - target > target - left ? left : root.val;
        }
        return root.val;
    }
}