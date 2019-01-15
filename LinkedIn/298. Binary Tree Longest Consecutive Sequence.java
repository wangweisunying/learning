// 298. Binary Tree Longest Consecutive Sequence
// Medium

// Share
// Given a binary tree, find the length of the longest consecutive sequence path.

// The path refers to any sequence of nodes 
// from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

// Example 1:

// Input:

//    1
//     \
//      3
//     / \
//    2   4
//         \
//          5

// Output: 3

// Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
// Example 2:

// Input:

//    2
//     \
//      3
//     / 
//    2    
//   / 
//  1

// Output: 2 

// Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.

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
    int max = 0;
    public int longestConsecutive(TreeNode root) {
        helper(root);
        return max;
    }
    private int helper(TreeNode root){
        if(root == null) return 0;
        int res = 1;
        int left = helper(root.left);
        int right = helper(root.right);
        //if left side is not connected , dont compare , max already consider thect in the left branch
        if(left != 0 && root.left.val == root.val + 1){
            res = Math.max(res , left + 1 );
        }
        //if right side is not connected , dont compare , max already consider thect in the right branch
        if(right != 0 && root.right.val == root.val + 1){
            res = Math.max(res , right + 1);
        }
        //only the one has protentaily incresing going up
        max = Math.max(max , res);
        return Math.max(res , 1);
    }
}


public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */

    public int longestConsecutive(TreeNode root) {
        return helper(root , null , 0);

    }
    private int helper(TreeNode root , TreeNode parent ,int lengthWithoutRoot){
        if(root == null){
            return 0;
        }
        int length = (parent != null && parent.val + 1 == root.val) ?
                    lengthWithoutRoot + 1 : 1; 
        int left = helper(root.left , root , length);
        int right = helper(root.right , root , length);
        return  Math.max(length ,Math.max(left, right));
    }
}


// version 2: Another Traverse + Divide Conquer 
public class Solution {
    private int longest;
    
    /**
     * @param root the root of binary tree
     * @return the length of the longest consecutive sequence path
     */
    public int longestConsecutive(TreeNode root) {
        longest = 0;
        helper(root);
        return longest;
    }
    
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int left = helper(root.left);
        int right = helper(root.right);
        
        // int subtreeLongest = 1; // at least we have root
        int curLen = 1;
        if (root.left != null && root.val + 1 == root.left.val) {
            left++;
            curLen =  Math.max(left , curLen);
        }
        if (root.right != null && root.val + 1 == root.right.val) {
            right++;
            curLen =  Math.max(right , curLen);
        }
        longest = Math.max(longest,curLen);
        return curLen;
    }
}