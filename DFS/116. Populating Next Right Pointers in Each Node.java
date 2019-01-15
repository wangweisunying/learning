// 116. Populating Next Right Pointers in Each Node
// Medium

// 773

// 54

// Favorite

// Share
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
// You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
// Example:

// Given the following perfect binary tree,

//      1
//    /  \
//   2    3
//  / \  / \
// 4  5  6  7
// After calling your function, the tree should look like:

//      1 -> NULL
//    /  \
//   2 -> 3 -> NULL
//  / \  / \
// 4->5->6->7 -> NULL


/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */




 
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return ;
        Queue<TreeLinkNode> que = new LinkedList();
        que.offer(root);
        while(!que.isEmpty()){
            int size = que.size();
            TreeLinkNode pre = null;
            for(int i = 0 ; i < size ; i++){
                TreeLinkNode cur = que.poll();
                if(pre != null) pre.next = cur;
                if(cur.left != null){
                    que.offer(cur.left);
                    que.offer(cur.right);
                }
                
                pre = cur;
            }
            pre.next = null;
        }
    }
}