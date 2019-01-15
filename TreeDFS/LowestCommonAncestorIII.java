
// Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.
// The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.
// Return null if LCA does not exist.

// Example
// For the following binary tree:

//   4
//  / \
// 3   7
//    / \
//   5   6
// LCA(3, 5) = 4

// LCA(5, 6) = 7

// LCA(6, 7) = 7
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

 //正向思维 找到就返回，根据left 和right 的出现情况来判断 lCA 的位置

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null ) return null;
        if(root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null) return root;
        return left == null ? right : left; // cover all null;
    }
}
























public class Solution {
    /*
     * @param root: The root of the binary tree.
     * @param A: A TreeNode
     * @param B: A TreeNode
     * @return: Return the LCA of the two nodes.
     */

    class ReturnType{
        boolean Ahere , Bhere;
        TreeNode node;
        ReturnType( TreeNode node , boolean Ahere ,boolean Bhere ){
            this.Ahere = Ahere;
            this.Bhere = Bhere;
            this.node = node;
        }
    }
    TreeNode res;
    boolean found;
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        helper(root , A , B);
        return res;
    }
    private ReturnType helper(TreeNode root, TreeNode A, TreeNode B){
        if(found){
            return new ReturnType(null , false , false);
        }
        if(root == null){
            return new ReturnType(null , false , false);
        }
        ReturnType left = helper(root.left , A , B);
        ReturnType right = helper(root.right , A , B);
        boolean isA = (root.val == A.val) || left.Ahere || right.Ahere;
        boolean isB = (root.val == B.val) || left.Bhere || right.Bhere;
        if(isA && isB && !found){
            res = root;
            found = true;
        }
        return new ReturnType(root , isA , isB);
    }
}
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
    /*
     * @param root: The root of the binary tree.
     * @param A: A TreeNode
     * @param B: A TreeNode
     * @return: Return the LCA of the two nodes.
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {       
        ReturnType res = helper(root , A , B);
        if(res.Ahere && res.Bhere){
            return res.node;
        }
        return null;
    }
    private ReturnType helper(TreeNode root, TreeNode A, TreeNode B){
        if(root == null){
            return new ReturnType(null, false , false);
        }
        ReturnType left = helper(root.left , A , B);
        ReturnType right = helper(root.right , A , B);

        boolean Ahere = left.Ahere || right.Ahere || root == A;
        boolean Bhere = left.Bhere || right.Bhere || root == B;
        
        if(root == A || root == B){
            return new ReturnType(root , Ahere , Bhere);
        }
        if( left.node != null && right.node != null){
            return new ReturnType(root , Ahere ,Bhere);
        }
        if( left.node != null){
            return new ReturnType(left.node , Ahere , Bhere);
        }
        if( right.node != null){
            return new ReturnType(right.node , Ahere ,Bhere);
        }

        return new ReturnType(null , Ahere ,Bhere);

    }

    class ReturnType{
        TreeNode node;
        boolean Ahere;
        boolean Bhere;
        ReturnType(TreeNode node , boolean Ahere , boolean Bhere){
            this.node = node;
            this.Ahere = Ahere;
            this.Bhere = Bhere;
        }
    }
}