// Binary Tree Path Sum II
// Your are given a binary tree in which each node contains a value. 
// Design an algorithm to get all paths which sum to a given value.
//  The path does not need to start or end at the root or a leaf, but it must go in a straight line down.

// Example
// Given a binary tree:

//     1
//    / \
//   2   3
//  /   /
// 4   2
// for target = 6, return

// [
//   [2, 4],
//   [1, 3, 2]
// ]


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
    /*
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     */
//backtracking result
// [1]
// [1, 2]
// [1, 2, 4]
// [1, 3]
// [1, 3, 2]

// without backtracking preorder
// [1]
// [1, 2]
// [1, 2, 4]
// [1, 2, 4, 3]
// [1, 2, 4, 3, 2]

    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList();
        dfs(res , root , target , new ArrayList() , 0 );
        return res;
    }
    private void dfs(List<List<Integer>> res , TreeNode root , int target , List<Integer> cur , int level){
        if(root == null){
            return;
        }
        int tmp = target;
        cur.add(root.val);
        for (int i = level;i >= 0; i--) {
            tmp -= cur.get(i); // cur level to up till get 0 get the res 
            if (tmp == 0) {
                List<Integer> temp = new ArrayList<Integer>();
                for (int j = i; j <= level; ++j)
                temp.add(cur.get(j));
                res.add(temp);
            }
        }
        dfs(res , root.left , target , cur , level + 1 );
        dfs(res , root.right , target , cur  , level + 1);
        cur.remove(cur.size() - 1);
    }
}


public class Solution {
    /**
     * @param root the root of binary tree
     * @param target an integer
     * @return all valid paths
     */
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        // Write your code here
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        ArrayList<Integer> buffer = new ArrayList<Integer>();
        if (root == null)
            return results;
        findSum(root, target, buffer, 0, results);
        return results;
    }

    public void findSum(TreeNode head, int sum, ArrayList<Integer> buffer, int level, List<List<Integer>> results) {
        if (head == null) return;
        int tmp = sum;
        buffer.add(head.val);
        for (int i = level;i >= 0; i--) {
            tmp -= buffer.get(i);
            if (tmp == 0) {
                List<Integer> temp = new ArrayList<Integer>();
                for (int j = i; j <= level; ++j)
                    temp.add(buffer.get(j));
                results.add(temp);
            }
        }
        findSum(head.left, sum, buffer, level + 1, results);
        findSum(head.right, sum, buffer, level + 1, results);
        buffer.remove(buffer.size() - 1);
    }
}