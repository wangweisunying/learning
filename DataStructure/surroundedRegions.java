// Surrounded Regions
// Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

// A region is captured by flipping all 'O''s into 'X''s in that surrounded region.

// Example
// X X X X
// X O O X
// X X O X
// X O X X
// After capture all regions surrounded by 'X', the board should be:

// X X X X
// X X X X
// X X X X
// X O X X

// Union Find 
public class Solution {
    /*
     * @param board: board a 2D board containing 'X' and 'O'
     * @return: nothing
     */
    int row , col;
    int[] f;
    boolean[] sur;
    private int find(int x){
        if(f[x] == x){
            return x;
        }
        sur[f[x]] |= sur[x]; // update father's status 
        return f[x] = find(f[x]);
    }
    private void union(int a ,int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA != rootB){
            f[rootA] = rootB;
            sur[rootB] |= sur[rootA]; //  must update father's status 
        }
    }
    public void surroundedRegions(char[][] board) {
        if(board == null || board.length == 0){
            return;
        }
        int[] deltaX = {-1 , 1, 0 , 0};
        int[] deltaY = {0 , 0, -1 ,1};
        int m = board.length;
        int n = board[0].length;
        row = m ;
        col = n;
        f = new int[m * n];
        sur = new boolean[m * n];
        int i , j , k;
        for(i = 0 ; i < m ; i++){
            for(j = 0; j < n ; j++){
                f[i * n + j] = i * n + j;
            }
        }
        for(i = 0 ; i < m ; i++){
            for(j = 0; j < n ; j++){
                if(board[i][j] == 'O'){
                    for(k = 0 ; k < 4 ; k ++){
                        int x = i + deltaX[k];
                        int y = j + deltaY[k];
                        if(outBound(x , y)){
                            sur[i * n + j] = true;
                            find(i * n + j); //  must update father's status 
                            continue;
                        }
                        if(board[x][y] == 'O'){
                            union(x * n + y , i * n + j);
                        }
                    }
                }
            }
        }
        for( i = 0 ; i < m ; i++){
            for(j = 0; j < n ; j++){
                if(!sur[find(i * n + j)]){ //  must use father's status 
                    board[i][j] = 'X';
                }
            }
        }
    }
    private boolean outBound(int x ,int y){
        if(x < 0 || x >= row || y < 0 || y >= col){
            return true;
        }
        return false;
    }
}

// Example
// X X X X
// X O O X
// X X O X
// X O X X
// After capture all regions surrounded by 'X', the board should be:

// X X X X
// X X X X
// X X X X
// X O X X


// bfs get all the  edge into Queue
public class Solution {
    /*
     * @param board: board a 2D board containing 'X' and 'O'
     * @return: nothing
     */
    int row , col;
    class Point{
        int x , y;
        Point(int x , int y){
            this.x = x;
            this.y = y;
        }
    }
    public void surroundedRegions(char[][] board) {
        if(board == null || board.length == 0){
            return;
        }
        int[] deltaX = {-1 , 1, 0 , 0};
        int[] deltaY = {0 , 0, -1 ,1};
        int m = board.length;
        int n = board[0].length;
        row = m ;
        col = n;
        boolean[][] sur = new boolean[m][n];
        boolean[][] visited = new boolean[m][n];
        Queue<Point> que = new LinkedList();
        int i , j ;
        for(i = 0 ; i < m ; i++){
            if(board[i][0] == 'O'){
                que.offer(new Point(i , 0));
            }
            if(board[i][n - 1] == 'O'){
                que.offer(new Point(i , n - 1));
            }
        }
        for(j = 0 ; j < n ; j++){
            if(board[0][j] == 'O'){
                que.offer(new Point(0 , j));
            }
            if(board[m - 1][ j ] == 'O'){
                que.offer(new Point(m - 1 , j));
            }
        }
        while(!que.isEmpty()){
            Point cur = que.poll();
            sur[cur.x][cur.y] = true;
            visited[cur.x][cur.y] = true;
            for(i = 0 ; i < 4 ; i++){
                int x = cur.x + deltaX[i];
                int y = cur.y + deltaY[i];
                if(outBound(x , y)){
                    continue;
                }
                if(visited[x][y]){
                    continue;
                }
                if(board[x][y] == 'O'){
                    que.offer(new Point(x , y));
                }
                
            }
        }
        for(i = 0 ; i < m ; i++){
            for(j = 0 ; j < n ; j++){
                if(!sur[i][j]){
                    board[i][j] = 'X';
                }
            }
        }
    }
    private boolean outBound(int x ,int y){
        if(x < 0 || x >= row || y < 0 || y >= col){
            return true;
        }
        return false;
    }
}