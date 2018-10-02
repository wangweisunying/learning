
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


public class BSTIterator {
    /*

    * @param root: The root of binary tree.
    
    */
    private Stack<TreeNode> stack = new Stack();
    public BSTIterator(TreeNode root) {
        //let the pointer go to the leftmost
        while(root !=null ){
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
        TreeNode cur = stack.pop(); //pop the leftMost 
        TreeNode res = cur;
        if(cur.right != null){  // is right exist then go to right node and find left most ;
            cur = cur.right;
            while(cur != null){
                stack.push(cur); // push to path to the stack
                cur = cur.left;
            }

        }
        return res;
    }
}



public class BSTIterator {
    private Stack<TreeNode> stack = new Stack<>();
    
    // @param root: The root of binary tree.
    public BSTIterator(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    
    //@return: return next node
    public TreeNode next() {
        TreeNode curt = stack.peek();
        TreeNode node = curt;
        
        // move to the next node
        if (node.right == null) {
            node = stack.pop();
            while (!stack.isEmpty() && stack.peek().right == node) {
                node = stack.pop();
            }
        } else {
            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        
        return curt;
    }
}