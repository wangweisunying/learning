// 407. Trapping Rain Water II
// DescriptionHintsSubmissionsDiscussSolution
// Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map,
//  compute the volume of water it is able to trap after raining.

 

// Note:

// Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

 

// Example:

// Given the following 3x6 height map:
// [
//   [1,4,3,1,3,2],
//   [3,2,1,3,2,4],
//   [2,3,3,2,3,1]
// ]

// Return 4.


// BFS + PQ   track the smallest height int the round.
class Solution {
    public int trapRainWater(int[][] heightMap) {
        if(heightMap == null || heightMap.length == 0) return 0;
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> que = new PriorityQueue<>((a , b) -> (a[2] - b[2]));
        for(int i = 0 ; i < m ; i++){
            visited[i][0] = true;
            visited[i][n - 1] = true;
            if(i != 0 || i!= m - 1){
                que.offer(new int[]{ i , 0 , heightMap[i][0]});
                que.offer(new int[]{ i , n - 1 , heightMap[i][n - 1]});
            }
        }
        for(int i = 0 ; i < n ; i++){
            visited[0][i] = true;
            visited[m - 1][i] = true;
            if(i != 0 || i!= n - 1){
                que.offer(new int[]{ 0 , i , heightMap[0][i]});
                que.offer(new int[]{ m - 1 , i , heightMap[m - 1][i]});
            }
        }
        int[] deltaX = {1 , -1 , 0 , 0};
        int[] deltaY = {0  ,0 , 1 , -1};
        int res = 0;
        while(!que.isEmpty()){
            int[] cur = que.poll();
            for(int i = 0 ; i < 4 ; i++){
                int x = cur[0] + deltaX[i];
                int y = cur[1] + deltaY[i];
                if(x < 0 || y < 0 || x >=m || y >= n || visited[x][y]){
                    continue;
                }
                visited[x][y] = true;
                if(heightMap[x][y] < cur[2]){
                    res += cur[2] - heightMap[x][y];
                    heightMap[x][y] = cur[2];
                }
                que.offer(new int[]{x , y , heightMap[x][y]});
            }
            
        }
        return res;
        
    }
}