class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        if(root == null){
            return res;
        }
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode cur = que.poll();
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



// 反向中序遍历 然后reverse list 画个图就清楚了
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        if(root == null){
            return res;
        }
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if(cur.left != null){
                stack.push(cur.left);
            }
            if(cur.right != null){
                stack.push(cur.right);
            }
        }       
        Collections.reverse(res); 
        return res;
    }
}






//inorder
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        if (root==null) return res;

        Stack<TreeNode> stack=new Stack<>();
        TreeNode curr=root;

        while(curr!=null || !stack.isEmpty()){
            while (curr!=null){
                stack.push(curr);
                curr=curr.left;
            }
            curr=stack.pop();
            res.add(curr.val);
            curr=curr.right;
        }
        return res;
    }

//preorder
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode current = stack.pop();
            list.add(current.val);
            if(current.right!=null) {
               stack.push(current.right);
            }
            if(current.left!=null) {
              stack.push(current.left);
            }
        }
        return list;
    }

//postorder
      public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            list.add(0,curr.val);
            if(curr.left!=null) {
              stack.push(curr.left);
            }
            if(curr.right!=null) {
               stack.push(curr.right); 
            }
        }
        return list;
    }
