// Binary Tree Longest Consecutive Sequence
// Given a binary tree, find the length of the longest consecutive sequence path.

// The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

// Example
// For example,

//    1
//     \
//      3
//     / \
//    2   4
//         \
//          5
// Longest consecutive sequence path is 3-4-5, so return 3.

//    2
//     \
//      3
//     / 
//    2    
//   / 
//  1
// Longest consecutive sequence path is 2-3,not3-2-1, so return 2.


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