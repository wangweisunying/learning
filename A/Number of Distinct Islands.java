// Number of Distinct Islands
// Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) 
// You may assume all four edges of the grid are surrounded by water.

// Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

// Notice that:

// 11
// 1
// and

//  1
// 11
// are considered different island shapes, because we do not consider reflection / rotation.

// Example
// Given grid = 
// [
// [1,1,0,0,0],
// [1,1,0,0,0],
// [0,0,0,1,1],
// [0,0,0,1,1]
// ]
// return 1
// Given grid = 
// [
// [1,1,0,1,1],
// [1,0,0,0,0],
// [0,0,0,0,1],
// [1,1,0,1,1]
// ]
// return 3
// Notice
// The length of each dimension in the given grid does not exceed 50.


// [[1,0,0]
// ,[1,0,0]
// ,[0,1,1]]

public class Solution {

    /**
     * @param grid: a list of lists of integers
     * @return: return an integer, denote the number of distinct islands
     */
    int[] deltaX = {0, 0, 1, -1};
    int[] deltaY = {1, -1, 0, 0};
    int m, n;
    public int numDistinctIslands(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        HashSet<String> set = new HashSet();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    List<int[]> list = new ArrayList();
                    dfs(grid, i, j, list);
                    Collections.sort(list , new Comparator<int[]>(){
                        @Override
                        public int compare(int[] arr1 , int[] arr2){
                            if(arr1[0] == arr2[0]){
                                return arr1[1] - arr2[1];
                            }
                            return arr1[0] - arr2[0];

                        }
                    });
                    set.add(getPath(list));
                }
            }
        }
        return set.size();
    }
    private String getPath(List<int[]> list){
        String res ="";
        int x = list.get(0)[0];
        int y = list.get(0)[1];
        for(int i = 1 ; i < list.size() ; i++){
            res += (list.get(i)[0] - x) + "_" + (list.get(i)[1] - y);
            x = list.get(i)[0];
            y = list.get(i)[1];
        }
        return res;
    }
    private void dfs(int[][] grid, int i, int j, List<int[]> list) {
        if (grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        list.add(new int[]{i, j});
        for (int k = 0; k < 4; k++) {
            int x = i + deltaX[k];
            int y = j + deltaY[k];
            if (x < 0 || y < 0 || x >= m || y >= n) {
                continue;
            }
            dfs(grid, x, y, list);
        }
    }
}

















public class Solution {

    /**
     * @param args the command line arguments
     */

    class Point{
        int x , y ;
        Point(int x ,int y){
            this.x = x;
            this.y = y;
        } 
    }

    class Shape{
        Point axis;
        Queue<Point> que ;
        int size ,  hashCode;
        String path;
        Shape(){
            path = "";
            axis = new Point(-1 ,-1);
            size = 0;
            que = new PriorityQueue(new Comparator<Point>(){
                @Override
                public int compare(Point p1 ,Point p2){
                    if(p1.x == p2.x){
                        return p1.y - p2.y;
                    }
                    return p1.x - p2.x;
                }
            });
        }
        void add(Point p){
            que.offer(p);
        }
        @Override
        public int hashCode(){
            int res = 0;
            int x = que.peek().x;
            int y = que.peek().y;
            axis.x = x;
            axis.y = y;
            while(!que.isEmpty()){
                size++;
                Point cur = que.poll();
                res += (cur.x - x) * 1 + (cur.y - y) * 77;
                path += (cur.x - x) * 1 + (cur.y - y) * 77;
//                System.out.println( cur.x + " : " + cur.y + " = "  + res);
            }
            hashCode = res;
//            System.out.println(size);
            return res;
        }
        @Override
        public boolean equals(Object o){
            Shape s = (Shape)o;
            if(this.size == s.size){
                if(this.size != 1){
//                    
                    System.out.println(this.axis.x +" : " + this.axis.y + " = "+ this.hashCode + " == " +s.axis.x +" : " + s.axis.y + " = "+ s.hashCode);
//                   
                }
                
                return this.hashCode == s.hashCode && this.path.equals(s.path);   
            }
            return false;
        }
    }
    
    
    public int numberofDistinctIslands(int[][] grid) {
        int[] deltaX = {-1, 1 ,0 , 0};
        int[] deltaY = {0 , 0 , -1 , 1};


        Queue<Point> que = new LinkedList();


        HashSet<Shape> set = new HashSet();
        for(int i = 0 ; i < grid.length ; i ++){
            for(int j = 0 ; j < grid[0].length ; j++){
                if(grid[i][j] == 1){
                    grid[i][j] = 0;
                    que.offer(new Point(i , j));
                    Shape shape = new Shape();
                    shape.add(que.peek());
                    while(!que.isEmpty()){
                        Point cur = que.poll();
                        
                        
                        for(int k = 0 ; k < 4 ; k++){
                            int x = cur.x + deltaX[k];
                            int y = cur.y + deltaY[k];
                            if(outBound(x , y , grid.length , grid[0].length)){
                                continue;
                            }
                            if(grid[x][y] == 0){
                                continue;
                            }
                            Point p = new Point( x , y);
                            que.offer(p);
                            shape.add(p);
                            grid[x][y] = 0;
//                            System.out.println(x + ": " +y );
                            
                        }
                    }
                    set.add(shape);
                }
            }
        }
        return set.size();
    }
    private boolean outBound(int x , int y , int m ,int n){
        if(x < 0 || x >= m || y < 0 || y >= n){
            return true;
        }
        return false;
    }
}


public class Solution {
    /**
     * @param grid: a list of lists of integers
     * @return: return an integer, denote the number of distinct islands
     */
    public int numberofDistinctIslands(int[][] grid) {
        // write your code here
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        Set<List<List<Integer>>> set = new HashSet<>();
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j <grid[0].length; j++){
                if(grid[i][j] == 1){
                    List<List<Integer>> ls = bfs(grid, i, j);
                    set.add(ls);
                }
            }
        }
        return set.size();
    }
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    List<List<Integer>> bfs(int[][] grid, int a, int b){
        List<List<Integer>> res = new ArrayList<>();
        int m = grid.length;
        int n = grid[0].length;
        Queue<Integer> qx = new LinkedList<Integer>();
        Queue<Integer> qy = new LinkedList<Integer>();
        qx.offer(a);
        qy.offer(b);
        while(!qx.isEmpty()){
            int x = qx.poll();
            int y = qy.poll();
            List<Integer> ls = new ArrayList<>();
            ls.add(x - a);
            ls.add(y - b);
            res.add(ls);
            for(int i = 0; i < 4; i++){
                int nx = x +dx[i];
                int ny = y + dy[i];
                if(nx >= 0 &&nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1){
                    grid[nx][ny] = 0;
                    qx.offer(nx);
                    qy.offer(ny);
                }
            }
        }
        return res;
    }
}