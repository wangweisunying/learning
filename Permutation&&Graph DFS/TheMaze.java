// There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, 
// but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

// Given the ball's start position, the destination and the maze, determine whether the ball could  stop at the destination.

// The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

// Example
// Given:
// a maze represented by a 2D array

// 0 0 1 0 0
// 0 0 0 0 0
// 0 0 0 1 0
// 1 1 0 1 1
// 0 0 0 0 0

// start coordinate (rowStart, colStart) = (0, 4)
// destination coordinate (rowDest, colDest) = (4, 4)

// Return:true

// bfs (memory exceeded )
public class Solution {
    /**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: whether the ball could stop at the destination
     */
//BFS 思路 碰到边界判断后再进入队列 ， 

public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if(start[0] == destination[0] && start[1] == destination[1]){
            return true;
        }
        Queue<int[]> que = new LinkedList();
        int row = maze.length, col = maze[0].length;
        int[] deltaX = {-1, 1, 0, 0};
        int[] deltaY = {0, 0, -1, 1};
        boolean[][] visited = new boolean[row][col];
        que.offer(start);
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                int[] point = que.poll();
                visited[point[0]][point[1]] = true;
                for (int j = 0; j < 4; j++) {
                    int[] tmp = {point[0] + deltaX[j], point[1] + deltaY[j]};
                    while(inBound(tmp , row ,col) && maze[tmp[0]][tmp[1]] == 0){
                        if (tmp[0] == destination[0] && tmp[1] == destination[1]) {
                            tmp[0] += deltaX[j];
                            tmp[1] += deltaY[j];
                            if (!inBound(tmp, row, col) || maze[tmp[0]][tmp[1]] == 1) {
                                return true;
                            }
                            break;
                        }
                        tmp[0] += deltaX[j];
                        tmp[1] += deltaY[j];
                        
                    }
                    //还原到原点
                    tmp[0] -= deltaX[j];
                    tmp[1] -= deltaY[j];
                    if(!visited[tmp[0]][tmp[1]]){
                        que.offer(tmp);
                    }  
                }
            }
        }
        return false;
    }

    private boolean inBound(int[] tmp, int row, int col) {
        if (tmp[0] >= 0 && tmp[0] < row && tmp[1] >= 0 && tmp[1] < col) {
            return true;
        }
        return false;
    }
}


//DFS  memorized exceed
public class Solution {
    /**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: whether the ball could stop at the destination
     */

    int[] deltaX = {-1, 1, 0, 0}; // 上下左右
    int[] deltaY = {0, 0, -1, 1};
    int row , col;
    boolean res = false;
    boolean found = false;
    int ct = 0;
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        row = maze.length;
        col = maze[0].length;
        for(int i = 0 ; i < 4 ; i ++){
            int[] arr = {destination[0] + deltaX[i] ,destination[1] + deltaY[i]};
            if(inBound(arr) && maze[destination[0] + deltaX[i]][destination[1] + deltaY[i]] == 0){
                ct ++;
            }
        }
        System.out.println(ct);
        
        
        boolean[][] visited = new boolean[row][col];
        dfs(visited , maze , start , destination , -9);
        return res;
    }
    private void dfs( boolean[][] visited , int[][] maze , int[] cur, int[] destination , int dir){        
        for(int i = 0 ; i < 4 ; i ++){
            if(found){
                return;
            }
            if((i == 0 && dir == 1)|| (i == 1 && dir == 0) || (i == 2 && dir == 3) || (i == 3 && dir == 2) ){ // dir 反向 
                continue;
            }
            
            int[] tmp = { cur[0] + deltaX[i] , cur[1] + deltaY[i] };
            while(inBound(tmp) && maze[tmp[0]][tmp[1]] == 0 ){
                if (tmp[0] == destination[0] && tmp[1] == destination[1]) {
                    tmp[0] += deltaX[i];
                    tmp[1] += deltaY[i];
                    if (!inBound(tmp) || maze[tmp[0]][tmp[1]] == 1) {
                        res = true;
                        found = true;
                        return;
                    }
                    System.out.println(ct);
                    ct --;
                    if(ct == 0){
                        found = true;
                        return;
                    }
                    break;
                }
                tmp[0] += deltaX[i];
                tmp[1] += deltaY[i];
                
            }
            tmp[0] -= deltaX[i];
            tmp[1] -= deltaY[i];
            if(visited[tmp[0]][tmp[1]]){
                continue;
            }
//            System.out.println(Arrays.toString(tmp));
            visited[tmp[0]][tmp[1]] = true;
            dfs( visited , maze , tmp , destination , i); 
            visited[tmp[0]][tmp[1]] = false;
            
        }
    }
    private boolean inBound(int[] tmp) {
        if (tmp[0] >= 0 && tmp[0] < row && tmp[1] >= 0 && tmp[1] < col) {
            return true;
        }
        return false;
    }
}