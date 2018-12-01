// 317. Shortest Distance from All Buildings
// DescriptionHintsSubmissionsDiscussSolution
// You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right.
//  You are given a 2D grid of values 0, 1 or 2, where:

// Each 0 marks an empty land which you can pass by freely.
// Each 1 marks a building which you cannot pass through.
// Each 2 marks an obstacle which you cannot pass through.
// Example:

// Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]

// 1 - 0 - 2 - 0 - 1
// |   |   |   |   |
// 0 - 0 - 0 - 0 - 0
// |   |   |   |   |
// 0 - 0 - 1 - 0 - 0

// Output: 7 

// Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
//              the point (1,2) is an ideal empty land to build a house, as the total 
//              travel distance of 3+3+1=7 is minimal. So return 7.
// Note:
// There will be at least one building. If it is not possible to build such house according to the above rules, return -1.

// 位运算运用！！！ //全局最优 不需要分层 32ms 
class Solution {
    public int shortestDistance(int[][] grid) {
        Queue<int[]> que = new LinkedList();
        int m = grid.length , n = grid[0].length;
        int key = 0;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ;j ++){
                if(grid[i][j] == 1){
                    que.offer(new int[]{i , j , key++ , 1});
                }
            }
        }
        int[] deltaX = {0 , 0 , 1, -1};
        int[] deltaY = {1 , -1 , 0, 0};
        long target = (1L << key) - 1;
        int[][] dis = new int[m][n];
        long[][] lock = new long[m][n];
        int min = Integer.MAX_VALUE;
        while(!que.isEmpty()){
            //可分可不分

            int[] cur = que.poll();
            for(int j = 0 ; j < 4 ; j++){
                int x = cur[0] + deltaX[j];
                int y = cur[1] + deltaY[j];
                if(x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == 1 || grid[x][y] == 2 || ((lock[x][y] >> cur[2]) & 1) == 1 ){
                    continue;
                }
                lock[x][y] |= (1L << cur[2]);
                if(lock[x][y] == target){
                    min = Math.min(min , dis[x][y] + cur[3]);
                }
                dis[x][y] += cur[3];
                que.offer(new int[]{x , y , cur[2] , cur[3] + 1});
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}








//位运算 1的个数超过了32 个就会失效
class Solution {
    public int shortestDistance(int[][] grid) {
        Queue<int[]> que = new PriorityQueue<>((a , b) -> ( a[3] - b[3]));
        int m = grid.length , n = grid[0].length;
        
        long key = 0;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ;j ++){
                if(grid[i][j] == 1){
                    grid[i][j] = -1;
                    que.offer(new int[]{i , j , key++ , 1});
                }
                if(grid[i][j] == 2){
                    grid[i][j] = -2;
                }
            }
        }
        int[] deltaX = {0 , 0 , 1, -1};
        int[] deltaY = {1 , -1 , 0, 0};
        int target = (1 << key) - 1;
        int[][] dis = new int[m][n];
        int min = Integer.MAX_VALUE;
        while(!que.isEmpty()){
            int size = que.size();
            for(int i = 0 ; i < size ; i++){
                int[] cur = que.poll();
                for(int j = 0 ; j < 4 ; j++){
                    int x = cur[0] + deltaX[j];
                    int y = cur[1] + deltaY[j];
                    if(x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == -1 || grid[x][y] == -2 || ((grid[x][y] >> cur[2]) & 1) == 1 ){
                        continue;
                    }
                    grid[x][y] |= (1 << cur[2]);
                    if(grid[x][y] == target){
                       min = Math.min(min , dis[x][y] + cur[3]);
                    }
                    dis[x][y] += cur[3];
                    que.offer(new int[]{x , y , cur[2] , cur[3] + 1});
                }
            }
            
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}



