
// 453. Flatten Binary Tree to Linked List
// Flatten a binary tree to a fake "linked list" in pre-order traversal.

// Here we use the right pointer in TreeNode as the next pointer in ListNode.

// Example
//               1
//                \
//      1          2
//     / \          \
//    2   5    =>    3
//   / \   \          \
//  3   4   6          4
//                      \
//                       5
//                        \
//                         6
// Challenge
// Do it in-place without any extra memory.

// Notice
// Don't forget to mark the left child of each node to null. Or you will get Time Limit Exceeded or Memory Limit Exceeded


/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }
        if(root.left!= null){
            flatten(root.left);
            TreeNode tmp = root.left;
            while(tmp.right != null){
                tmp = tmp.right;
            }
            if(root.right != null ){
                tmp.right = root.right;
            }
            root.right = root.left;
            root.left = null;
        }
        flatten(root.right);
    }
}



public class Solution {
    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten(TreeNode root) {
        helper(root);
    }
    public TreeNode helper(TreeNode root){
        if(root == null){
            return null ;
        }
        TreeNode leftLast = helper(root.left);
        TreeNode rightLast = helper(root.right);
        if(leftLast != null){
            leftLast.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        if(rightLast != null){
            return rightLast;
        }
        if(leftLast != null){
            return leftLast;
        }
        return root;
    }
}