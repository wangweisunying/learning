// 803. Bricks Falling When Hit
// DescriptionHintsSubmissionsDiscussSolution
// We have a grid of 1s and 0s; the 1s in a cell represent bricks.  A brick will not drop if and only if it is directly connected to the top of the grid, or at least one of its (4-way) adjacent bricks will not drop.

// We will do some erasures sequentially. Each time we want to do the erasure at the location (i, j), the brick (if it exists) on that location will disappear, and then some other bricks may drop because of that erasure.

// Return an array representing the number of bricks that will drop after each erasure in sequence.

// Example 1:
// Input: 
// grid = [[1,0,0,0],[1,1,1,0]]
// hits = [[1,0]]
// Output: [2]
// Explanation: 
// If we erase the brick at (1, 0), the brick at (1, 1) and (1, 2) will drop. So we should return 2.
// Example 2:
// Input: 
// grid = [[1,0,0,0],[1,1,0,0]]
// hits = [[1,1],[1,0]]
// Output: [0,0]
// Explanation: 
// When we erase the brick at (1, 0), the brick at (1, 1) has already disappeared due to the last move. So each erasure will cause no bricks dropping.  Note that the erased brick (1, 0) will not be counted as a dropped brick.
 

// Note:

// The number of rows and columns in the grid will be in the range [1, 200].
// The number of erasures will not exceed the area of the grid.
// It is guaranteed that each erasure will be different from any other erasure, and located inside the grid.
// An erasure may refer to a location with no brick - if it does, no bricks drop.



class Solution {
    int[] deltaX = {0 , 0 , 1 , -1};
    int[] deltaY = {1 , -1 , 0 , 0};
    int m;
    int n;
    public int[] hitBricks(int[][] grid, int[][] hits) {
        m = grid.length;
        n = grid[0].length;
        
        //break all the hits now  0 become -1 , 1 become 0
        for(int[] hit : hits){
            grid[hit[0]][hit[1]]--;    
        }
        // find all brick connect to the top label to 2;
        for(int i = 0 ; i < n ;i++){
            if(grid[0][i] == 1){
                grid[0][i] = 2;
                find(0 , i , grid);
            }
        }
        int[] res = new int[hits.length];
        //reverse add back brick to the wall 
        for(int i = hits.length - 1 ; i >= 0 ; i--){
            grid[hits[i][0]][hits[i][1]]++;
            if(grid[hits[i][0]][hits[i][1]] == 1){

                // check whether this brick connect to the top or top itself , label it to 2
                if(check(hits[i][0] , hits[i][1] , grid)){

                    // find those brick  that connected to this brick and value is 1 (not connected to top but only through this brick to top , label to 2)
                    res[i] = find(hits[i][0] , hits[i][1] , grid); 
                }
                else{
                    res[i] = 0;
                }
            }
        }
        return res;
    }
    
    private boolean check(int x ,int y ,int[][] grid){
        if(x == 0){
            grid[x][y] = 2;
            return true;
        }
        for(int i = 0 ; i < 4 ; i++){
            int xx = x + deltaX[i];
            int yy = y + deltaY[i];
            if(xx < 0 || yy < 0 || xx >= m || yy >= n){
                continue;
            }
            if(grid[xx][yy] == 2){
                grid[x][y] = 2;
                return true;
            }
        }
        return false;
    }


    private int find(int x ,int y ,int[][] grid){
        int res = 0;
        for(int i = 0 ; i < 4 ; i++){
            int xx = x + deltaX[i];
            int yy = y + deltaY[i];
            if(xx < 0 || yy < 0 || xx >= m || yy >= n || grid[xx][yy] != 1){
                continue;
            }
            grid[xx][yy] = 2;
            res++;
            res += find(xx , yy , grid);
        }
        return res;
    }


    // private void dfs(int x ,int y ,int[][] grid){
    //     for(int i = 0 ; i < 4 ; i++){
    //         int xx = x + deltaX[i];
    //         int yy = y + deltaY[i];
    //         if(xx < 0 || yy < 0 || xx >= m || yy >= n || grid[xx][yy] != 1){
    //             continue;
    //         }
    //         grid[xx][yy] = 2;
    //         dfs(xx , yy , grid);
    //     }
    // }
}