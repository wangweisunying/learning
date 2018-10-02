// 596. Minimum Subtree
// Given a binary tree, find the subtree with minimum sum. Return the root of the subtree.

// Example
// Given a binary tree:

//      1
//    /   \
//  -5     2
//  / \   /  \
// 0   2 -4  -5 
// return the node 1.

// Notice
// LintCode will print the subtree which root is your return node.
// It's guaranteed that there is only one subtree with minimum sum and the given binary tree is not an empty tree.


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

class Node{
    TreeNode treeNode;
    int sum;
    Node(TreeNode treeNode ,int sum){
        this.treeNode = treeNode;
        this.sum = sum;
    }
}

public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the root of the minimum subtree
     */
    public TreeNode findSubtree(TreeNode root) {
        
        helper(root);
        return res;
    }
    TreeNode res;
    int i = Integer.MAX_VALUE;
    private Node helper(TreeNode root){
        if(root == null){
            return new Node(null , 0);
        }
        Node left = helper(root.left);
        Node right = helper(root.right);
        int sum = left.sum + right.sum + root.val;
        if(i > sum){
            i = sum;
            res = root;
        }
        return new Node(root , sum);
    }
}