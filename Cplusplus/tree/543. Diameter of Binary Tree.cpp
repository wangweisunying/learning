// 543. Diameter of Binary Tree
// Easy

// 1160

// 67

// Favorite

// Share
// Given a binary tree, you need to compute the length of the diameter of t  he tree. 
// The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

// Example:
// Given a binary tree 
//           1
//          / \
//         2   3
//        / \     
//       4   5    
// Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

// Note: The length of path between two nodes is represented by the number of edges between them.



  //Definition for a binary tree node.
  struct TreeNode {
      int val;
      TreeNode *left;
      TreeNode *right;
      TreeNode(int x) : val(x), left(NULL), right(NULL) {}
  };
 
class Solution {
public:
    int res;
    int diameterOfBinaryTree(TreeNode* root) {
        if(root == NULL) return 0;
        helper(root);
        return res - 1;
    }
    int helper(TreeNode* root){
        if(root == NULL) return 0;
        int left = helper(root->left);
        int right = helper(root->right);
        res = max(left + right + 1, res);
        return max(left ,right) + 1;
    }
};



// /**
//  * Definition for a binary tree node.
//  * public class TreeNode {
//  *     int val;
//  *     TreeNode left;
//  *     TreeNode right;
//  *     TreeNode(int x) { val = x; }
//  * }
//  */
// class Solution {
//     int max;
//     public int diameterOfBinaryTree(TreeNode root) {
//         if(root == null) return 0;
//         helper(root);
//         return max - 1;
//     }
//     private int helper(TreeNode root){
//         if(root == null) return 0;
//         int left = helper(root.left);
//         int right = helper(root.right);
//         max = Math.max(max , left + right + 1);
//         return Math.max(left , right) + 1;
//     }
// }