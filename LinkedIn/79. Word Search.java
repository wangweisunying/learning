// 79. Word Search


// Share
// Given a 2D board and a word, find if the word exists in the grid.

// The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

// Example:

// board =
// [
//   ['A','B','C','E'],
//   ['S','F','C','S'],
//   ['A','D','E','E']
// ]

// Given word = "ABCCED", return true.
// Given word = "SEE", return true.
// Given word = "ABCB", return false.
[["A","B","C","E"],
["S","F","E","S"],
["A","D","E","E"]]
"ABCESEEEFS"

//需要回溯
class Solution {
    int m , n;
    int[] deltaX , deltaY;
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0) return false;
        m = board.length;
        n = board[0].length;
        deltaX = new int[]{0 , 0 , 1 , -1};
        deltaY = new int[]{1 , -1 , 0 ,0};
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(dfs(board , i , j , word , new boolean[m][n])) return true;
            }
        }
        return false;
    }
    private boolean dfs(char[][] board , int x , int y ,String word, boolean[][] visited){
        if(word.charAt(0) != board[x][y]) return false;
        if(visited[x][y]) return false;
        visited[x][y] = true;
        if(word.length() == 1) return true;
        for(int i = 0 ; i < 4 ; i ++){
            int xx = x + deltaX[i];
            int yy = y + deltaY[i];
            if(xx < 0 || yy < 0 || xx >= m || yy >= n ) continue;
            if(dfs(board , xx ,yy , word.substring(1) , visited))return true;
        }
        visited[x][y] = false;
        return false;
    }
}