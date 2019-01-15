// 289. Game of Life
// DescriptionHintsSubmissionsDiscussSolution
// According to the Wikipedia's article: "The Game of Life, also known simply as Life, 
// is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

// Given a board with m by n cells, each cell has an initial state live (1) or dead (0). 
// Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

// Any live cell with fewer than two live neighbors dies, as if caused by under-population.
// Any live cell with two or three live neighbors lives on to the next generation.
// Any live cell with more than three live neighbors dies, as if by over-population..
// Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
// Write a function to compute the next state (after one update) of the board given its current state. 
// The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

// Example:

// Input: 
// [
//   [0,1,0],
//   [0,0,1],
//   [1,1,1],
//   [0,0,0]
// ]
// Output: 
// [
//   [0,0,0],
//   [1,0,1],
//   [0,1,1],
//   [0,1,0]
// ]
// Follow up:
// Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then 
// use their updated values to update other cells.
// In this question, we represent the board using a 2D array. In principle, the board is infinite, 
// which would cause problems when the active area encroaches the border of the array. How would you address these problems?
// [
//   [0,1,0],
//   [0,0,1],
//   [1,1,1],
//   [0,0,0]
// ]
// Output: 
// [
//   [0,0,0],
//   [1,0,1],
//   [0,1,1],
//   [0,1,0]
// ]
// [[0,0,0]
// ,[1,0,0]
// ,[0,0,0]
// ,[0,1,0]]


//read the data source line by line 




//in place  bit manupulation 0next  0 cur
class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length , n = board[0].length;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                int ct = count(i , j ,board);
                if(ct == 3 && ((board[i][j] & 1) == 0)) board[i][j] |= 1<<1; 
                if((ct == 3 || ct == 2 ) && ((board[i][j] & 1) == 1)) board[i][j] |= 1<<1;
            }
        }
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                board[i][j] = board[i][j] >>1;
            }
        }
    }
    private int count(int x , int y ,int[][] board){
        int m = board.length , n = board[0].length;
        int[] deltaX = {1 , -1 , 0 , 0 , 1 , -1 , 1 , -1};
        int[] deltaY = {0 , 0 , 1 , -1 , 1 , -1 , -1 , 1};
        int res = 0;
        for(int i = 0 ; i < 8 ; i++){
            int xx = x + deltaX[i];
            int yy = y + deltaY[i];
            if(xx < 0 || yy < 0 || xx >= m || yy >= n)continue;
            if((board[xx][yy] & 1) == 1) res++;
        }
        return res;
    }
}





// native solution
class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length , n = board[0].length;
        boolean[][] change = new boolean[m][n];
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                int ct = count(i , j ,board);
                if(ct == 3 && board[i][j] == 0) change[i][j] = true;
                if((ct < 2 || ct > 3) && board[i][j] == 1) change[i][j] = true;
            }
        }
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(change[i][j]) board[i][j] = board[i][j] == 1 ? 0 : 1;
            }
        }
    }
    private int count(int x , int y ,int[][] board){
        int m = board.length , n = board[0].length;
        int[] deltaX = {1 , -1 , 0 , 0 , 1 , -1 , 1 , -1};
        int[] deltaY = {0 , 0 , 1 , -1 , 1 , -1 , -1 , 1};
        int res = 0;
        for(int i = 0 ; i < 8 ; i++){
            int xx = x + deltaX[i];
            int yy = y + deltaY[i];
            if(xx < 0 || yy < 0 || xx >= m || yy >= n)continue;
            if(board[xx][yy] == 1) res++;
        }
        return res;
    }
}