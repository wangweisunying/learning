// Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth (e.g., if you have a tree with depth D, you'll have D linked lists).

// Example
// Given binary tree:

//     1
//    / \
//   2   3
//  /
// 4
// return

// [
//   1->null,
//   2->3->null,
//   4->null
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
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    /**
     * @param root the root of binary tree
     * @return a lists of linked list
     */
    public List<ListNode> binaryTreeToLists(TreeNode root) {
        List<ListNode> res = new ArrayList();
        if(root == null){
            return res;
        }
        Queue<TreeNode> que = new LinkedList();
        que.offer(root);
        bfs(res , que);
        return res;
    }
    private void bfs(List<ListNode> res , Queue<TreeNode> que ){
        ListNode dummy = new ListNode(0);
        ListNode cur = null;
        while(!que.isEmpty()){
            dummy.next = null;
            cur = dummy;
            int size = que.size();
            for(int i = 0 ; i < size ; i ++){
                TreeNode tmp = que.poll();
                cur.next = new ListNode(tmp.val);
                cur = cur.next;
                if(tmp.left != null){
                    que.offer(tmp.left);
                }
                if(tmp.right != null){
                    que.offer(tmp.right);
                }
            }
            res.add(dummy.next);
        }
    }
}


public class Solution {
    /**
     * @param root the root of binary tree
     * @return a lists of linked list
     */
    public List<ListNode> binaryTreeToLists(TreeNode root) {
        List<ListNode> result = new ArrayList<ListNode>();
        
        if (root == null)
            return result;
            
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        
        ListNode dummy = new ListNode(0);
        ListNode lastNode = null;
        while (!queue.isEmpty()) {
            dummy.next = null;
            lastNode = dummy;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();
                lastNode.next = new ListNode(head.val);
                lastNode = lastNode.next;

                if (head.left != null)
                    queue.offer(head.left);
                if (head.right != null)
                    queue.offer(head.right);
            }
            result.add(dummy.next);
        }
        
        return result;
    }
}