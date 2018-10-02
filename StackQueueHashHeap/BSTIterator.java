
// Binary Search Tree Iterator
// Design an iterator over a binary search tree with the following rules:

// Elements are visited in ascending order (i.e. an in-order traversal)
// next() and hasNext() queries run in O(1) time in average.
// Example
// For the following binary search tree, in-order traversal by using iterator is [1, 6, 10, 11, 12]

//    10
//  /    \
// 1      11
//  \       \
//   6       12
// Challenge
// Extra memory usage O(h), h is the height of the tree.

// Super Star: Extra memory usage O(1)
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
 * Example of iterate a tree:
 * BSTIterator iterator = new BSTIterator(root);
 * while (iterator.hasNext()) {
 *    TreeNode node = iterator.next();
 *    do something for node
 * } 
 */
//1 .push all the root left into stack
//2 .pop the stack top(smallest currently) and if top has the right son , push only this right son  into the stack , if this right son has left son  , push til met the leftmost son alwasy keep the smallest on top;
public class BSTIterator {
    /*
    * @param root: The root of binary tree.
    */
    Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack();
        while(root!= null){
            stack.push(root);
            root = root.left; 
        }
    }
    /*
     * @return: True if there has next node, or false
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /*
     * @return: return next node
     */
    public TreeNode next() {
        TreeNode cur = stack.pop();
        TreeNode res = cur;
        if(cur.right != null){
            cur = cur.right;
            // stack.push(cur);
            // while(cur.left != null){
            //     cur = cur.left;
            //     stack.push(cur);
            // } 
           
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            } 
        }
        return res;
    }
}