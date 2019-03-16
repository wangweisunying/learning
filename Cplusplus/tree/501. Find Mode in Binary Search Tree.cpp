// 501. Find Mode in Binary Search Tree
// Easy

// 503

// 184

// Favorite

// Share
// Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

// Assume a BST is defined as follows:

// The left subtree of a node contains only nodes with keys less than or equal to the node's key.
// The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
// Both the left and right subtrees must also be binary search trees.
 

// For example:
// Given BST [1,null,2,2],

//    1
//     \
//      2
//     /
//    2
 

// return [2].

// Note: If a tree has more than one mode, you can return them in any order.

// Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).



//   Definition for a binary tree node.
#include <vector>
using namespace std;

struct TreeNode {
    int val;
     TreeNode *left;
     TreeNode *right;
     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
 
class Solution {
public:
    int maxCt = 0;
    int cur_max = 0;
    double cur_val = 1.5;
    vector<int> res;
    vector<int> findMode(TreeNode* root) {
        helper(root );
        return res;
    }
    void helper(TreeNode* root){
        if(root == NULL) return;
        helper(root->left);
        if(cur_val != root-> val){
            cur_max = 1;
            cur_val = root->val;  
        } 
        else{
            cur_max++;
        }
        if(cur_max > maxCt){
            res.clear();
            res.push_back(root->val);
            maxCt = cur_max;
        }
        if(cur_max == maxCt){
            if(res[res.size() - 1] != root->val){
                res.push_back(root -> val);
            }
        }
        
        helper(root->right );
    }
};



// class Solution {
//     public int[] findMode(TreeNode root) {
//         List<Integer> res = new ArrayList();
//         helper(root, res);
//         int[] tmp = new int[res.size()];
//         for(int i = 0 ; i < res.size() ; ++i) tmp[i] = res.get(i);
//         return tmp;
//     }
//     double preVal = 1.5;
//     int curCt = 0;
//     int maxCt = 0;
//     private void helper(TreeNode root , List<Integer> res){
//         if(root == null) return ;
//         helper(root.left, res);
//         if(root.val != preVal){
//             preVal = root.val;
//             curCt = 1;
//         }
//         else curCt++;
//         if(maxCt < curCt){
//             res.clear();
//             res.add(root.val);
//             maxCt = curCt;
//         }
//         if(maxCt == curCt){
//             if(res.get(res.size() - 1) != root.val){
//                 res.add(root.val);
//             }
//         }
//         helper(root.right, res);
//     }
// }

