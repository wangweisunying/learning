// 655. Print Binary Tree
// DescriptionHintsSubmissionsDiscussSolution
// Print a binary tree in an m*n 2D string array following these rules:

// The row number m should be equal to the height of the given binary tree.
// The column number n should always be an odd number.
// The root node's value (in string format) should be put in the exactly middle of the first row it can be put. 
// The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part).
//  You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part.
//   The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not,
//    you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree.
//     However, if two subtrees are none, then you don't need to leave space for both of them.
// Each unused space should contain an empty string "".
// Print the subtrees following the same rules.
// Example 1:
// Input:
//      1
//     /
//    2
// Output:
// [["", "1", ""],
//  ["2", "", ""]]
// Example 2:
// Input:
//      1
//     / \
//    2   3
//     \
//      4
// Output:
// [["", "", "", "1", "", "", ""],
//  ["", "2", "", "", "", "3", ""],
//  ["", "", "4", "", "", "", ""]]
// Example 3:
// Input:
//       1
//      / \
//     2   5
//    / 
//   3 
//  / 
// 4 
// Output:

// [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
//  ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
//  ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
//  ["4", "",  "1", "",  "2", "", "3", "",  "5",  "",  "6",  "",  "7", "", "8"]]
// Note: The height of binary tree is in the range of [1, 10].


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
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> res = new ArrayList();
        if(root == null) return res;
        int height = getHeight(root);
        int size = (1 << height) - 1;
        for(int i = 0 ; i < height ; i++){
            List<String> cur = new ArrayList();
            for(int j = 0 ; j < size ; j++){
                cur.add("");
            }
            res.add(cur);
        }
        dfs(res , root , size / 2  , (size + 1) / 4 , 0);
        return res; 
    }
    private void dfs( List<List<String>> res , TreeNode root , int curPos , int offset , int level){
        if(root == null) return;
        res.get(level).set(curPos , "" + root.val);
        dfs(res , root.left , curPos - offset , offset / 2  , level + 1);
        dfs(res , root.right , curPos + offset , offset / 2 , level + 1);
    }
    private int getHeight(TreeNode root){
        if(root == null) return 0;
        return Math.max(getHeight(root.left) , getHeight(root.right)) + 1;
    }
}





/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */























//  [1,2,5,3]
// Your answer
// [["","1",""],["","2",""]]
// dfs 
class Solution {
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> res = new ArrayList();
        if(root == null) return res;
        int height = getHeight(root);
        int len = (1<<height) - 1;
        for(int i = 0 ; i < height ; i++){
            List<String> list = new ArrayList();
            for(int j = 0 ; j < len ; j++) list.add("");
            res.add(list);
        }
        traverse(root , res , 0 , len / 2  , (len + 1) / 2);
        return res;
    }
    private void traverse(TreeNode root , List<List<String>> res , int curLevel , int curIndex , int offSet){
        if(root == null) return;
        res.get(curLevel).set(curIndex , root.val + "");
        offSet /= 2;
        traverse(root.left , res, curLevel + 1 , curIndex - offSet , len);
        traverse(root.right , res , curLevel + 1 , curIndex + offSet , len);
    }
    private int getHeight(TreeNode root){
        if(root == null) return 0;
        return Math.max(getHeight(root.left) , getHeight(root.right)) + 1;
    }
}