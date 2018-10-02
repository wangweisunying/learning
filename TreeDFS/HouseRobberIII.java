// The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

// Determine the maximum amount of money the thief can rob tonight without alerting the police.

// Example
//   3
//  / \
// 2   3
//  \   \ 
//   3   1
// Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

//     3
//    / \
//   4   5
//  / \   \ 
// 1   3   1
// Maximum amount of money the thief can rob = 4 + 5 = 9.

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


//dp

//dp[i][0]表示以i为根的子树不偷根节点能获得的最高价值，dp[i][1]表示以i为根的子树偷根节点能获得的最高价值
public class Solution {
    public int houseRobber3(TreeNode root) {
        int[] ans = dp(root);
        return Math.max(ans[0], ans[1]);
    }
    public int[] dp(TreeNode root) {
        if (root == null) {
            int[] now = new int[]{0, 0};
            return now;
        }
        int[] left = dp(root.left);
        int[] right = dp(root.right);
        int[] now = new int[2];
        now[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        now[1] = left[0] + right[0] + root.val;
        return now;
    }
}


//dfs
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: The maximum amount of money you can rob tonight
     */
    
class ReturnType{
    int withRoot;
    int withoutRoot;
    
    ReturnType(int withRoot , int withoutRoot){
        this.withRoot = withRoot;
        this.withoutRoot = withoutRoot;
    }
}
//divine and conquer  rob or not rob
    public int houseRobber3(TreeNode root) {
        return Math.max(helper(root).withRoot , helper(root).withoutRoot);
    }
    private ReturnType helper(TreeNode root){
        if(root == null){
            return new ReturnType(0 , 0); 
        }  
        ReturnType left = helper(root.left);
        ReturnType right = helper(root.right);

        return new ReturnType( root.val + left.withoutRoot + right.withoutRoot,
         Math.max(left.withRoot , left.withoutRoot) + Math.max(right.withRoot , right.withoutRoot)); 
    }
}