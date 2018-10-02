// Description
// Given a binary tree, return the preorder traversal of its nodes' values.

// Have you met this question in a real interview?  
// Example
// Given:

//     1
//    / \
//   2   3
//  / \
// 4   5
// return [1,2,4,5,3].

// Challenge
// Can you do it without recursion?

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
//stack

// pre order 

// 1如果根节点非空，将根节点加入到栈中。
// 2如果栈不空，弹出出栈顶节点，将其值加加入到数组中。
//   如果该节点的右子树不为空，将右子节点加入栈中。
//   如果左子节点不为空，将左子节点加入栈中。
// 3重复第二步，直到栈空。
// [1 , 2 , 3]
public class Solution {
    /**
     * @param root: A Tree
     * @return: Preorder in ArrayList which contains node values.
     */
    
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        List<Integer> res = new ArrayList();
        if(root == null){
            return res;
        }
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if(cur.right != null){
                stack.push(cur.right);
            }
            if(cur.left != null){
                stack.push(cur.left);  
            }
        }
        return res;
    }
}





public class Solution {
    /**
     * @param root: A Tree
     * @return: Preorder in ArrayList which contains node values.
     */
    Stack<TreeNode> stack = new Stack();
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        while(root != null){
            res.add(root.val);
            if(root.right != null){
                stack.push(root.right);
            }
            root = root.left;
        }
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();    
            while(cur != null){
                res.add(cur.val);
                if(cur.right != null ){
                    stack.push(cur.right);
                }
                cur = cur.left;
            }         
        }
    }
}



//rescursion
public class Solution {
    /**
     * @param root: A Tree
     * @return: Preorder in ArrayList which contains node values.
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        helper(res ,root);
        return res;
    }
    private void helper(List<Integer> res ,TreeNode root ){
        if(root == null){
            return;
        }
        res.add(root.val);
        helper(res, root.left);
        helper(res, root.right);
    }
}