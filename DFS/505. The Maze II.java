// 505. The Maze II
// Medium

// 238

// 12

// Favorite

// Share
// There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

// Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

// The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

 

// Example 1:

// Input 1: a maze represented by a 2D array

// 0 0 1 0 0
// 0 0 0 0 0
// 0 0 0 1 0
// 1 1 0 1 1
// 0 0 0 0 0

// Input 2: start coordinate (rowStart, colStart) = (0, 4)
// Input 3: destination coordinate (rowDest, colDest) = (4, 4)

// Output: 12

// Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
//              The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

// Example 2:

// Input 1: a maze represented by a 2D array

// 0 0 1 0 0
// 0 0 0 0 0
// 0 0 0 1 0
// 1 1 0 1 1
// 0 0 0 0 0

// Input 2: start coordinate (rowStart, colStart) = (0, 4)
// Input 3: destination coordinate (rowDest, colDest) = (3, 2)

// Output: -1

// Explanation: There is no way for the ball to stop at the destination.

 

// Note:

// There is only one ball and one destination in the maze.
// Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
// The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
// The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.


public class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row: distance)
            Arrays.fill(row, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;
        dfs(maze, start, distance);
        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }

    public void dfs(int[][] maze, int[] start, int[][] distance) {
        int[][] dirs={{0,1}, {0,-1}, {-1,0}, {1,0}};
        for (int[] dir: dirs) {
            int x = start[0] + dir[0];
            int y = start[1] + dir[1];
            int count = 0;
            while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                x += dir[0];
                y += dir[1];
                count++;
            }
            if (distance[start[0]][start[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                distance[x - dir[0]][y - dir[1]] = distance[start[0]][start[1]] + count;
                dfs(maze, new int[]{x - dir[0],y - dir[1]}, distance);
            }
        }
    }
}
//记忆话dfs
class Solution {
    int m , n;
    int[] deltaX ;
    int[] deltaY ;
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        m  = maze.length ;
        n  = maze[0].length;
        deltaX = new int[]{0 , 0 , 1 , -1};
        deltaY = new int[]{1 , -1 , 0 , 0};
        int[] res = new int[1];
        int[][] dis = new int[m][n];
        for(int[] x : dis) Arrays.fill(x , Integer.MAX_VALUE);
        dis[start[0]][start[1]] = 0;
        res[0] = Integer.MAX_VALUE;
        dfs(res , maze , start , destination , dis);
        return res[0] == Integer.MAX_VALUE ? -1 : res[0];
    }
    private void dfs(int[] res , int[][] maze , int[] start , int[] destination , int[][] distance){
        if(start[0] == destination[0] && start[1] == destination[1]){
            res[0] = Math.min(res[0] , distance[start[0]][start[1]]);
            return;
        }
        for(int i = 0 ; i < 4 ; i++){
            int x = start[0] + deltaX[i];
            int y = start[1] + deltaY[i];
            int dis = distance[start[0]][start[1]] + 1;
            
            while(x >= 0 && y >= 0 && x < m && y < n && maze[x][y] == 0){
                
                x += deltaX[i];
                y += deltaY[i];
                ++dis;
            }
            x -= deltaX[i];
            y -= deltaY[i];
            --dis;
           
            if(dis >= distance[x][y]) continue;
             
            distance[x][y] = dis;
            dfs(res , maze , new int[]{x , y} , destination , distance);
        } 
    }
}





















// bfs + pq
class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        Queue<int[]> que = new PriorityQueue<>((a , b) -> (a[2] - b[2]));
        int m  = maze.length , n  = maze[0].length;
        que.offer(new int[]{start[0] , start[1] , 0});
        int[] deltaX = {0 , 0 , 1 , -1};
        int[] deltaY = {1 , -1 , 0 , 0};
        int res = Integer.MAX_VALUE;
        while(!que.isEmpty()){
            int[] cur = que.poll();
            maze[cur[0]][cur[1]] = -1;
            if(cur[0] == destination[0] && cur[1] == destination[1]){
                res = Math.min(res , cur[2]);
                return res;
            }
            for(int i = 0 ; i < 4 ; i++){
                int x = cur[0] + deltaX[i];
                int y = cur[1] + deltaY[i];
                int dis = cur[2] + 1;
                while(x >= 0 && y >= 0 && x < m && y < n && maze[x][y] != 1){
                    x += deltaX[i];
                    y += deltaY[i];
                    ++dis;
                }
                x -= deltaX[i];
                y -= deltaY[i];
                --dis;
                if(maze[x][y] == -1) continue;
                que.offer(new int[]{x , y , dis});
            } 
        }
        return -1;
    }
}