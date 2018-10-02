
// Knight Shortest Path II
// Given a knight in a chessboard n * m (a binary matrix with 0 as empty and 1 as barrier). the knight initialze position is (0, 0) and he wants to reach position (n - 1, m - 1), Knight can only be from left to right. Find the shortest path to the destination position, return the length of the route. Return -1 if knight can not reached.

// Example
// [[0,0,0,0],
//  [0,0,0,0],
//  [0,0,0,0]]

// Return 3

// [[0,0,0,0],
//  [0,0,0,0],
//  [0,1,0,0]]

// Return -1
// Clarification
// If the knight is at (x, y), he can get to the following positions in one step:

// (x + 1, y + 2)
// (x - 1, y + 2)
// (x + 2, y + 1)
// (x - 2, y + 1)

public class Solution {
    /**
     * @param grid: a chessboard included 0 and 1
     * @return: the shortest path
     */
    class Point{
        int x , y;
        Point(int x ,int y){
            this.x = x;
            this.y = y;
        }
    }
    public int shortestPath2(boolean[][] grid) {
        int[] deltaX = { 1 , -1, 2 ,-2 };
        int[] deltaY = { 2 , 2, 1, 1};


        Queue<Point> que = new LinkedList();
        que.offer(new Point(0 , 0));
        grid[0][0] = true;
        int res = 0;
        while(!que.isEmpty()){
            int size = que.size();
            res ++;
            for(int i = 0 ; i < size; i++){
                Point cur = que.poll();
                
                for(int j = 0 ; j < 4;j++){
                    int x = cur.x + deltaX[j];
                    int y = cur.y + deltaY[j];
                    if(outBound(x , y , grid.length , grid[0].length)){
                        continue;
                    }
                    if(grid[x][y]){
                        continue;
                    }
                    if(x == grid.length - 1 && y == grid[0].length - 1){
                        return res;
                    }
                    grid[x][y] = true; //别的路径走过的 你也不需要重新走放在这里
                    que.offer(new Point(x , y));
                }
            }
        }
        return -1;
    }
    private boolean outBound(int x ,int y ,int m , int n){
        if(x < 0 || x >= m || y <0 || y>=n){
            return true;
        }
        return false;

    }
    
}