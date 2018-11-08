// 106. Construct Binary Tree from Inorder and Postorder Traversal
// DescriptionHintsSubmissionsDiscussSolution
// Given inorder and postorder traversal of a tree, construct the binary tree.

// Note:
// You may assume that duplicates do not exist in the tree.

// For example, given

// inorder = [9,3,15,20,7]
// postorder = [9,15,7,20,3]
// Return the following binary tree:

//     3
//    / \
//   9  20
//     /  \
//    15   7

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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(postorder.length - 1 , 0 , inorder.length - 1 , inorder , postorder);
    }
    private TreeNode helper(int postIndex , int inSIndex , int inEIndex ,  int[] inorder, int[] postorder){
        if(postIndex < 0 || inSIndex > inEIndex){
            return null;
        }
        TreeNode root = new TreeNode(postorder[postIndex]);
        int index = - 1;
        for(int i = 0 ; i < inorder.length ; i++){
            if(inorder[i] == root.val){
                index = i;
                break;
            }
        }

        //postorder left  right  mid, 下一个 左子树起点应该是 postIndex - (len(rightSubtree)), 
        root.left = helper(postIndex - (inEIndex - index) - 1 , inSIndex , index - 1 , inorder, postorder);
        root.right = helper(postIndex - 1 , index + 1 , inEIndex , inorder, postorder);
        return root;
    }
}
