
//位运算实战！

class Solution {
    int[] rowVisited = new int[9];
    int[] colVisited = new int[9];
    int[] areaVisited = new int[9];    
    public void solveSudoku(char[][] board) { 
        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0 ; j < 9 ; j++){
                if(board[i][j] != '.'){
                    int val = board[i][j] - '1';
                   
                    rowVisited[i] |= 1 << val;
                    colVisited[j] |= 1 << val;
                    areaVisited[(i / 3) * 3 + j / 3] |= 1 << val;
                }
            }
        }
        dfs(board);
    }
    private boolean check(){
        for(int i = 0 ; i < 9 ; i++){
            int st = (1 << 9) - 1;
            if(rowVisited[i] != st || colVisited[i] != st || areaVisited[i] != st ){
                return false;
            }
        }
        return true;
    }
    private boolean dfs(char[][] board){
        if(check()){
            return true;
        }
        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0 ; j < 9 ; j++){
                if(board[i][j] != '.'){
                    continue;
                }
                for(int k = 0 ; k < 9 ; k++){
                    if(((rowVisited[i] >> k) & 1) == 1 ||
                     ((colVisited[j] >> k) & 1) == 1 ||
                    ((areaVisited[(i / 3) * 3 + j / 3] >> k) & 1) == 1 ){
                        continue;
                    }
                    int now = 1 << k;
                    rowVisited[i] |= now;
                    colVisited[j] |= now;
                    areaVisited[(i / 3) * 3 + j / 3] |= now;
                    board[i][j] = (char)(k + '1');
                    if(dfs(board)){
                        return true;
                    }
                    board[i][j] = '.';
                    rowVisited[i] ^= now;
                    colVisited[j] ^= now;
                    areaVisited[(i / 3) * 3 + j / 3] ^= now;

                }
                return false;
            }
        }
        return true;
    }
}

