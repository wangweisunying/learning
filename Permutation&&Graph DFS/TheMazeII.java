// // There is a ball in a maze with empty spaces and walls.
//  The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, 
//  it could choose the next direction.

// // Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. 
// The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). 
// If the ball cannot stop at the destination, return -1.

// // The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. 
// The start and destination coordinates are represented by row and column indexes.

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

// Return:12


public class Solution {
    /**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: the shortest distance for the ball to stop at the destination
     */
    //BFS
    int row;
    int col;
    class Point{
        int[] cur = new int[2];
        int len;
        Point(int[] arr , int z){
            this.cur[0] = arr[0];
            this.cur[1] = arr[1];
            this.len = z;
        }
    }
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if(start[0] == destination[0] && start[1] == destination[1]){
            return 0;
        }
        row = maze.length ;
        col = maze[0].length ;
        int[] deltaX = {-1, 1, 0, 0};
        int[] deltaY = {0, 0, -1, 1};
        boolean[][] visited = new boolean[row][col];
        Queue<Point> que = new LinkedList();
        que.offer(new Point(start , 0));
        
        while(!que.isEmpty()){
            int size = que.size();
            for(int i = 0 ; i < size ; i ++){
                Point p = que.poll();             
                visited[p.cur[0]][p.cur[1]] = true;
                for(int j = 0 ; j < 4 ; j ++){
                    int[] tmp = {p.cur[0] + deltaX[j] , p.cur[1] + deltaY[j]};
                    int ct  = p.len;
                    while(inBound(tmp) && maze[tmp[0]][tmp[1]] == 0){
                        ct ++; 
                        if(tmp[0] == destination[0] && tmp[1] == destination[1]){
                            tmp[0] += deltaX[j];
                            tmp[1] += deltaY[j];
                            if (inBound(tmp) && maze[tmp[0]][tmp[1]] == 0) {
                                return -1;
                            }
                            if (!inBound(tmp) || maze[tmp[0]][tmp[1]] == 1) { 
                                return ct;
                            }
                            break;
                        }
                        tmp[0] += deltaX[j];
                        tmp[1] += deltaY[j];
                    }
                    tmp[0] -= deltaX[j];
                    tmp[1] -= deltaY[j];
                    if(!visited[tmp[0]][tmp[1]]){ 
                        que.offer(new Point(tmp , ct));
                    }
                    
                }
            }
        }
        return -1;
    }
    private boolean inBound(int[] tmp) {
        if (tmp[0] >= 0 && tmp[0] < row && tmp[1] >= 0 && tmp[1] < col) {
            return true;
        }
        return false;
    }
}