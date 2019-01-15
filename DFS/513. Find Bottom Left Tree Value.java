// 513. Find Bottom Left Tree Value
// Medium

// 477

// 78

// Favorite

// Share
// Given a binary tree, find the leftmost value in the last row of the tree.

// Example 1:
// Input:

//     2
//    / \
//   1   3

// Output:
// 1
// Example 2: 
// Input:

//         1
//        / \
//       2   3
//      /   / \
//     4   5   6
//        /
//       7

// Output:
// 7
// Note: You may assume the tree (i.e., the given root node) is not NULL.

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
    int res;
    public int findBottomLeftValue(TreeNode root) {
        int height = helper(root);
        return dfs(root, height - 1);
    }
    private int helper(TreeNode root){
        if(root == null) return 0;
        return Math.max(helper(root.left) , helper(root.right)) + 1;   
    }
    private boolean dfs(TreeNode root, int height){ 
        if(root == null) return false;
        if(height == 0){
            res = root.val;
            return true;
        } 
        if(dfs(root.left , height - 1))return true;
        if(dfs(root.right , height - 1)) return true;
        return false;
    }
}
