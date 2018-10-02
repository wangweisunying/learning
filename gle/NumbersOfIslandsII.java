// // Number of Islands II
// // Given a n,m which means the row and column of the 2D matrix and an array of pair A( size k). Originally, the 2D matrix is all 0 which means there is only sea in the matrix. 
// The list pair has k operator and each operator has two integer A[i].x, A[i].y means that you can change the grid matrix[A[i].x][A[i].y] 
// from sea to island. Return how many island are there in the matrix after each operator.

// Example
// Given n = 3, m = 3, array of pair A = [(0,0),(0,1),(2,2),(2,1)].

// return [1,1,2,2].

// Notice
// 0 is represented as the sea, 1 is represented as the island. If two 1 is adjacent, we consider them in the same island. We only consider up/down/left/right adjacent.

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

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
     * @param n: An integer
     * @param m: An integer
     * @param operators: an array of point
     * @return: an integer array
     */
    
    int[] f;
    boolean[] island;
    int ct , row , col ; 
    
    private int find(int x){
        if(f[x] == x){
            return x;
        }
        return f[x] = find(f[x]);
    }
    private void union(int a ,int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA != rootB){
            f[rootA] = rootB;
            ct --;
        }
    }
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        row = n;
        col = m;
        List<Integer> res = new ArrayList();
        if(operators == null || operators.length == 0){
            return res;
        }
        
        int[] deltaX = {-1 , 1, 0 , 0};
        int[] deltaY = {0 , 0, -1 ,1};
        
        f = new int[n * m];
        island = new boolean[n * m];
        ct = 0;
        // init
        int i , j; 
        for(i = 0 ; i < n ; i ++){
            for( j = 0 ; j < m ; j++){
                f[i * m + j] = i * m + j;
            } 
        }
        
        for(Point cur : operators){
            if(!island[cur.x * m + cur.y]){
                ct++;
                island[cur.x * m + cur.y] = true;   
            }
            for(i = 0 ; i < 4 ;i++){
                int x = cur.x + deltaX[i];
                int y = cur.y + deltaY[i];
                if(outBound(x , y)){
                    continue;
                }
                if(island[x * m + y]){ //上下左右 如果是岛屿 union 一下 ct-- union 只 union father , 如果 是同一个集合 ct 不变
                    union(x * m + y , cur.x * m + cur.y);
                    continue;
                }
            }
            res.add(ct);

        }
        return res;
    }
    private boolean outBound(int x ,int y){
        if(x < 0 || x >= row || y < 0 || y >= col){
            return true;
        }
        return false;
    }
}








// BFS (MNK)
public class Solution {
    /**
     * @param n: An integer
     * @param m: An integer
     * @param operators: an array of point
     * @return: an integer array
     */
    int row , col;
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        List<Integer> res = new ArrayList();
        if(operators == null || operators.length == 0){
            return res;
        }
        int[][] matrix = new int[n][m];
        int[] deltaX = {-1 , 1, 0 , 0};
        int[] deltaY = {0 , 0, -1 ,1};
        row = n;
        col = m;
        for(Point p : operators){
            boolean[][] visited = new boolean[n][m];
            matrix[p.x][p.y] = 1;
            int sum = 0;
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < m ; j++){
                    if(matrix[i][j] == 1 && !visited[i][j]){
                        sum ++;
                        Queue<Point> que = new LinkedList();
                        que.offer(new Point(i , j));
                        visited[i][j] = true;

                        while(!que.isEmpty()){
                            int size = que.size();
                            for(int k = 0 ; k < size ; k++){
                                Point cur = que.poll();
                                for(int l = 0 ; l < 4 ; l ++){
                                    int x = cur.x + deltaX[l];
                                    int y = cur.y + deltaY[l];
                                    if(outBound(x , y)){
                                        continue;
                                    }
                                    if(visited[x][y]){
                                        continue;
                                    }
                                    if(matrix[x][y] == 0){
                                        continue;
                                    }
                                    que.offer(new Point(x , y));
                                    visited[x][y] = true;
                                }
                                

                            }
                        }
                    }
                }
            }
            res.add(sum);
        }
        return res;        
    }
    private boolean outBound(int x ,int y){
        if(x < 0 || x >= row || y < 0 || y >= col){
            return true;
        }
        return false;
    }
}



