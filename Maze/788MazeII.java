
// // 788. The Maze II
// // There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. 
// When the ball stops, it could choose the next direction.

// // Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. 
// The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

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
// Notice
// 1.There is only one ball and one destination in the maze.
// 2.Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
// 3.The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
// 4.The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.

public class Solution {
    /**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: the shortest distance for the ball to stop at the destination
     */
    int[] deltaX = {0 , 0 , 1 , -1};
    int[] deltaY = {-1 , 1, 0 ,  0};
    
    int m , n;
    
    class Point{
        int[] axis;
        int dis;
        Point(int[] axis , int dis){
            this.axis = axis;
            this.dis = dis;
        }

    }
    //priority Queue 找最小的先出队列 + bfs 
    public int shortestDistance(int[][] maze, int[] start, int[] end) {
        m = maze.length;
        n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[start[0]][start[1]] = true;
        Queue<Point> que = new PriorityQueue(new Comparator<Point>(){
            @Override
            public int compare(Point p1 ,Point p2){
                return p1.dis - p2.dis;
            }
        });
        que.offer(new Point(start , 0));
        
        
        while(!que.isEmpty()){
            Point cur = que.poll();
            if(cur.axis[0] == end[0] && cur.axis[1] == end[1]){
                return cur.dis;
            }
            for(int i = 0 ; i < 4 ; i++){
                int dis = cur.dis;
                int x = cur.axis[0];
                int y = cur.axis[1];
                while(!outBound(x , y , m , n) && maze[x][y] == 0){
                    dis++;
                    x += deltaX[i];
                    y += deltaY[i];
                }
                dis--;
                x -= deltaX[i];
                y -= deltaY[i];
                if(visited[x][y]){
                    continue;
                }
                visited[x][y] = true;
                que.offer(new Point(new int[]{x , y} ,dis ));
            } 
        }
        return -1;


    }
    private boolean outBound(int x ,int y , int  m , int n){
        if(x < 0 || y < 0 || x >= m || y >= n ){
            return true;
        }
        return false;
    }
}


