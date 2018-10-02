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

public class Solution {
    /**
     * @param root: A Tree
     * @return: Inorder in ArrayList which contains node values.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        List<Integer> res = new ArrayList();
        if(root == null){
            return res;
        }
        while(root != null){
            stack.push(root);
            root = root.left;
        }
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if(cur.right != null){
                cur = cur.right;
                while(cur != null){
                    stack.push(cur);
                    cur = cur.left;
                }
            }
        }
        return res;
    }
}





//recursion
public class Solution {
    /**
     * @param root: A Tree
     * @return: Inorder in ArrayList which contains node values.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        helper(res , root);
        return res;
    }
    private void helper(List<Integer> res , TreeNode root){
        if(root == null){
            return;
        }
        helper(res , root.left);
        res.add(root.val);
        helper(res , root.right);
    }
}