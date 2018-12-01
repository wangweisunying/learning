// 124. Binary Tree Maximum Path Sum
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-empty binary tree, find the maximum path sum.

// For this problem, a path is defined as any sequence of nodes from some starting node to any node in 
// the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

// Example 1:

// Input: [1,2,3]

//        1
//       / \
//      2   3

// Output: 6
// Example 2:

// Input: [-10,9,20,null,null,15,7]

//    -10
//    / \
//   9  20
//     /  \
//    15   7

// Output: 42


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Idea & Cases Explanation. Java + recursion
// At first, I think 2 parameters should be return in recursion. Then I find that we only need to update max once, 
// so I move max to be a member parameter.

// Basic idea:

// store/update max during in-order traversal.
// return maximum branches
// a) 0
// b)root.val
// c)root.val + dfs(root.left)
// d) root.val + dfs(root.right)
// Whole situation can be broken down to four cases:

// root
// left<0 right<0
// max = Math.max(0, root.val + 0 + 0), return Math.max(0,root.val)
// root
// left>0 right<0
// max = Math.max(0, root.val + dfs(root.left) + 0), return Math.max(0, root.val + dfs(root.left))
// root
// left<0 right>0
// max = Math.max(0, root.val + 0+ dfs(root.right) + 0), return Math.max(0, root.val + dfs(root.right))
// 4)root
// left>0 right>0
// max = Math.max(0, root.val + 0+ dfs(root.left) + dfs(root.right) ), return Math.max(0, root.val + dfs(root.left) + dfs(root.right))

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


// DG  ,1. 分为选  左 ， 右 ， 自己  可加 
        // 2. 若同时加了左右 ， 无法往上传递 ，所以只需要往上传递 1的值
class Solution {
    
    public int maxPathSum(TreeNode root) {
        int[] max = new int[]{Integer.MIN_VALUE};
        dfs(root , max);
        return max[0]; 
    }
    private int dfs(TreeNode root , int[] max){
        if(root == null) return 0;
        int res = root.val;
        int left = dfs(root.left , max);
        int right = dfs(root.right , max);
        int tmp0 = Math.max(left , right);
        res += tmp0 > 0 ? tmp0 : 0;
        int tmp1 = root.val + left + right;
        max[0] = Math.max(Math.max(res , tmp1) , max[0]);
        return res; 
    }
}


// [5,4,8,11,null,13,4,7,2,null,null,null,1]