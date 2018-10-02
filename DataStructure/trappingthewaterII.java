// Trapping Rain Water II
// Given n x m non-negative integers representing an elevation map 2d where the area of each cell is 1 x 1, compute how much water it is able to trap after raining.



// Example
// Given 5*4 matrix

// [12,13,0,12]
// [13,4,13,12]
// [13,8,10,12]
// [12,13,12,12]
// [13,13,13,13]
// return 14.


public class Solution {
    /**
     * @param heights: a matrix of integers
     * @return: an integer
     */
    class Point{
        int x , y , val;
        Point(int x ,int y ,int val){
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    public int trapRainWater(int[][] heights) {
        int res = 0;
        int m = heights.length;
        int n = heights[0].length;
        int[] deltaX = {-1 , 1 , 0 , 0};
        int[] deltaY = {0 , 0 , -1  , 1};
        int i , j ;
        Queue<Point> que = new PriorityQueue(new Comparator<Point>(){
            @Override
            public int compare(Point p1 ,Point p2){
                return p1.val - p2.val;
            }
        });
        boolean[][] visited = new boolean[m][n];
        
        for(i = 1 ; i < heights[0].length - 1 ; i++){
            que.offer(new Point(0 , i , heights[0][i]));
            visited[0][i] = true;
            que.offer(new Point(heights.length - 1 , i , heights[heights.length - 1][i]));
            visited[heights.length - 1][i] = true;
        }
        for(i = 1 ; i < heights.length - 1 ; i++){
            que.offer(new Point(i , 0 , heights[i][0]));
            visited[i][0] = true;
            que.offer(new Point(i ,heights[0].length - 1 ,heights[i][heights[0].length - 1] ));
            visited[i][heights[0].length - 1] = true;
        }
        visited[0][0] = true;
        visited[m - 1][0] = true;
        visited[0][n - 1] = true;
        visited[m - 1][n - 1] = true;
        
        while(!que.isEmpty()){
            Point cur = que.poll();
            for(i = 0 ; i < 4 ; i++){
                int x = cur.x + deltaX[i];
                int y = cur.y + deltaY[i];
                if(outBound(x , y , m , n)){
                    continue;
                }
                if(visited[x][y]){
                    continue;
                }
                if(heights[x][y] < cur.val){
                    res += cur.val - heights[x][y];
                }
                visited[x][y] = true;
                que.offer(new Point(x , y , Math.max(heights[x][y] , cur.val)));;
            }
        }
        return res;
    }
    private boolean outBound(int x ,int y ,int m , int n){
        if(x < 0 || x >= m || y < 0 || y >= n){
            return true;
        }
        return false;
    }
}