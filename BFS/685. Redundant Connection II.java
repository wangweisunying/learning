// // 685. Redundant Connection II
// // DescriptionHintsSubmissionsDiscussSolution
// // In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, 
// plus every node has exactly one parent, except for the root node which has no parents.

// // The given input is a directed graph that started as a rooted tree with N nodes
//  (with distinct values 1, 2, ..., N), with one additional directed edge added. 
//  The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

// // The resulting graph is given as a 2D-array of edges. Each element of edges is a pair
// //  [u, v] that represents a directed edge connecting nodes u and v, where u is a parent of child v.

// // Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. 
// If there are multiple answers, return the answer that occurs last in the given 2D-array.

// // Example 1:
// // Input: [[1,2], [1,3], [2,3]]
// Output: [2,3]
// Explanation: The given directed graph will be like this:
//   1
//  / \
// v   v
// 2-->3
// Example 2:
// Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
// Output: [4,1]
// Explanation: The given directed graph will be like this:
// 5 <- 1 -> 2
//      ^    |
//      |    v
//      4 <- 3
// Note:
// The size of the input 2D-array will be between 3 and 1000.
// Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.

// There are two cases for the tree structure to be invalid.
// 1) A node having two parents;
//    including corner case: e.g. [[4,2],[1,5],[5,2],[5,3],[2,4]]
// 2) A circle exists
// If we can remove exactly 1 edge to achieve the tree structure, a single node can have at most two parents. So my solution works in two steps.

// 1) Check whether there is a node having two parents. 
//     If so, store them as candidates A and B, and set the second edge invalid. 
// 2) Perform normal union find. 
//     If the tree is now valid 
//            simply return candidate B
//     else if candidates not existing 
//            we find a circle, return current edge; 
//     else 
//            remove candidate A instead of B.

class Solution {
    int[] f;
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] can1 = new int[]{-1 , - 1};
        int[] can2 = new int[]{-1 , - 1};
        int n = edges.length;
        int[] parent = new int[n + 1];
        for(int[] edge : edges){
            if(parent[edge[1]] == 0){
                parent[edge[1]] = edge[0];
            }
            else{
                //不是之前的 就是现在这个 ， 因为 就只加了一条边 所以只有两个候选
                can1 = new int[]{parent[edge[1]] ,edge[1]};
                can2 = new int[]{edge[0] ,edge[1]};
                //将can2这条边先去掉, 用后面判断反推
                edge[1] = 0; 
            }
        }
        
        f= new int[n + 1];
        for(int i =0  ; i <= n ; i++){
            f[i] = i;
        }
        
        for(int[] edge : edges){
            if(edge[1] == 0){
                continue;
            }
            int father = parent[edge[1]];
            int son = edge[1];
            
            if(find(father) == son){
                //case1 : 图中只有环 没有出现2个父亲的节点 返回 该边
                if(can1[0] == -1){
                    return edge;
                }
                //如果出现这个边上的节点和节点的一个候选父亲构成环 返回该条边
                return can1;
            }
            union(son , father);
            // f[son] = father; 

        }
        // 如果2个parent不构成环 ，返回第二候选人
        return can2;
    }
    private int find(int a){
        if(f[a] == a){
            return a;
        }
        return f[a] = find(f[a]);
    }
    private void union(int a , int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA != rootB){
            f[rootA] = rootB;
        }
    }
}










class Solution {
    int[] f;
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] can1 = new int[]{-1 , - 1};
        int[] can2 = new int[]{-1 , - 1};
        int n = edges.length;
        int[] parent = new int[n + 1];
        for(int[] edge : edges){
            if(parent[edge[1]] == 0){
                parent[edge[1]] = edge[0];
            }
            else{
                can1 = new int[]{parent[edge[1]] ,edge[1]};
                can2 = new int[]{edge[0] ,edge[1]};
                //
                edge[1] = 0; 
            }
        }
        
        f= new int[n + 1];
        for(int i =0  ; i <= n ; i++){
            f[i] = i;
        }
        
        for(int[] edge : edges){
            if(edge[1] == 0){
                continue;
            }
            int father = edge[0];
            int son = edge[1];
            if(find(father) == son){
                if(can1[0] == -1){
                    return edge;
                }
                return can1;
            }
            union(son , father);
            // f[son] = father; 

        }
        return can2;
    }
    private int find(int a){
        if(f[a] == a){
            return a;
        }
        return f[a] = find(f[a]);
    }
    private union(int a , int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA != rootB){
            f[rootA] = rootB;
        }
    }
}


















class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] can1 = {-1, -1};
        int[] can2 = {-1, -1};
        int[] parent = new int[edges.length + 1];
        for (int i = 0; i < edges.length; i++) {
            if (parent[edges[i][1]] == 0) {
                parent[edges[i][1]] = edges[i][0];
            } else {
                can2 = new int[] {edges[i][0], edges[i][1]};
                can1 = new int[] {parent[edges[i][1]], edges[i][1]};
                edges[i][1] = 0; 
            }
        }
        for (int i = 0; i < edges.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            if (edges[i][1] == 0) {
                continue;
            }
            int child = edges[i][1], father = edges[i][0];
            if (root(parent, father) == child) {
                if (can1[0] == -1) {
                    return edges[i];
                }
                return can1;
            }
            parent[child] = father;
        }
        return can2;
    }
    
    int root(int[] parent, int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }   
        return i;
    }
}