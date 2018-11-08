// 117. Populating Next Right Pointers in Each Node II
// DescriptionHintsSubmissionsDiscussSolution
// Given a binary tree

// struct TreeLinkNode {
//   TreeLinkNode *left;
//   TreeLinkNode *right;
//   TreeLinkNode *next;
// }
// Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

// Initially, all next pointers are set to NULL.

// Note:

// You may only use constant extra space.
// Recursive approach is fine, implicit stack space does not count as extra space for this problem.
// Example:

// Given the following binary tree,

//      1
//    /  \
//   2    3
//  / \    \
// 4   5    7
// After calling your function, the tree should look like:

//      1 -> NULL
//    /  \
//   2 -> 3 -> NULL
//  / \    \
// 4-> 5 -> 7 -> NULL



public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null){
            return;
        }
        Queue<TreeLinkNode> que = new LinkedList();
        que.offer(root);
        while(!que.isEmpty()){
            int size = que.size();
            TreeLinkNode right = null;
            for(int i = 0 ; i < size ; i++){
                TreeLinkNode cur = que.poll();
                cur.next = right;
                right = cur;
                if(cur.right != null){
                    que.offer(cur.right);
                }
                if(cur.left != null){
                    que.offer(cur.left);
                }
            }
        }
    }
}
