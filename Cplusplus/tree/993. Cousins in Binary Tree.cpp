// 993. Cousins in Binary Tree
// Easy

// 32

// 3

// Favorite

// Share
// In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.

// Two nodes of a binary tree are cousins if they have the same depth, but have different parents.

// We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.

// Return true if and only if the nodes corresponding to the values x and y are cousins.

 

// Example 1:


// Input: root = [1,2,3,4], x = 4, y = 3
// Output: false
// Example 2:


// Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
// Output: true
// Example 3:



// Input: root = [1,2,3,null,4], x = 2, y = 3
// Output: false
 

// Note:

// The number of nodes in the tree will be between 2 and 100.
// Each node has a unique integer value from 1 to 100.



//  Definition for a binary tree node.
#include<vector>
using namespace std;
  struct TreeNode {
      int val;
      TreeNode *left;
      TreeNode *right;
      TreeNode(int x) : val(x), left(NULL), right(NULL) {}
  };
 
 
class Solution {
private:
	void helper(TreeNode* root, int x, vector<int>& res, int parent, int depth) {
		if (root == NULL) return;
		if (root->val == x) {
			res[0] = depth;
			res[1] = parent;
			return;
		}
		helper(root->left, x, res, root->val, depth + 1);
		helper(root->right, x, res, root->val, depth + 1);
	}

public:
	bool isCousins(TreeNode* root, int x, int y) {
		vector<int> f(2 , -1);
		vector<int> s(2 , -1);
		helper(root, x, f, -2, 0);
		helper(root, y, s, -2, 0);
		return f[0] == s[0] && f[1] != s[1];
	}
};





class Solution {
private:
	void helper(TreeNode* root, int x, vector<int> res, int parent, int depth) {
		if (root == NULL) return;
		if (root->val == x) {
			res[0] = depth;
			res[1] = parent;
			return;
		}
		helper(root->left, x, res, root->val, depth + 1);
		helper(root->right, x, res, root->val, depth + 1);
	}

public:
	bool isCousins(TreeNode* root, int x, int y) {
		vector<int> f(2 , -1);
		vector<int> s(2 , -1);
		helper(root, x, f, -1, 0);
		helper(root, y, s, -1, 0);
		bool res = f[0] == s[0] && f[1] != s[1];
		return res;
	}
};
//  bool isCousins(TreeNode* root, int x, int y)
//         {
//             struct Node
//             {
//                 TreeNode* node = nullptr;
//                 int index = 0;
//             };

//             // siblings if one is a left child and the other the immediate right child,
// 			// using index numbering 2*i+0,2*i+1 for left,right children
//             auto siblings = [](int i, int j)
//             {
//                 if (i % 2 == 0) return i + 1 == j;
//                 if (j % 2 == 0) return j + 1 == i;

//                 return false;
//             };

//             deque<Node>  q(1, { root, 0 });
//             while (!q.empty())
//             {
//                 int xindex = -1;
//                 int yindex = -1;

//                 auto n = (int)q.size();
//                 for (int i = 0; i < n; ++i)
//                 {
//                     auto[node, index] = q.front();
//                     q.pop_front();

//                     if (node->val == x)
//                         xindex = index;

//                     if (node->val == y)
//                         yindex = index;

//                     // x, y found on the same level -- early exit
//                     if (xindex != -1 && yindex != -1)
//                         return !siblings(xindex, yindex);

//                     // left child, don't need to push back null children
//                     if(node->left)
//                         q.push_back({ node->left, 2 * i + 0 });

//                     // right child (immediate right child), ignore if null
//                     if(node->right)
//                         q.push_back({ node->right, 2 * i + 1 });
//                 }
//             }

//             return false;
//         }

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// class Solution {
//     public boolean isCousins(TreeNode root, int x, int y) {
//         int[] first = new int[2];
//         int[] second = new int[2];
//         helper(root , x ,first , -1 , 0);
//         helper(root , y ,second , -1 , 0); 
//         return first[0] == second[0] && first[1] != second[1];       
//     }
//     private void helper(TreeNode root , int x , int[] res , int parent , int depth){
//         if(root == null) return ;
//         if(root.val == x){
//             res[0] = depth;
//             res[1] = parent;
//             return;
//         }
//         helper(root.left , x , res , root.val , depth + 1);
//         helper(root.right , x , res , root.val , depth + 1);
        
//     }
// }