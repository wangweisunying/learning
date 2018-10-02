// 611. Knight Shortest Path
// Given a knight in a chessboard (a binary matrix with 0 as empty and 1 as barrier) with a source position, find the shortest path to a destination position, return the length of the route.
// Return -1 if knight can not reached.

// Example
// [[0,0,0],
//  [0,0,0],
//  [0,0,0]]
// source = [2, 0] destination = [2, 2] return 2

// [[0,1,0],
//  [0,0,0],
//  [0,0,0]]
// source = [2, 0] destination = [2, 2] return 6

// [[0,1,0],
//  [0,0,1],
//  [0,0,0]]
// source = [2, 0] destination = [2, 2] return -1
// Clarification
// If the knight is at (x, y), he can get to the following positions in one step:

// (x + 1, y + 2)
// (x + 1, y - 2)
// (x - 1, y + 2)
// (x - 1, y - 2)
// (x + 2, y + 1)
// (x + 2, y - 1)
// (x - 2, y + 1)
// (x - 2, y - 1)
// Notice
// source and destination must be empty.
// Knight can not enter the barrier.


/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

public class Solution {
    /**
     * @param grid: a chessboard included 0 (false) and 1 (true)
     * @param source: a point
     * @param destination: a point
     * @return: the shortest path 
     */
    public int shortestPath(boolean[][] grid, Point source, Point destination) {
        int[] deltaX = new int[]{1 , 1 , - 1 ,-1 ,2 , 2 ,-2 ,-2};
        int[] deltaY = new int[]{2 , -2 , 2 , -2 ,1, -1 , 1, -1};

        int ct = 0;
        
        Queue<Point> que = new LinkedList();
        que.offer(source);
        grid[source.x][source.y] = true;
        while(!que.isEmpty()){
            int size = que.size();
            for(int i = 0 ; i < size ; i++){
                Point cur = que.poll();
                if(cur.x == destination.x && cur.y == destination.y){
                    return ct;
                }
                for(int j = 0 ; j < 8 ; j++){
                    int x = cur.x + deltaX[j];
                    int y = cur.y + deltaY[j];
                    if(outBound(x ,y , grid.length ,  grid[0].length)){
                        continue;
                    }
                    if(grid[x][y]){
                        continue;
                    }
                    grid[x][y] = true;
                    que.offer(new Point(x , y));
                }
            }
            ct++;
        }
        return -1;
    }
    private boolean outBound(int x ,int y , int m , int n){
        if(x < 0 || y < 0 || x >= m || y >= n){
            return true;
        }
        return false;


    }
}