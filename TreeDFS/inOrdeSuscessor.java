// Inorder Successor in BST
// Given a binary search tree (See Definition) and a node in it, find the in-order successor of that node in the BST.

// If the given node has no in-order successor in the tree, return null.

// Example
// Given tree = [2,1] and node = 1:

//   2
//  /
// 1
// return node 2.

// Given tree = [2,1,3] and node = 2:

//   2
//  / \
// 1   3
// return node 3.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// {7,2,8,1,6,#,10,#,#,3,#,9,11,#,5,#,#,#,#,4}
// node with value 3

public class Solution {
    /*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */
    TreeNode res;
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null){
            return null;
        }
        if(root.val == p.val){
            if(root.right != null){
                return findmin(root.right); //右子数中最小的点 
            }
            return res;
        }
        if(root.val > p.val){
            res = root;
            return inorderSuccessor(root.left , p);
        }
        else{
            return inorderSuccessor(root.right , p);
        }
    }
    private TreeNode findmin(TreeNode root){
        if(root.left == null){
            return root;
        }
        return findmin(root.left);
    }
}

