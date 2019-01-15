// 230. Kth Smallest Element in a BST
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

// Note: 
// You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

// Example 1:

// Input: root = [3,1,4,null,2], k = 1
//    3
//   / \
//  1   4
//   \
//    2
// Output: 1
// Example 2:

// Input: root = [5,3,6,2,4,null,null,1], k = 3
//        5
//       / \
//      3   6
//     / \
//    2   4
//   /
//  1
// Output: 3
// Follow up:
// What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
//  How would you optimize the kthSmallest routine?


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 //BST上面的二分法 
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int count = count(root.left);
        if(count == k - 1){
            return root.val;
        }
        else if(count > k - 1){
            return kthSmallest(root.left , k);
        }
        else{
            return kthSmallest(root.right , k - 1 - count);
        }
    }
    private int count(TreeNode root){
        if(root == null) return 0;
        return 1 + count(root.left) + count(root.right);
    }
}




class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int[] res = new int[1];
        helper(root, new int[]{k}, res);
        return res[0];
    }
    private void helper(TreeNode root ,int[] ct , int[] res){
        if(root == null ) return;
        helper(root.left , ct , res);
        ct[0]--;
        if(ct[0] == 0){
            res[0] = root.val;
            return;
        }
        helper(root.right , ct , res);
    }
}


Binary Search (dfs): most preferable

  public int kthSmallest(TreeNode root, int k) {
        int count = countNodes(root.left);
        if (k <= count) {
            return kthSmallest(root.left, k);
        } else if (k > count + 1) {
            return kthSmallest(root.right, k-1-count); // 1 is counted as current node
        }
        return root.val;
    }
    
    public int countNodes(TreeNode n) {
        if (n == null) return 0;
        
        return 1 + countNodes(n.left) + countNodes(n.right);
    }
DFS in-order recursive:

    // better keep these two variables in a wrapper class
    private static int number = 0;
    private static int count = 0;

    public int kthSmallest(TreeNode root, int k) {
        count = k;
        helper(root);
        return number;
    }
    
    public void helper(TreeNode n) {
        if (n.left != null) helper(n.left);
        count--;
        if (count == 0) {
            number = n.val;
            return;
        }
        if (n.right != null) helper(n.right);
    }