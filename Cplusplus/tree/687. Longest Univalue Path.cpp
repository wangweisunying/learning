// 687. Longest Univalue Path
// Share
// Given a binary tree, find the length of the longest path where each node in the path has the same value. 
// This path may or may not pass through the root.

// Note: The length of path between two nodes is represented by the number of edges between them.

// Example 1:

// Input:

//               5
//              / \
//             4   5
//            / \   \
//           1   1   5
// Output:

// 2
// Example 2:

// Input:

//               1
//              / \
//             4   5
//            / \   \
//           4   4   5
// Output:

// 2
// Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.



//   Definition for a binary tree node.
struct TreeNode {
     int val;
     TreeNode *left;
     TreeNode *right;
     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
// [1,4,5,4,4,5]
// Output
// 0
// Expected
// 2
// [5,4,5,1,1,5]


//定义一个全局变量记录结果， 子函数返回的是包括当前节点最大节点数 ， 同时需要判断 左中右都相同的情况
class Solution {
public:
    int res;
    int longestUnivaluePath(TreeNode* root) {
        if(root == NULL) return 0;
        helper(root);
        return res - 1;
    }
    int helper(TreeNode* root){
        if(root == NULL) return 0;
        int curMax = 1;
        int curRes = 1;
        int left = helper(root->left);
        // curRes = max(left , curRes);
        if(root-> left != NULL && root->left->val == root->val) curRes = max(curRes , left + 1);
        int right = helper(root->right);
        // curRes = max(right , curRes);
        if(root-> right != NULL && root->right->val == root->val) curRes = max(curRes , right + 1);
        res = max(curRes , res); 
        if(root-> right != NULL && root-> left != NULL && root->right->val == root->left->val && root->right->val == root->val){
            res = max(res , left + right + 1);
        }
        return curRes;
    }
    
};




// class Solution {
//     int ans;
//     public int longestUnivaluePath(TreeNode root) {
//         ans = 0;
//         arrowLength(root);
//         return ans;
//     }
//     public int arrowLength(TreeNode node) {
//         if (node == null) return 0;
//         int left = arrowLength(node.left)
//         int right = arrowLength(node.right);
//         int arrowLeft = 0, arrowRight = 0;
//         if (node.left != null && node.left.val == node.val) {
//             arrowLeft += left + 1;
//         }
//         if (node.right != null && node.right.val == node.val) {
//             arrowRight += right + 1;
//         }
//         ans = Math.max(ans, arrowLeft + arrowRight);
//         return Math.max(arrowLeft, arrowRight);
//     }
// }