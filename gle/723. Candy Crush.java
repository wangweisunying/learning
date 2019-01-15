// 723. Candy Crush
// DescriptionHintsSubmissionsDiscussSolution
// This question is about implementing a basic elimination algorithm for Candy Crush.

// Given a 2D integer array board representing the grid of candy, different positive integers board[i][j] represent different types of candies. A value of board[i][j] = 0 represents that the cell at position (i, j) is empty. The given board represents the state of the game following the player's move. Now, you need to restore the board to a stable state by crushing candies according to the following rules:

// If three or more candies of the same type are adjacent vertically or horizontally, "crush" them all at the same time - these positions become empty.
// After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, then these candies will drop until they hit a candy or bottom at the same time. (No new candies will drop outside the top boundary.)
// After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.
// If there does not exist more candies that can be crushed (ie. the board is stable), then return the current board.
// You need to perform the above rules until the board becomes stable, then return the current board.

 

// Example:

// Input:
// board = 
// [[110,5,112,113,114],[210,211,5,213,214],[310,311,3,313,314],[410,411,412,5,414],[5,1,512,3,3],[610,4,1,613,614],[710,1,2,713,714],[810,1,2,1,1],[1,1,2,2,2],[4,1,4,4,1014]]

// Output:
// [[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[110,0,0,0,114],[210,0,0,0,214],[310,0,0,113,314],[410,0,0,213,414],[610,211,112,313,614],[710,311,412,613,714],[810,411,512,713,1014]]

// Explanation: 

 

// Note:

// The length of board will be in the range [3, 50].
// The length of board[i] will be in the range [3, 50].
// Each board[i][j] will initially start as an integer in the range [1, 2000].


// 思路 先crush 再drop ， 
// crush 要分层统计 ，再分列统计需要消除的axis
// drop自下而上 经典merge 问题
class Solution {
    int m , n;
    public int[][] candyCrush(int[][] board) {
        m = board.length;
        n = board[0].length;
        while(crush(board)){
            drop(board);
        }
        return board;
    }

    private void drop(int[][] board){
        for(int j = 0 ; j < n ; j++){
            int s = m - 1;
            while(s >= 0 && board[s][j] != 0){
                s--;
            }
            int e = s - 1;
            while(e >= 0){
                if(board[e][j] != 0){
                    board[s--][j] = board[e--][j];
                }
                else{
                    e--;
                }
            }
            while(s >= 0){
                board[s--][j] = 0;
            }
        }
    }


    private boolean crush(int[][] board){
        List<int[]> list = new ArrayList();
        // horizonal
        for(int i = 0 ; i < m ; i++){
            int s = 0 , e = 0 , pre = board[i][s];
            while(e < n){

                // skip all 0s
                if(board[i][e] == board[i][s] && board[i][s] == 0){
                    s++;
                    e++;
                    continue;
                }
                if(board[i][e] == pre){
                    e++;
                }
                else{
                    if(e - s >= 3){
                        while(s < e){
                            list.add(new int[]{i , s++});
                        }
                    }
                    else{
                        s = e;
                    }
                    pre = board[i][e];    
                }
            }
            if(e - s >= 3){
                while(s < e){
                    list.add(new int[]{i , s++});
                }
            }
        }

        // vertical
        for(int i = 0 ; i < n ; i++){
            int s = 0 , e = 0 , pre = board[s][i];
            while(e < m){
                if( board[s][i] == board[e][i] && board[s][i] == 0){
                    s++;
                    e++;
                    continue;
                }
                if(board[e][i] == pre){
                    e++;
                }
                else{
                    if(e - s >= 3){
                        while(s < e){
                            list.add(new int[]{s++ , i});
                        }
                    }
                    else{
                        s = e;
                    }
                    pre = board[e][i];    
                }
            }
            if(e - s >= 3){
                while(s < e){
                    list.add(new int[]{s++ , i});
                }
            }
        }
        for(int[] x : list){
            board[x[0]][x[1]] = 0;
        }
        return !list.isEmpty();
    }
}

