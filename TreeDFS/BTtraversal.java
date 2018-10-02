// pre order 

// 1如果根节点非空，将根节点加入到栈中。
// 2如果栈不空，弹出出栈顶节点，将其值加加入到数组中。
//   如果该节点的右子树不为空，将右子节点加入栈中。
//   如果左子节点不为空，将左子节点加入栈中。
// 3重复第二步，直到栈空。
// [1 , 2 , 3]
public List<Integer> preorderTraversal(TreeNode root) {
    Stack<TreeNode> stack = new Stack<TreeNode>();
    List<Integer> preorder = new ArrayList<Integer>();
    
    if (root == null) {
        return preorder;
    }
    
    stack.push(root); // [ 1]
    while (!stack.empty()) {
        TreeNode node = stack.pop();  // 1 out []    // 2 out  //  3 out  per order
        preorder.add(node.val); 
        if (node.right != null) {
            stack.push(node.right);  //[3]              
        }
        if (node.left != null) {
            stack.push(node.left); //[3,2]
        }
    }
    
    return preorder;
}

// in order 
//1 如果根节点非空，将根节点加入到栈中。
//2 如果栈不空，取栈顶元素（暂时不弹出），
//         如果左子树已访问过，或者左子树为空，则弹出栈顶节点，将其值加入数组，如有右子树，将右子节点加入栈中。
//         如果左子树不为空，则将左子节点加入栈中。
//3 重复第二步，直到栈空。
// 一开始从根节点开始入栈就是一路向左，将根节点以及所有的左子节点全都入栈。然后自底向上出栈过程中判断并将右子节点入栈，入栈之后这个节点出栈的时候便是左子树已访问了




public ArrayList<Integer> inorderTraversal(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    ArrayList<Integer> result = new ArrayList<>();
    
    while (root != null) {
        stack.push(root);
        root = root.left;
    }

    while (!stack.isEmpty()) {
        TreeNode node = stack.peek();
        result.add(node.val);
        
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
    return result;
}






//other solution 
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