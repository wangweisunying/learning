// 113. Path Sum II
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

// Note: A leaf is a node with no children.

// Example:

// Given the below binary tree and sum = 22,

//       5
//      / \
//     4   8
//    /   / \
//   11  13  4
//  /  \    / \
// 7    2  5   1
// Return:

// [
//    [5,4,11,2],
//    [5,8,4,5]
// ]


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList();
        dfs(res , new ArrayList() , sum , root);
        return res; 
    }
    private void dfs(List<List<Integer>> res ,List<Integer> cur , int rest , TreeNode root){
        if(root == null){
            return;
        }
        if(rest == root.val && root.left == null && root.right == null){
            
            cur.add(root.val); // pay attention here
            res.add(new ArrayList(cur));
            cur.remove(cur.size() - 1);
            return;
        }
        
        cur.add(root.val);
        dfs(res , cur , rest - root.val , root.left);
        dfs(res , cur , rest - root.val , root.right);
        cur.remove(cur.size() - 1);

    }
}
