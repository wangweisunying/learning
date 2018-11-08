// 329. Longest Increasing Path in a Matrix
// Given an integer matrix, find the length of the longest increasing path.

// From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

// Example 1:

// Input: nums = 
// [
//   [9,9,4],
//   [6,6,8],
//   [2,1,1]
// ] 
// Output: 4 
// Explanation: The longest increasing path is [1, 2, 6, 9].
// Example 2:

// Input: nums = 
// [
//   [3,4,5],
//   [3,2,6],
//   [2,2,1]
// ] 
// Output: 4 
// Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.




// memo dfs O(MN)
class Solution {
    int m , n;
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0 ){
            return 0;
        }
        m = matrix.length;
        n = matrix[0].length;
        int[][]memo = new int[m][n]; 
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
               int tmp = dfs(i , j , matrix , memo);
               max = Math.max(tmp , max);
               System.out.println(tmp);
               
            }
        }
        System.out.println(max);
        return max;
    }
    int[] deltaX = {1 , -1 , 0 , 0} ;
    int[] deltaY = {0 , 0 , 1 , -1} ;
    int max = 0;
    public int dfs(int i , int j , int[][] matrix , int[][]memo ){
        if(memo[i][j] > 0){
            return memo[i][j];
        }
        int tmp = 0;
        for(int k = 0 ; k < 4 ; k++){
            int x = i + deltaX[k];
            int y = j + deltaY[k];
            if(outBound(x , y)){
                continue;
            }
            if(matrix[x][y] <= matrix[i][j]){
                continue;
            }
            tmp = Math.max(dfs(x , y , matrix, memo) , tmp);
        } 
        memo[i][j] = tmp + 1;
        return memo[i][j];
    }
    private boolean outBound(int x ,int y){
        if(x < 0 || y < 0 || x>= m || y >= n){
            return true;
        }
        return false;
    }
}



    int m , n;
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0 ){
            return 0;
        }
        m = matrix.length;
        n = matrix[0].length;
        boolean[][] visited  = new boolean[m][n];
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                visited[i][j] = true;
                dfs(i , j , matrix , 1 , visited);
                visited[i][j] = false;
            }
        }
        return max;
    }
    int[] deltaX = {1 , -1 , 0 , 0} ;
    int[] deltaY = {0 , 0 , 1 , -1} ;
    int max = 0;
    public void dfs(int i , int j , int[][] matrix , int len , boolean[][] visited){
        max = Math.max(max , len);
        for(int k = 0 ; k < 4 ; k++){
            int x = i + deltaX[k];
            int y = j + deltaY[k];
            if(outBound(x , y)){
                continue;
            }
            if(matrix[x][y] <= matrix[i][j]){
                continue;
            }
            if(visited[x][y]){
                continue;
            }
            visited[x][y] = true;
            dfs(x , y , matrix , len + 1 , visited);
            visited[x][y] = false;
        }
    

    }
    private boolean outBound(int x ,int y){
        if(x < 0 || y < 0 || x>= m || y >= n){
            return true;
        }
        return false;
    }