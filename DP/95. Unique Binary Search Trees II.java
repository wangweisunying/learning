// 95. Unique Binary Search Trees II
// DescriptionHintsSubmissionsDiscussSolution
// Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

// Example:

// Input: 3
// Output:
// [
//   [1,null,3,2],
//   [3,2,null,1],
//   [3,1,null,null,2],
//   [2,1,3],
//   [1,null,2,null,3]
// ]
// Explanation:
// The above output corresponds to the 5 unique BST's shown below:

//    1         3     3      2      1
//     \       /     /      / \      \
//      3     2     1      1   3      2
//     /     /       \                 \
//    2     1         2                 3
 
   /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 //get all the valid bst node start from 1 to n ;
public List<TreeNode> generateTrees(int n) {
    if(n == 0){
        return new ArrayList();
    }
	return helper(1 , n);
}

private List<TreeNode> helper(int s, int e) {
    List<TreeNode> res = new ArrayList();
    //exit point s > e [5 , 5]  - left[ 5 , 4] null  right [6 , 5] null
    
    if(s > e){
        res.add(null);
        return res;
    }
    
    for(int i = s ; i <= e ; i++){
        List<TreeNode> left = helper(s , i - 1);
        List<TreeNode> right = helper(i + 1 , e);
        for(TreeNode leftSubtree : left){
            for(TreeNode rightSubtree : right){
                TreeNode node = new TreeNode(i);
                node.left = leftSubtree;
                node.right = rightSubtree;
                res.add(node);
            }
        }
        
    }
    return res;
}








// Your input
// 2
// [,],],[],,,,[,,[],[]
// Expected:
// [[],[1,null,2,null,4,3],,[],[],,,[,[],[]
//想清楚 每一种情况 ，
class Solution {
    public List<TreeNode> generateTrees(int n) {
        if(n == 0){
            return new ArrayList();
        }
        if(n == 1){
            return new ArrayList(Arrays.asList(new TreeNode(1)));    
        }
        List<TreeNode> res = new ArrayList();
        List<TreeNode> list = generateTrees(n - 1);
        for(TreeNode node : list){
            //case 1 add origin on the left;
            TreeNode curNode1 = new TreeNode(n);
            curNode1.left = copy(node);
            res.add(curNode1);


            //case 2 add to origin's on the right most;
            TreeNode curNode2 = new TreeNode(n);
            TreeNode parentNode = copy(node);
            TreeNode tmp = parentNode;
            int ct = 0;
            while(tmp.right != null){
                ct++;
                tmp = tmp.right;
            }
            tmp.right = curNode2;
            System.out.println(parentNode.right.val);
            res.add(parentNode);
            
            
             //case 3 insert to origin's all the position on the right branch;
            for(int i = 0 ; i < ct ; i++){
                TreeNode midNode = copy(node);
                TreeNode point = midNode;
                TreeNode curNode3 = new TreeNode(n);
                int j = i;
                while(j > 0){
                    point = point.right;
                    j--;
                }
                curNode3.left = point.right;
                point.right = curNode3; 
                res.add(midNode);
            }
                
            
        }
        return res;
    }
    public TreeNode copy(TreeNode node){
        if(node == null){
            return null;
        }
        TreeNode left = copy(node.left);
        TreeNode right = copy(node.right);
        
        TreeNode cur = new TreeNode(node.val);
        cur.left = left;
        cur.right = right;
        return cur;
    }
}


// This problem is a variant of the problem of Unique Binary Search Trees.

// I provided a solution along with explanation for the above problem, in the question "DP solution in 6 lines with explanation"

// It is intuitive to solve this problem by following the same algorithm. Here is the code in a divide-and-conquer style.
// bst val left must be in the left tree , val right must be in the right tree;
public List<TreeNode> generateTrees(int n) {
    if(n == 0){
        return new ArrayList();
    }
	return generateSubtrees(1, n);
}

private List<TreeNode> generateSubtrees(int s, int e) {
	List<TreeNode> res = new LinkedList<TreeNode>();
	if (s > e) {
		res.add(null); // empty tree
		return res;
	}

	for (int i = s; i <= e; ++i) {
		List<TreeNode> leftSubtrees = generateSubtrees(s, i - 1);  //bst 性质
		List<TreeNode> rightSubtrees = generateSubtrees(i + 1, e); //bst 性质

		for (TreeNode left : leftSubtrees) {
			for (TreeNode right : rightSubtrees) {
				TreeNode root = new TreeNode(i);
				root.left = left;
				root.right = right;
				res.add(root);
			}
		}
	}
	return res;
}