// 272. Closest Binary Search Tree Value II

// Share
// Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

// Note:

// Given target value is a floating point.
// You may assume k is always valid, that is: k ≤ total nodes.
// You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
// Example:

// Input: root = [4,2,5,1,3], target = 3.714286, and k = 2

//     4
//    / \
//   2   5
//  / \
// 1   3

// Output: [4,3]
// Follow up:
// Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 class Solution {
    List<Integer> res = new ArrayList();
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        if(root == null) return new ArrayList();
        closestKValues(root.left , target , k);
        if(res.size() < k){
          res.add(root.val);
        }
        else{
          if(Math.abs(target - res.get(0)) > Math.abs(target - root.val)){
            res.remove(0);
            res.add(root.val);
          }
          else return res;
        }
        closestKValues(root.right , target , k);
        return res;
    }
}











 
class Solution {
    List<Integer> res = new ArrayList();
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        closestKValues(root.left , target , k);
        if(res.size() < k) res.add(root.val);
        else{
            if(Math.abs(res.get(0) - target ) > Math.abs(root.val - target)){
                res.remove(0);
                res.add(root.val);
            }
            else return res;
        } 
        closestKValues(root.right , target , k);
        System.out.println(res);
        return res;
    }
}

// The idea is to compare the predecessors and successors of the closest node to the target, we can use two stacks to track the predecessors and successors,
//  then like what we do in merge sort, we compare and pick the closest one to the target and put it to the result list.

// As we know, inorder traversal gives us sorted predecessors, whereas reverse-inorder traversal gives us sorted successors.

// We can use iterative inorder traversal rather than recursion, but to keep the code clean, here is the recursion version.

public List<Integer> closestKValues(TreeNode root, double target, int k) {
  List<Integer> res = new ArrayList<>();

  Stack<Integer> s1 = new Stack<>(); // predecessors
  Stack<Integer> s2 = new Stack<>(); // successors

  inorder(root, target, false, s1);
  inorder(root, target, true, s2);
  
  while (k-- > 0) {
    if (s1.isEmpty())
      res.add(s2.pop());
    else if (s2.isEmpty())
      res.add(s1.pop());
    else if (Math.abs(s1.peek() - target) < Math.abs(s2.peek() - target))
      res.add(s1.pop());
    else
      res.add(s2.pop());
  }
  
  return res;
}

// inorder traversal
void inorder(TreeNode root, double target, boolean reverse, Stack<Integer> stack) {
  if (root == null) return;

  inorder(reverse ? root.right : root.left, target, reverse, stack);
  // early terminate, no need to traverse the whole tree
  if ((reverse && root.val <= target) || (!reverse && root.val > target)) return;
  // track the value of current node
  stack.push(root.val);
  inorder(reverse ? root.left : root.right, target, reverse, stack);
}
// The solution is simple;
// 1- Traverse in Order in the Tree and as long as the size of the ArrayList is less than K, add the root's value
// 2- Once you the size of the ArrayList reached K, start comparing first value of the ArrayList with the comming root's value.
//  if it's bigger than the comming root's value, remove the value from the first of the ArrayList and add the new value
// 3- Once the first value in the ArrayList(Index=0) is smaller than the comming root's val, return the ArrayList as the result.

 List<Integer> res = new ArrayList<>();
  public List<Integer> closestKValues(TreeNode root, double target, int k) {		 
		 if(root==null) return res;
		 closestKValues(root.left, target, k);
		 if(res.size()<k) res.add(root.val);
		 else{
			 if(Math.abs(target-res.get(0))>Math.abs(target-root.val)){
				 res.remove(0); res.add(root.val);
			 }else return res;
		 }
		 closestKValues(root.right, target, k);
    return res;
 } 
Thanks to @Abin here is the LinkedList version of the code: 

  LinkedList<Integer> res = new LinkedList<>();
  public List<Integer> closestKValues(TreeNode root, double target, int k) {		 
		 if(root==null) return res;
		 closestKValues(root.left, target, k);
		 if(res.size()<k) res.addLast(root.val);
		 else{
			 if(Math.abs(target-res.get(0))>Math.abs(target-root.val)){
				 res.removeFirst(); res.addLast(root.val);
			 }else return res;
		 }
		 closestKValues(root.right, target, k);
    return res;
 }


//native
//inorder and bs O(n) + O(logn) + O(k);


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
//     public List<Integer> closestKValues(TreeNode root, double target, int k) {
//         List<Integer> list = new ArrayList();
//         traverse(list , root);
//         int s = 0 , e = list.size() -  1;
//         while(s + 1 < e){
//             int mid = (s + e) / 2;
//             if(list.get(mid) < target){
//                 s = mid;
//             }
//             else{
//                 e = mid;
//             }
//         }
//         List<Integer> res = new ArrayList();
        
      
//         while( k > 0 && s >=0 && e < list.size()){
//             if(Math.abs(list.get(s) - target) < Math.abs(list.get(e) - target)){
//                 res.add(list.get(s--));
//                 k--;  
//             }
//             else{
//                 res.add(list.get(e++));
//                 k--;
//             }

//         }
//         while( k > 0 && s >=0){
//             res.add(list.get(s--));
//             k--;
//         }
//         while( k > 0 &&  e < list.size()){
//             res.add(list.get(e++));
//             k--;
//         }
//         return res;

//     }
//     void traverse(List<Integer> list , TreeNode root){
//         if(root == null){
//             return;
//         }
//         traverse(list, root.left);
//         list.add(root.val);
//         traverse(list, root.right);
        
//     }
// }