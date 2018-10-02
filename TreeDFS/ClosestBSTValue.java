// Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

// Example
// Given root = {1}, target = 4.428571, return 1.

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
// inorder 
public class Solution {
    /**
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
       /**
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
    double t;
    double res = Integer.MAX_VALUE;
    double pre = Integer.MAX_VALUE;
    public int closestValue(TreeNode root, double target) {
        t = target;
        traverse(root);
        return (int)res;
    }
    private void traverse(TreeNode root){
        if(root == null){
            return;
        }
        traverse(root.left);
        if(Math.abs(root.val - t) < pre){
            pre = Math.abs(root.val - t);
            res = root.val;
        }
        traverse(root.right);

    }
}



// pre order
public class Solution {
    /**
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
       /**
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
    double t;
    double res = Integer.MAX_VALUE;
    double pre = Integer.MAX_VALUE;
    public int closestValue(TreeNode root, double target) {
        t = target;
        traverse(root);
        return (int)res;
    }
    private void traverse(TreeNode root){
        if(root == null){
            return;
        }
        if(Math.abs(root.val - t) < pre){
            pre = Math.abs(root.val - t);
            res = root.val;
        }
        if(t < root.val){
            traverse(root.left);
        }
        else{
            traverse(root.right);
        }
  
    }
}
public class Solution {
    int goal;
    double min = Double.MAX_VALUE;
 
    public int closestValue(TreeNode root, double target) {
        helper(root, target);
        return goal;
    }
 
    public void helper(TreeNode root, double target){
        if(root==null)
            return;
 
        if(Math.abs(root.val - target) < min){
            min = Math.abs(root.val-target);
            goal = root.val;
        } 
 
        if(target < root.val){
            helper(root.left, target);
        }else{
            helper(root.right, target);
        }
    }
}




class Solution {
    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return 0;
        }
        
        TreeNode lowerNode = lowerBound(root, target);
        TreeNode upperNode = upperBound(root, target);
        
        if (lowerNode == null) {
            return upperNode.val;
        }
        
        if (upperNode == null) {
            return lowerNode.val;
        }
        
        if (target - lowerNode.val > upperNode.val - target) {
            return upperNode.val;
        }
        
        return lowerNode.val;
    }
    
    // find the node with the largest value that smaller than target
    private TreeNode lowerBound(TreeNode root, double target) {
        if (root == null) {
            return null;
        }
        
        if (target <= root.val) {
            return lowerBound(root.left, target);
        }
        
        // root.val < target
        TreeNode lowerNode = lowerBound(root.right, target);
        if (lowerNode != null) {
            return lowerNode;
        }
        
        return root;
    }
    
    // find the node with the smallest value that larger than or equal to target
    private TreeNode upperBound(TreeNode root, double target) {
        if (root == null) {
            return null;
        }
        
        if (root.val < target) {
            return upperBound(root.right, target);
        }
        
        // root.val >= target
        TreeNode upperNode = upperBound(root.left, target);
        if (upperNode != null) {
            return upperNode;
        }
        
        return root;
    }
    
}