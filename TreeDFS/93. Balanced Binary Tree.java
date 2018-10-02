
// 93. Balanced Binary Tree
// Given a binary tree, determine if it is height-balanced.

// For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

// Example
// Given binary tree A = {3,9,20,#,#,15,7}, B = {3,#,20,15,7}

// A)  3            B)    3 
//    / \                  \
//   9  20                 20
//     /  \                / \
//    15   7              15  7
// The binary tree A is a height-balanced binary tree, but B is not.

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
{1,2,3,4,#,5,6,7,8,9,10,11,12}
Output
true
Expected
false




class returnType{
    boolean isB;
    int depth;
    returnType(boolean isB , int depth){
        this.isB = isB;
        this.depth = depth;
    }

}
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }
        return helper(root).isB;
        
    }
    private returnType helper(TreeNode root){
        if(root == null){
            return new returnType(true , 0);
        }
        returnType left = isBalanced(root.left);
        returnType right = isBalanced(root.right);
        boolean now = left.isB && right.isB && (Math.abs(left.depth - right.depth) < 2);
        int depth = Math.max(left.depth , right.depth) + 1;
        return new returnType(now , depth);
    }
}




public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    int isNotBa = -1;
    public boolean isBalanced(TreeNode root) {
        
        return isNotBa != maxDepth(root);
    }
    public int maxDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        if(left == isNotBa || right == isNotBa){
            return isNotBa;
        }
        if(Math.abs(left - right) > 1){
            return isNotBa;
        }
        return Math.max(left,right) + 1;
    }
}