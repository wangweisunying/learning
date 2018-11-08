// 105. Construct Binary Tree from Preorder and Inorder Traversal
// DescriptionHintsSubmissionsDiscussSolution
// Given preorder and inorder traversal of a tree, construct the binary tree.

// Note:
// You may assume that duplicates do not exist in the tree.

// For example, given

// preorder = [3,9,20,15,7]
// inorder = [9,3,15,20,7]
// Return the following binary tree:

//     3
//    / \
//   9  20
//     /  \
//    15   7
// Seen this question in a real interview before?  
// Difficulty:Medium
// Total Accepted:171.9K
// Total Submissions:463.2K
// Contributor:LeetCode
// Subscribe to see which companies asked this question.

// Related Topics 

1
/**
2
 * Definition for a binary tree node.
3
 * public class TreeNode {
4
 *     int val;
5
 *     TreeNode left;
6
 *     TreeNode right;
7
 *     TreeNode(int x) { val = x; }
8
 * }
9
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


// peroder first element is root , go through the inorder find that root index  , inorder[left] is left tree , right is right tree
// perorder left start is p + 1  , right start is p + (leftsubtreelength); this is subquestion  we use recusion! 
class Solution {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper( 0 , 0 , inorder.length - 1 ,  preorder , inorder);
    }
    private TreeNode helper(int pS , int inS , int inE ,int[] preorder, int[] inorder){
        if(pS >= preorder.length || inS > inE ){
            return null;
        }
        TreeNode root = new TreeNode(preorder[pS]);
        int index = -1;
        for(int i = inS ; i <= inE ; i++ ){
            if(inorder[i] == root.val){
                index = i;
                break;     
            }
        }
        root.left = helper(pS + 1 ,inS , index - 1 , preorder , inorder);
        root.right = helper(pS + index - inS + 1 , index + 1, inE , preorder ,inorder);
        return root;

    }

}