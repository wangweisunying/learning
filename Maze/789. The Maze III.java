
// 789. The Maze III
// There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), 
// but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.

// Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance. 
// The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included). Output the moving directions by using 'u', 'd', 'l' and 'r'. 
// Since there could be several different shortest ways, you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".

// The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. 
// The ball and the hole coordinates are represented by row and column indexes.

// Example
// Input:
// a maze represented by a 2D array

// 0 0 0 0 0
// 1 1 0 0 1
// 0 0 0 0 0
// 0 1 0 0 1
// 0 1 0 0 0

// ball coordinate (rowBall, colBall) = (4, 3)
// hole coordinate (rowHole, colHole) = (0, 1)
// [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]]
// [4,3]
// [0,1]
// Output
// "ul"
// Expected
// "lul"
// Output:"lul"
// Notice
// 1.There is only one ball and one hole in the maze.
// 2.Both the ball and hole exist on an empty space, and they will not be at the same position initially.
// 3.The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
// 4.The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.

//bfs + priortyQueue , trick part is find queue , next loop return , make sure all the result with same size in this round goes into the que.
public class Solution {
    /**
     * @param maze: the maze
     * @param ball: the ball position
     * @param hole: the hole position
     * @return: the lexicographically smallest way
     */

    int[] deltaX = {0 , 0 , 1 , -1};
    int[] deltaY = {-1 , 1, 0 ,  0};
    String[] dir = {"l" , "r" , "d", "u"};
    int m , n;
    class Point{
        int[] axis;
        String path;
        int dis;
        Point(int[] axis , String path , int dis){
            this.axis = axis;
            this.path = path;
            this.dis = dis;
        }

    }
    public String findShortestWay(int[][] maze, int[] start, int[] end) {
        m = maze.length;
        n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[start[0]][start[1]] = true;
        Queue<Point> que = new PriorityQueue(new Comparator<Point>(){
            @Override
            public int compare(Point p1 ,Point p2){
                if(p1.dis == p2.dis){
                    return p1.path.compareTo(p2.path);
                }
                return p1.dis - p2.dis;
                
            }
        });
        que.offer(new Point(start , "" , 0));
        while(!que.isEmpty()){
            Point cur = que.poll();
            if(cur.axis[0] == end[0] && cur.axis[1] == end[1]){
                return cur.path;
            }
            for(int i = 0 ; i < 4 ; i++){
                String path = cur.path;
                int x = cur.axis[0];
                int y = cur.axis[1];
                int dis = cur.dis;
                path += dir[i];
                
                while(!outBound(x , y , m , n) && maze[x][y] == 0){
                    if(x == end[0] && y == end[1]){
                        que.offer(new Point(new int[]{x , y} ,path , dis));
                    }
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
                que.offer(new Point(new int[]{x , y} ,path , dis));
            } 
        }
        return "impossible";
    }
    private boolean outBound(int x ,int y , int  m , int n){
        if(x < 0 || y < 0 || x >= m || y >= n ){
            return true;
        }
        return false;
    }
}




public class Solution {
    /**
     * @param maze: the maze
     * @param ball: the ball position
     * @param hole: the hole position
     * @return: the lexicographically smallest way
     */

    int[] deltaX = {0 , 0 , 1 , -1};
    int[] deltaY = {-1 , 1, 0 ,  0};
    String[] dir = {"l" , "r" , "d", "u"};
    int m , n;
    class Point{
        int[] axis;
        String path;
        int dis;
        Point(int[] axis , String path , int dis){
            this.axis = axis;
            this.path = path;
            this.dis = dis;
        }

    }
    public String findShortestWay(int[][] maze, int[] start, int[] end) {
        
        List<String> res = new ArrayList();
        int min = Integer.MAX_VALUE;
        m = maze.length;
        n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[start[0]][start[1]] = true;
        Queue<Point> que = new PriorityQueue(new Comparator<Point>(){
            @Override
            public int compare(Point p1 ,Point p2){
                if(p1.dis == p2.dis){
                    return p1.path.compareTo(p2.path);
                }
                return p1.dis - p2.dis;
                
            }
        });
        que.offer(new Point(start , "" , 0));
        while(!que.isEmpty()){
            Point cur = que.poll();
            
            for(int i = 0 ; i < 4 ; i++){
                String path = cur.path;
                
                int x = cur.axis[0];
                int y = cur.axis[1];
                int dis = cur.dis;
                path += dir[i];
                
                while(!outBound(x , y , m , n) && maze[x][y] == 0){
                    if(x == end[0] && y == end[1]){
                        if(min >= dis){
                            res.add(path);
                            min = dis;
                        }
                        else{
                            System.out.println(res);
                            Collections.sort(res);
                            return res.get(0);
                        }
                    }
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
                que.offer(new Point(new int[]{x , y} ,path , dis));
            } 
        }
        if(res.size() != 0){
            Collections.sort(res);
            return res.get(0);
        }
        return "impossible";
    }
    private boolean outBound(int x ,int y , int  m , int n){
        if(x < 0 || y < 0 || x >= m || y >= n ){
            return true;
        }
        return false;
    }
}











public class Solution {
    /**
     * @param maze: the maze
     * @param ball: the ball position
     * @param hole: the hole position
     * @return: the lexicographically smallest way
     */
    int[] dx = {0, 0, -1, 1};
    int[] dy = {1, -1, 0, 0};
    char[] dir = {'r', 'l', 'u', 'd'};
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        // write your code here
        
        int m = maze.length;
        int n = maze[0].length;
        
        PriorityQueue<Point> q = new PriorityQueue<Point>();
        boolean visit[][] = new boolean[m][n];
        StringBuilder first = new StringBuilder();
        q.offer(new Point(ball[0], ball[1], 0,first));

        
        while(!q.isEmpty()){

            Point cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int len = cur.len;
            StringBuilder sb = cur.sb;
            if(x == hole[0] && y == hole[1]){
                return sb.toString();
            }
            if(visit[x][y]){
                continue;
            }
            visit[x][y] = true;
            for(int i = 0; i < 4; i++){
                int nx = x;
                int ny = y;
                int newLen = len;
                StringBuilder next = new StringBuilder(sb);
                next.append(dir[i]);
                while(nx >= 0 && nx < m && ny >= 0 && ny < n && maze[nx][ny] == 0 && !(nx == hole[0] && ny == hole[1])){
                    
                    nx += dx[i];
                    ny += dy[i];
                    newLen++;
    
                }
                if(!(nx == hole[0] && ny == hole[1])){
                    newLen--;
                    nx -= dx[i];
                    ny -= dy[i];
                }
                q.offer(new Point(nx, ny, newLen, next));
            }
        }
        
        return "impossible";
        

    }
}
class Point implements Comparable<Point>{
    int x;
    int y;
    int len;
    StringBuilder sb;
    
    public Point(int x, int y, int len,StringBuilder sb){
        this.x = x;
        this.y = y;
        this.len = len;
        this.sb = new StringBuilder(sb);
    }
    
    public int compareTo(Point p){
        if(len == p.len){
            return sb.toString().compareTo(p.sb.toString());
        }
        
        return len - p.len;
    }
}
