// 897. Increasing Order Search Tree
// Easy

// 186

// 205

// Favorite

// Share
// Given a tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree, 
// and every node has no left child and only 1 right child.

// Example 1:
// Input: [5,3,6,2,4,null,8,1,null,null,null,7,9]

//        5
//       / \
//     3    6
//    / \    \
//   2   4    8
//  /        / \ 
// 1        7   9

// Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]

//  1
//   \
//    2
//     \
//      3
//       \
//        4
//         \
//          5
//           \
//            6
//             \
//              7
//               \
//                8
//                 \
//                  9  
// Note:

// The number of nodes in the given tree will be between 1 and 100.
// Each node will have a unique integer value from 0 to 1000.



//   Definition for a binary tree node.
struct TreeNode {
	int val;
	TreeNode *left;

	TreeNode *right;
	TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
class Solution {
public:
	TreeNode* cur;
	TreeNode* increasingBST(TreeNode* root) {
		TreeNode* res = new TreeNode(-1);
        cur = res;
		inorder(root);
		return res->right;
	}
	void inorder(TreeNode* root) {
		if (root == NULL) return;
		inorder(root->left);
		root->left = NULL;
		cur->right = root;
		cur = cur->right;
		inorder(root->right);
	}
};

// class Solution {
//     TreeNode cur;
//     public TreeNode increasingBST(TreeNode root) {
//         TreeNode ans = new TreeNode(0);
//         cur = ans;
//         inorder(root);
//         return ans.right;
//     }

//     public void inorder(TreeNode node) {
//         if (node == null) return;
//         inorder(node.left);
//         node.left = null;
//         cur.right = node;
//         cur = node;
//         inorder(node.right);
//     }
// }
class Solution {
    TreeNode* newroot, *curr;
public:
    TreeNode* increasingBST(TreeNode* root) {
        if (root == NULL) return NULL;
        increasingBST(root->left);
        
        if (newroot == NULL) { 
            newroot = new TreeNode(root->val);
            curr = newroot;
        }
        else {
            curr->right = new TreeNode(root->val);
            curr=curr->right;
        }
        
        increasingBST(root->right);
        return newroot;
    }
};


// java iteration 

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

//     public TreeNode increasingBST(TreeNode root) {
//         Stack<TreeNode> stack = new Stack();
//         while(root != null){
//             stack.push(root);
//             TreeNode x = root;
//             root = root.left;
//             x.left = null;
//         } 
//         TreeNode res = stack.peek();
//         TreeNode now = res;
//         while(!stack.isEmpty()){
//             TreeNode cur = stack.pop();
//             if(res != cur){
//                 now.left = null;
//                 now.right = cur;
//                 now = cur;
//             }
//             if(cur.right != null){
//                 cur = cur.right;
//                 while(cur != null){
//                     stack.push(cur);
//                     TreeNode x = cur;
//                     cur = cur.left;
//                     x.left = null;
//                 }
//             }
//         }
        
//         return res;
//     }
// }



// // recursion
// class Solution {

//     public TreeNode increasingBST(TreeNode root) {
//         List<TreeNode> list = new ArrayList();
//         helper(list ,root);
//         TreeNode res = list.get(0);
//         TreeNode now = res;
//         for(int i = 1 ; i< list.size() ; ++i){
//             now.right = list.get(i);
//             now = now.right; 
//         }
//         return res;
        
//     }
//     private void helper(List<TreeNode> list ,TreeNode root){
//         if(root == null) return ;
//         helper(list , root.left);
//         root.left = null;
//         list.add(root);
//         helper(list , root.right);
        
//     }
// }


// class Solution {
//     TreeNode cur;
//     public TreeNode increasingBST(TreeNode root) {
//         TreeNode ans = new TreeNode(0);
//         cur = ans;
//         inorder(root);
//         return ans.right;
//     }

//     public void inorder(TreeNode node) {
//         if (node == null) return;
//         inorder(node.left);
//         node.left = null;
//         cur.right = node;
//         cur = node;
//         inorder(node.right);
//     }
// }