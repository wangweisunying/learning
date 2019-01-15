// 366. Find Leaves of Binary Tree

// Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

 

// Example:

// Input: [1,2,3,4,5]
  
//           1
//          / \
//         2   3
//        / \     
//       4   5    

// Output: [[4,5,3],[2],[1]]
 

// Explanation:

// 1. Removing the leaves [4,5,3] would result in this tree:

//           1
//          / 
//         2          
 

// 2. Now removing the leaf [2] would result in this tree:

//           1          
 

// 3. Now removing the leaf [1] would result in the empty tree:

//           []       
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
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> list = new ArrayList();
        helper(list , root);
        return list;
    }
    private int helper(List<List<Integer>> list , TreeNode root){
        if(root == null) return 0;
        int res = Math.max(helper(list , root.left) , helper(list , root.right)) + 1;
        if(list.size() < res){
            list.add(new ArrayList(Arrays.asList(root.val)));
        }
        else{
            list.get(res - 1).add(root.val);
        }
        return res;
    }
}