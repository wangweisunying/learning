// 663. Walls and Gates
// Description
// You are given a m x n 2D grid initialized with these three possible values.

// -1 - A wall or an obstacle.
// 0 - A gate.
// INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
// Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

// Have you met this question in a real interview?  
// Example
// Given the 2D grid:

// INF  -1  0  INF
// INF INF INF  -1
// INF  -1 INF  -1
//   0  -1 INF INF
// return the result:

//   3  -1   0   1
//   2   2   1  -1
//   1  -1   2  -1
//   0  -1   3   4


//不要想的太复杂） 遇到最短路径首选BFS
//以所有0点开始向外扩展！！

public class Solution {
    /**
     * @param rooms: m x n 2D grid
     * @return: nothing
     */
    int m , n;
    int[] deltaX = {1 , -1 , 0 , 0};
    int[] deltaY = {0 , 0 , -1 , 1};
    public void wallsAndGates(int[][] nums) {
        m = nums.length;
        n = nums[0].length;

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> que = new LinkedList();
        for(int i = 0 ; i < m ; i++ ){
            for(int j = 0 ; j < n ; j++){
                if(nums[i][j] == 0){
                    que.offer(new int[]{i , j});
                    visited[i][j] = true; 
                }
            }
        }

        int level = 0;
        while(!que.isEmpty()){
            level ++;
            int size = que.size();
            for(int l = 0 ; l < size ; l++){
                int[] axis = que.poll();
                for(int k = 0 ; k < 4 ; k++){
                    int x = axis[0] + deltaX[k];
                    int y = axis[1] + deltaY[k];
                    if(outBound(x , y) || visited[x][y] || nums[x][y] <= 0){
                        continue;
                    }
                    visited[x][y] = true;
                    // nums[x][y] = Math.min(level , nums[x][y]);
                    nums[x][y] = level;
                    que.offer(new int[]{x , y});
                }
            }
            
        }
    }
    private boolean outBound(int x , int y){
        if(x < 0 || y < 0 || x>= m || y >= n){
            return true;
        }
        return false;
    }
        
}