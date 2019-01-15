// 741. Cherry Pickup
// DescriptionHintsSubmissionsDiscussSolution
// In a N x N grid representing a field of cherries, each cell is one of three possible integers.

// 0 means the cell is empty, so you can pass through;
// 1 means the cell contains a cherry, that you can pick up and pass through;
// -1 means the cell contains a thorn that blocks your way.
// Your task is to collect maximum number of cherries possible by following the rules below:

// Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
// After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
// When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
// If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.
// Example 1:
// Input: grid =
// [[0, 1, -1],
//  [1, 0, -1],
//  [1, 1,  1]]
// Output: 5
// Explanation: 
// The player started at (0, 0) and went down, down, right right to reach (2, 2).
// 4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
// Then, the player went left, up, up, left to return home, picking up one more cherry.
// The total number of cherries picked up is 5, and this is the maximum possible.
// Note:

// grid is an N by N 2D array, with 1 <= N <= 50.
// Each grid[i][j] is an integer in the set {-1, 0, 1}.
// It is guaranteed that grid[0][0] and grid[N-1][N-1] are not -1.



//memo search 最高境界
//memo[i][j][k] 记录 i , j 的状态 和 k , y = i + j - k 的状态
//因为需要记录是否到达了终点 所以需要多一个visited 来记录是否是valid 结果 ，是valid 才能算作canlidate
//由于一直往右下 ， 下面 和 右面的点 在同一个路线上永远不可能相交 所以 不需要回溯或者 改变值！！！
class Solution {
    class Node{
        boolean visited;
        int ct;
        Node(boolean visited , int ct){
            this.visited = visited;
            this.ct = ct;
        }
    }
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        Node[][][] memo = new Node[n][n][n]; 
        Node res = dfs(memo , 0 , 0 , 0 , grid , n);
        if(res.visited) return res.ct;
        return 0;
    }
    private Node dfs(Node[][][] memo , int i , int j  , int k , int[][] grid , int n){
        if(i == n - 1 && j == n - 1 && k == n - 1){
            return new Node(true , grid[n - 1][n - 1]);
        }
        if(memo[i][j][k] != null) return memo[i][j][k];
        int tmp = grid[i][j];
        if(i != k || j != i + j - k){
            tmp += grid[k][i + j - k]; 
        }
        // int tmp1 = grid[i][j];
        // grid[i][j] = 0;
        // int tmp2 = grid[k][i + j - k];
        // grid[k][i + j - k] = 0;
        int res = 0;
        boolean resV = false; 
        if(j + 1 < n && k + 1 < n && grid[i][j + 1] != -1 && grid[k + 1][i + j - k] != -1){
            Node tmpNode = dfs(memo , i , j + 1 , k + 1 , grid , n );
            if(tmpNode.visited)res = Math.max(res , tmpNode.ct);
            resV |=  tmpNode.visited;
        } 
        if(i + 1 < n && k + 1 < n && grid[i + 1][j] != -1 && grid[k + 1][i + j - k] != -1){
            Node tmpNode = dfs(memo , i + 1, j , k + 1 , grid , n );
            if(tmpNode.visited)res = Math.max(res , tmpNode.ct);
            resV |=  tmpNode.visited;
        } 
        if(j + 1 < n && i + j - k + 1 < n && grid[i][j + 1] != -1 && grid[k][i + j - k + 1] != -1){
            Node tmpNode = dfs(memo , i , j + 1 , k, grid , n );
            if(tmpNode.visited)res = Math.max(res , tmpNode.ct);
            resV |=  tmpNode.visited;
        }
        if(i + 1 < n && i + j - k + 1 < n && grid[i + 1][j] != -1 && grid[k][i + j - k + 1] != -1){
            Node tmpNode = dfs(memo , i + 1 , j , k, grid , n );
            if(tmpNode.visited)res = Math.max(res , tmpNode.ct);
            resV |=  tmpNode.visited;
        } 
        // grid[i][j] = tmp1;
        // grid[k][i + j - k] = tmp2;
        res += tmp;
        memo[i][j][k] = new Node(resV , res);
        return memo[i][j][k];
    }
}