// Lowest Common Ancestor II
// Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.

// The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.

// The node has an extra attribute parent which point to the father of itself. The root's parent is null.

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
 * Definition of ParentTreeNode:
 * 
 * class ParentTreeNode {
 *     public ParentTreeNode parent, left, right;
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
        ParentTreeNode node;
        Res(boolean aHere ,boolean bHere ,ParentTreeNode node ){
            this.aHere = aHere;
            this.bHere = bHere;
            this.node = node;
        }
    }
    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B) {
        return LCA(root , A , B).node;
    }
    private Res LCA(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B){
        if(root == null){
            return new Res(false , false , null);
        }
        Res left = LCA(root.left , A , B );
        Res right = LCA(root.right , A , B );
        
        if(left.aHere && left.bHere){
            return left;
        }
        if(right.aHere && right.bHere){
            return right;
        }

        return new Res(left.aHere || right.aHere || A == root, left.bHere || right.bHere || B == root  , root);
        }
}