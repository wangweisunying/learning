// 270. Closest Binary Search Tree Value
// Easy

// 308

// 21

// Favorite

// Share
// Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

// Note:

// Given target value is a floating point.
// You are guaranteed to have only one unique value in the BST that is closest to the target.
// Example:

// Input: root = [4,2,5,1,3], target = 3.714286

//     4
//    / \
//   2   5
//  / \
// 1   3

// Output: 4
// Accepted
// 71,844s
// Submissions
// 169,548

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */





 
class Solution {
    public int closestValue(TreeNode root, double target) {
        if(root.val == target) return root.val;
        if(root.val > target){
            if(root.left != null){
                int left = closestValue(root.left , target);
                if(Math.abs(left - target) < Math.abs(root.val - target)) return left;
            }
            return root.val;
        }
        else{
            if(root.right != null){
            int right = closestValue(root.right , target);
            if(Math.abs(right - target) < Math.abs(root.val - target)) return right;
            }
            return root.val;
        }
    }
}


public int closestValue(TreeNode root, double target) {
    int ret = root.val;   
    while(root != null){
        if(Math.abs(target - root.val) < Math.abs(target - ret)){
            ret = root.val;
        }      
        root = root.val > target? root.left: root.right;
    }     
    return ret;
}