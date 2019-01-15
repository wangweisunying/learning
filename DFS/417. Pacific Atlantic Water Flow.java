// 417. Pacific Atlantic Water Flow

// Share
// Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, 
// the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

// Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

// Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

// Note:
// The order of returned grid coordinates does not matter.
// Both m and n are less than 150.
// Example:

// Given the following 5x5 matrix:

//   Pacific ~   ~   ~   ~   ~ 
//        ~  1   2   2   3  (5) *
//        ~  3   2   3  (4) (4) *
//        ~  2   4  (5)  3   1  *
//        ~ (6) (7)  1   4   5  *
//        ~ (5)  1   1   2   4  *
//           *   *   *   *   * Atlantic

// Return:

// [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).


//从外向内 扩展 ，直接做会导致先后问题
class Solution {
     int m , n ;
    int[] deltaX;
    int[] deltaY;
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList();
        if(matrix == null || matrix.length == 0) return res;
        m = matrix.length;
        n = matrix[0].length;
        deltaX = new int[]{1 , -1 , 0 , 0};
        deltaY = new int[]{0 , 0 , -1 , 1};
        boolean[][] alt = new boolean[m][n];
        boolean[][] pac = new boolean[m][n];
        boolean[][] altV = new boolean[m][n];
        boolean[][] pacV = new boolean[m][n];
        for(int i = 0 ; i < m ; i++){
            alt[i][0] = true;
            pac[i][n - 1] = true;
            altV[i][0] = true;
            pacV[i][n - 1] = true;
            dfs(matrix , i , 0 , alt , altV);
            dfs(matrix , i , n - 1 , pac , pacV);
        }
        for(int i = 0 ; i < n ; i++){
            alt[0][i] = true;
            pac[m - 1][i] = true;
            altV[0][i] = true;
            pacV[m - 1][i] = true;
            dfs(matrix , 0 , i , alt , altV);
            dfs(matrix , m - 1 , i , pac , pacV);
        }
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++ ){
                if(alt[i][j] && pac[i][j])res.add(new int[]{i , j});
            }
        }
        return res;
    }
    private void dfs(int[][] matrix , int x , int y , boolean[][] sea , boolean[][] visited){

        for(int i = 0 ; i < 4; i++){
            int nextX = x + deltaX[i];
            int nextY = y + deltaY[i];
            if(nextX < 0 || nextY <0 || nextX >= m || nextY >= n || visited[nextX][nextY])continue;
            if(matrix[x][y] > matrix[nextX][nextY]) continue;
            sea[nextX][nextY] = true;
            visited[nextX][nextY] = true;
            dfs(matrix , nextX , nextY , sea ,visited);   
        }
    }
}

