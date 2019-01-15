
// 7Serialize and Deserialize Binary Tree
// Design an algorithm and write code to serialize and deserialize a binary tree. Writing the tree to a file is called 'serialization' 
// and reading back from the file to reconstruct the exact same binary tree is 'deserialization'.

// Example
// An example of testdata: Binary tree {3,9,20,#,#,15,7}, denote the following structure:

//   3
//  / \
// 9  20
//   /  \
//  15   7
// Our data serialization use bfs traversal. This is just for when you got wrong answer and want to debug the input.

// You can use other method to do serializaiton and deserialization.

// Notice
// There is no limit of how you deserialize or serialize a binary tree, LintCode will take your output of serialize as the input of deserialize, it won't check the result of serialize.


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
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        String res = "";
        if(root == null){
            return res;
        }
        Queue<TreeNode> que = new LinkedList();
        que.offer(root);
        res += root.val + ",";
        while(!que.isEmpty()){
            int size = que.size();
            for(int i = 0 ; i < size ; i++){
                TreeNode cur = que.poll();
                if(cur.left == null){
                    res += "#,";    
                }
                else{
                    res += cur.left.val + ",";
                    que.offer(cur.left);
                }
                if(cur.right == null){
                    res += "#,";    
                }
                else{
                    res += cur.right.val + ",";
                    que.offer(cur.right);
                }

            }
        }
        return res.substring(0 , res.length() - 1);
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        if(data == null || data.length() == 0){
            return null;
        }
        String[] arr = data.split(",");
        int ct = 0;
        TreeNode root = new TreeNode(Integer.parseInt(arr[ct++]));

        Queue<TreeNode> que = new LinkedList();
        que.offer(root);
        
        while(!que.isEmpty() && ct < arr.length){
            int size = que.size();
            for(int i = 0 ; i < size ; i++){
                TreeNode cur = que.poll();
                if(!arr[ct].equals("#")){
                    int tmpl = Integer.parseInt(arr[ct]);
                    cur.left = new TreeNode(tmpl);
                    que.offer(cur.left);
                    ct++;
                }
                else{
                    ct++;
                } 
                if(!arr[ct].equals("#")){
                    int tmpl = Integer.parseInt(arr[ct]);
                    cur.right = new TreeNode(tmpl);
                    que.offer(cur.right);
                    ct++;
                }
                else{
                    ct++;
                } 
            }
        }
        return root;
    }
}