// 652. Find Duplicate Subtrees
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees,
//  you only need to return the root node of any one of them.

// Two trees are duplicate if they have the same structure with same node values.

// Example 1:

//         1
//        / \
//       2   3
//      /   / \
//     4   2   4
//        /
//       4
// The following are two duplicate subtrees:

//       2
//      /
//     4
// and

//     4
// Therefore, you need to return above trees' root in the form of a list.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


// dfs 用map记录 序列化节点的出现的次数 当次数 为2 时加入list
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> list = new ArrayList();
        Map<String , Integer> map = new HashMap();
        traverse(root , map , list);
        return list;
    }
    private String traverse(TreeNode root , Map<String , Integer> map ,List<TreeNode> list ){
        String res ="";
        if(root == null) return "#";
        res += root.val;
        String left = traverse(root.left  , map , list);
        String right = traverse(root.right , map , list);
        if(!left.equals(""))res += "_" + left;
    
        if(!right.equals(""))res += "_" + right;
   
        map.put(res , map.getOrDefault(res , 0) + 1);
        if(map.get(res) ==2 ) list.add(root);
        return res;
    }
}