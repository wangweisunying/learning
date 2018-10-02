// Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

// Example
// Given root = {1}, target = 0.000000, k = 1, return [1].

// Challenge
// Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?


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

public class Solution {
    /**
     * @param root: the given BST
     * @param target: the given target
     * @param k: the given k
     * @return: k values in the BST that are closest to the target
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> list = new ArrayList();
        traverse(root , list);
        List<Integer> res = new ArrayList();
        int i = 0 , j = list.size() - 1 , left = - 1;
        while(i + 1 < j){
            int mid = (i + j) / 2;
            if(list.get(mid) == target){
                j = mid;
            }
            if(list.get(mid) < target){
                i = mid;
            }
            else{
                j = mid;
            }
        }
        //find left bound 
        if(list.get(i) < target){
            left = i;
        }
        if(list.get(j) < target){
            left = j;
        }
        
        int l = left , r = left + 1;
        for(int g = 0 ; g < k ; g++){
            if(isLeftCloser(list , target , l , r)){
                res.add(list.get(l--));
            }
            else{
                res.add(list.get(r++));
            }

        }
        return res;
    }
    private boolean isLeftCloser(List<Integer> list , double target , int left , int right){
        if(left < 0){
            return false;
        }
        if(right >= list.size()){
            return true;
        }
        if(target - list.get(left) <= list.get(right) - target){
            return true;
        }
        return false;

    }
    private void traverse(TreeNode root, List<Integer> list){
        if(root == null){
            return;
        }
        traverse(root.left);
        list.add(root.val);
        traverse(root.right); 
    }
}