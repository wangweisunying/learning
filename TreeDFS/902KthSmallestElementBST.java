// Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

// Example
// Given root = {1,#,2}, k = 2, return 2.

// Challenge
// What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? 
// How would you optimize the kthSmallest routine?

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
     * @param root: the given BST
     * @param k: the given k
     * @return: the kth smallest element in BST
     */
    int ct;
    int res;
    boolean found;
    public int kthSmallest(TreeNode root, int k) {
        ct = k;
        traverse(root);
        return res;
    }
    private void traverse(TreeNode root){
        if(found){
            return;
        }
        if(root == null){
            return;
        }
        traverse(root.left);
        ct--;
        if(ct == 0){
            res = root.val;
            found =
            return;
        }
        traverse(root.right);
    }
}




// stack 
public class Solution {
    /**
     * @param root: the given BST
     * @param k: the given k
     * @return: the kth smallest element in BST
     */

    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack();
        while(root!= null){
            stack.push(root);
            root = root.left;
        }
        
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            k--;
            if(k == 0){
                return cur.val;
            }
            if(cur.right!= null){
                cur = cur.right;
                while(cur!= null){
                    stack.push(cur);
                    cur = cur.left;
                }
            }
            
        }
        return -1;
    }
}

public class Solution {
    /**
     * @param root: A Tree
     * @return: Inorder in ArrayList which contains node values.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        Stack<TreeNode> stack = new Stack();
        //get all the left node
        while(root != null){
            stack.push(root);
            root = root.left;
        }
        
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop(); //pop the leftMost 
            res.add(cur.val);
            if(cur.right != null){  // is right exist then go to right node and find left most ;
                cur = cur.right;
                while(cur != null){
                    stack.push(cur); // push to path to the stack
                    cur = cur.left;
                }
            }
        }
        return res;
    }
}



















// recursion preorder
public class Solution {
    /**
     * @param root: the given BST
     * @param k: the given k
     * @return: the kth smallest element in BST
     */
    int res = Integer.MIN_VALUE;
    int ct;
    public int kthSmallest(TreeNode root, int k) {
        ct = k;
        traversal(root);
        return res;
    }
    private void traversal(TreeNode root){
        if(root == null){
            return;
        }
        traversal(root.left);
        ct--;
        if(ct == 0){
            res = root.val;
            return;
        }
        traversal(root.right);

    }
}


// iteration stack
public class Solution {
    /**
     * @param root: the given BST
     * @param k: the given k
     * @return: the kth smallest element in BST
     */

    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    
        for (int i = 0; i < k - 1; i++) {
            TreeNode node = stack.peek();
            
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
        }
        
        return stack.peek().val;
    }

}


public class Solution {
    /**
     * @param root: the given BST
     * @param k: the given k
     * @return: the kth smallest element in BST
     */

    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    
        for (int i = 0; i < k - 1; i++) {
            TreeNode cur = stack.pop();
            if(cur.right != null){
                cur = cur.right;
                while(cur != null){
                    stack.push(cur);
                    cur = cur.left;
                }
            }
        }
        
        return stack.peek().val;
    }

}
