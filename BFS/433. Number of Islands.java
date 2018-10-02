
// 433. Number of Islands
// Given a boolean 2D matrix, 0 is represented as the sea, 1 is represented as the island. If two 1 is adjacent, we consider them in the same island. We only consider up/down/left/right adjacent.

// Find the number of islands.

// Example
// Given graph:

// [
//   [1, 1, 0, 0, 0],
//   [0, 1, 0, 0, 1],
//   [0, 0, 0, 1, 1],
//   [0, 0, 0, 0, 0],
//   [0, 0, 0, 0, 1]
// ]
// return 3.


public class Solution {
    /**
     * @param grid: a boolean 2D matrix
     * @return: an integer
     */

    class Axis{
        int x , y;
        Axis(int x ,int y){
            this.x = x;
            this.y = y;
        }
    }

    public int numIslands(boolean[][] grid) {
        int res = 0;
        int[] deltaX = { - 1 ,1 , 0 , 0};
        int[] deltaY = { 0 ,0 , 1 , -1};
        

        Queue<Axis> que = new LinkedList();
        for(int i = 0 ; i < grid.length ; i++){
            for(int j = 0 ; j < grid[0].length ; j++){
                if(grid[i][j]){
                    que.offer(new Axis(i , j));
                    res++;
                    while(!que.isEmpty()){
                        Axis cur = que.poll();
                        grid[cur.x][cur.y] = false;
                        for(int k = 0 ; k < 4 ; k ++){
                            int x = cur.x + deltaX[k];
                            int y = cur.y + deltaY[k];
                            if(outBound(x , y , grid.length ,grid[0].length)){
                                continue;
                            }
                            if(!grid[x][y]){
                                continue;
                            }
                            que.offer(new Axis(x , y));
                        }
                    }                    
                }
            }
        }
        return res;
    }

    private boolean outBound(int x , int y , int m , int n){
        if(x < 0 || y < 0 || x >= m || y >= n){
            return true;
        }
        return false;
    
    }
}