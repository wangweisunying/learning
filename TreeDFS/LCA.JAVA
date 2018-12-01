//   4
//  / \
// 3   7
//    / \
//   5   6
// LCA(3, 5) = 4

// LCA(5, 6) = 7

// LCA(6, 7) = 4



/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    /*
     * @param root: The root of the tree
     * @param A: node in the tree
     * @param B: node in the tree
     * @return: The lowest common ancestor of A and B
     */
    class Res{
        boolean aHere , bHere;
        TreeNode node;
        Res(boolean aHere ,boolean bHere ,TreeNode node ){
            this.aHere = aHere;
            this.bHere = bHere;
            this.node = node;
        }
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        return LCA(null , root , A , B).node;
    }
    private Res LCA(TreeNode parent,TreeNode root, TreeNode A, TreeNode B){
        if(root == null){
            return new Res(false , false , null);
        }
        Res left = LCA( root, root.left , A , B );
        Res right = LCA(root , root.right , A , B );
        
        if(left.aHere && left.bHere){
            return (left.node!= A && left.node != B)? left : new Res(true , true , root);
        }
        if(right.aHere && right.bHere){
            return (right.node!= A && left.node != B)? right : new Res(true , true , root);
        }
        boolean ahere = left.aHere || right.aHere || A == root;
        boolean bhere = left.bHere || right.bHere || B == root;
        if(A == root || B == root){
            return new Res(ahere, bhere , parent);
        }
        return new Res(ahere, bhere , root);
    }
}