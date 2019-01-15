// 437. Path Sum III
// DescriptionHintsSubmissionsDiscussSolution
// You are given a binary tree in which each node contains an integer value.

// Find the number of paths that sum to a given value.

// The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

// The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

// Example:

// root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

//       10
//      /  \
//     5   -3
//    / \    \
//   3   2   11
//  / \   \
// 3  -2   1

// Return 3. The paths that sum to 8 are:

// 1.  5 -> 3
// 2.  5 -> 2 -> 1
// 3. -3 -> 11

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


 //画图 每一层分为两种情况 ， 用root val 和不用root val 无重复无遗漏
class Solution {
    public int pathSum(TreeNode root, int sum){
        if(root == null) return 0; 
        return helper(root , sum) +  pathSum(root.left , sum) + pathSum(root.right , sum);
    }
    private int helper(TreeNode root , int sum){
        if(root == null) return 0; 
        int res = 0;
        if(root.val == sum)res++;
        return res + helper(root.left , sum - root.val) + helper(root.right , sum - root.val);
    }
}

public class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    private int pathSumFrom(TreeNode node, int sum) {
        if (node == null) return 0;
        return (node.val == sum ? 1 : 0) 
            + pathSumFrom(node.left, sum - node.val) + pathSumFrom(node.right, sum - node.val);
    }
}


