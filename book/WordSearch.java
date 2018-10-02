// Word Search
// Given a 2D board and a word, find if the word exists in the grid.

// The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

// Example
// Given board =

// [
//   "ABCE",
//   "SFCS",
//   "ADEE"
// ]
// word = "ABCCED", -> returns true,
// word = "SEE", -> returns true,
// word = "ABCB", -> returns false.


public class Solution {
    /**
     * @param board: A list of lists of character
     * @param word: A string
     * @return: A boolean
     */
    int m , n;
    class Axis{
        int x , y;
        Axis(int x ,int y){
            this.x = x;
            this.y = y;
        }
    }
    int[] deltaX = {-1 , 1 , 0 ,0};
    int[] deltaY = {0 , 0 , -1 ,1};
    boolean res = false;
    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        
        int i , j ;
        for(i = 0 ; i < m ; i ++){
            for(j = 0 ; j < n ; j++){
                if(board[i][j] == word.charAt(0)){
                    boolean[][] visited = new boolean[m][n];
                    visited[i][j] = true;
                    dfs( word , board ,visited ,String.valueOf(word.charAt(0)) , 1 , new Axis(i , j));
                    if(res){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private void dfs( String word , char[][] board , boolean[][] visited ,String cur ,int startIndex , Axis curAxis ){
        if(res){
            return;
        }
        if(cur.equals(word)){
            res = true;
            return;
        }  
        for(int i = 0 ; i < 4 ; i++){
            int x = curAxis.x + deltaX[i];
            int y = curAxis.y + deltaY[i];
            if(!inBound(x , y)){
                continue;
            }
            if(visited[x][y]){
                continue;
            }
            if(startIndex < word.length() && word.charAt(startIndex) == board[x][y]){
                visited[x][y] = true;
                dfs(word , board , visited , cur + board[x][y] , startIndex + 1 , new Axis(x , y));
                visited[x][y] = false;   
            }

        }
    }
    private boolean inBound(int x ,int y){
        if(x >= 0 && x < m && y >=0 && y < n){
            return true;
        }
        return false;
    } 
}


public class Solution {
    /**
     * @param board: A list of lists of character
     * @param word: A string
     * @return: A boolean
     */
    int m , n;
    class Axis{
        int x , y;
        Axis(int x ,int y){
            this.x = x;
            this.y = y;
        }
    }
    int[] deltaX = {-1 , 1 , 0 ,0};
    int[] deltaY = {0 , 0 , -1 ,1};
    boolean res = false;
    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        
        int i , j ;
        for(i = 0 ; i < m ; i ++){
            for(j = 0 ; j < n ; j++){
                if(board[i][j] == word.charAt(0)){
                    board[i][j] = '#';
                    dfs( word , board ,String.valueOf(word.charAt(0)) , 1 , new Axis(i , j));
                    board[i][j] = '#';
                    if(res){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private void dfs( String word , char[][] board ,String cur ,int startIndex , Axis curAxis ){
        if(res){
            return;
        }
        if(cur.equals(word)){
            res = true;
            return;
        }  
        for(int i = 0 ; i < 4 ; i++){
            int x = curAxis.x + deltaX[i];
            int y = curAxis.y + deltaY[i];
            if(!inBound(x , y)){
                continue;
            }
            if(board[x][y] == '#'){
                continue;
            }
            if(startIndex < word.length() && word.charAt(startIndex) == board[x][y]){
                String tmp = cur + board[x][y];
                board[x][y] = '#';
                dfs(word , board , tmp , startIndex + 1 , new Axis(x , y));
                board[x][y] = word.charAt(startIndex);
            }
            
        }
    }
    private boolean inBound(int x ,int y){
        if(x >= 0 && x < m && y >=0 && y < n){
            return true;
        }
        return false;
    } 
}




public class Solution {
    // recursion
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0)
            return false;
        if(word.length() == 0)
            return true;
        
        for(int i = 0; i< board.length; i++){
            for(int j=0; j< board[0].length; j++){
                if(board[i][j] == word.charAt(0)){
                    boolean rst = find(board, i, j, word, 0);
                    if(rst)
                        return true;
                }
            }
        }
        return false;
    }
    
    private boolean find(char[][] board, int i, int j, String word, int start){
        if(start == word.length())
            return true;
        
        if (i < 0 || i>= board.length || 
     j < 0 || j >= board[0].length || board[i][j] != word.charAt(start)){
            return false;
     }
        
        board[i][j] = '#'; // should remember to mark it
        boolean rst = find(board, i-1, j, word, start+1) 
|| find(board, i, j-1, word, start+1) 
|| find(board, i+1, j, word, start+1) 
|| find(board, i, j+1, word, start+1);
        board[i][j] = word.charAt(start);
        return rst;
    }
}