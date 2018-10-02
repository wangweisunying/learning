// ClosestLeafinaBinaryTree
// Given a binary tree where every node has a unique value, and a target key k, find the value of the nearest leaf node to target k in the tree.
// If there is more than one answer, return to the leftmost.

// Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. 
// Also, a node is called a leaf if it has no children.

// Example
// Example 1:

// Given:
// root = {1, 3, 2}, k = 1
// Diagram of binary tree:
//           1
//          / \
//         3   2

// Return: 2 (or 3)

// Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.
// Example 2:

// Given:
// root = {1}, k = 1
// Return: 1

// Explanation: The nearest leaf node is the root node itself.
// Example 3:

// Given:
// root = {1,2,3,4,#,#,#,5,#,6}, k = 2
// Diagram of binary tree:
//              1
//             / \
//            2   3
//           /
//          4
//         /
//        5
//       /
//      6

// Return: 3
// Explanation: The leaf node with value 3 (and not the leaf node with value 6) is nearest to the node with value 2.
// Notice
// 1.root represents a binary tree with at least 1 node and at most 1000 nodes.
// 2.Every node has a unique node.val in range [1, 1000].
// 3.There exists some node in the given binary tree for which node.val == k.

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

 // 
public class Solution {
    /**
     * @param root: the root
     * @param k: an integer
     * @return: the value of the nearest leaf node to target k in the tree
     */
    class Node{
        Node left , right , parent;
        public int val;
        boolean visited;
        Node(int val){
            this.val = val;
        }
    }
    
    Node target;
    public int findClosestLeaf(TreeNode root, int k) {
        traverse(new Node(root) , root , k);
        Queue<Node> que = new LinkedList();
        que.offer(target);
        target.visited = true;
        int res = -1;
        
        while(!que.isEmpty()){
            int size = que.size();
            for(int i = 0 ; i < size ; i++){
                Node cur = que.poll();
                // System.out.println(cur.val);
                if(cur.left!= null && cur.left.val != -1 && !cur.left.visited){
                    que.offer(cur.left);
                    cur.left.visited = true;
                }
                if(cur.right!= null && cur.right.val != -1  && !cur.right.visited){
                    que.offer(cur.right);
                    cur.right.visited = true;
                }


                if(cur.parent!= null && cur.parent.val != -1 && !cur.parent.visited){
                    que.offer(cur.parent);
                    cur.parent.visited = true;
                }
                if(cur.left == null && cur.right == null){
                    return cur.val;
                }
            }
        }
        return res;
    }

    //construct binary tree wiht parent;
    private void traverse(Node node, TreeNode root ,int k){
        if(root == null){
            return;
        }
        if(root.left != null){
            Node left = new Node(root.left.val);
            left.parent = node;
            node.left = left;
            traverse(node.left , root.left , k);
        }
        if(root.right != null){
            Node right = new Node(root.right.val);
            right.parent = node;
            node.right = right;
            traverse(node.right , root.right , k);
        }
        if(root.val == k){
            target = Node;
        }
    }   
    
}
