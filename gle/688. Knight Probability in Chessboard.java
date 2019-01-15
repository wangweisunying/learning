// 688. Knight Probability in Chessboard
// DescriptionHintsSubmissionsDiscussSolution
// On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves.
//  The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).

// A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, 
// then one square in an orthogonal direction.

 



 

// Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) 
// and moves there.

// The knight continues moving until it has made exactly K moves or has moved off the chessboard.
//  Return the probability that the knight remains on the board after it has stopped moving.

 

// Example:

// Input: 3, 2, 0, 0
// Output: 0.0625
// Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
// From each of those positions, there are also two moves that will keep the knight on the board.
// The total probability the knight stays on the board is 0.0625.
 

// Note:

// N will be between 1 and 25.
// K will be between 0 and 100.
// The knight always initially starts on the board.


O(N^2)
class Solution {
    public double knightProbability(int n, int k, int r, int c) {
        int[] deltaX = {1, 2, 1, 2,-1,-2, -1, -2};
        int[] deltaY = {2, 1,-2,-1, 2, 1, -2, -1};

        double[][][] f = new double[n][n][2];
        f[r][c][0] = 1;
        int pre = 0 , cur = 0;
        for(int l = 0; l < k ; l++){
            pre = l % 2;
            cur = (l + 1) % 2;
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < n ; j++){
                    for(int m = 0 ; m < 8 ; m++){
                        int x = i - deltaX[m];
                        int y = j - deltaY[m];
                        if(x < 0 || y < 0 || x >= n|| y >= n) continue;
                        f[i][j][cur] += f[x][y][pre] / 8;
                    }
                }
            }
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < n ; j++){
                    f[i][j][pre] = 0;
                }
            }
        }
        
        double res = 0;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                res += f[i][j][cur];
            }
        }
        return res;
    }
}
















O(K*N^2)

class Solution {
    public double knightProbability(int n, int k, int r, int c) {
        int[] deltaX = {1, 2, 1, 2,-1,-2, -1, -2};
        int[] deltaY = {2, 1,-2,-1, 2, 1, -2, -1};
        
        double[][][] f = new double[k + 1][n][n];
        f[0][r][c] = 1;
        for(int l = 1 ; l <= k ; l++){
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < n ; j++){
                    for(int m = 0 ; m < 8 ; m++){
                        int x = i - deltaX[m];
                        int y = j - deltaY[m];
                        if(x < 0 || y < 0 || x >= n || y >= n){
                            continue;
                        }
                        f[l][i][j] += f[l - 1][x][y] / 8;
                    }
                }
            }
        }
        double remain = 0;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                remain += f[k][i][j];
            }
        }
        
        return remain;
    }
}