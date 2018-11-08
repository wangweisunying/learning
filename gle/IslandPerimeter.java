// 463. Island Perimeter
// loop over the matrix and count the number of islands;
// if the current dot is an island, count if it has any right neighbour or down neighbour;
// the result is islands * 4 - neighbours * 2
public class Solution {
    public int islandPerimeter(int[][] grid) {
        int islands = 0, neighbours = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    islands++; // count islands
                    if (i < grid.length - 1 && grid[i + 1][j] == 1) neighbours++; // count down neighbours
                    if (j < grid[i].length - 1 && grid[i][j + 1] == 1) neighbours++; // count right neighbours
                }
            }
        }

        return islands * 4 - neighbours * 2;
    }
}

class Solution {
    public int islandPerimeter(int[][] M) {
        
        if (M == null || M.length == 0) {
            return 0;
        }
        int[] deltaX = {0, 0, 1, -1};
        int[] deltaY = {1, -1, 0, 0};
        int m = M.length;
        int n = M[0].length;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(M[i][j] == 1){
                    int[] axis = {i , j};
                    break;
                }
            }
        }
        boolean[][] visited = new boolean[m][n];
        visited[axis[0]][axis[1]] = true;
        Queue<int[]> que = new LinkedList();
        que.offer(axis);
        int ct = 0;
        while (!que.isEmpty()) { 
            
            int[] cur = que.poll();
            
            for (int i = 0; i < 4; i++) {
                ct++;
                int x = cur[0] + deltaX[i];
                int y = cur[1] + deltaY[i];
                if (outBound(x, y, m, n)) {
                    continue;
                }
                if(M[x][y] != 1){
                    continue;
                }
                //if it has neighbors！！ ct --;
                ct--;
                if (visited[x][y]) {
                    continue;
                }
                visited[x][y] = true;
                que.offer(new int[]{x , y});
                }
        }
        return ct;
    }
    private boolean outBound(int x, int y, int m, int n) {
        if (x < 0 || y < 0 || x >= m || y >= n) {
            return true;
        }
        return false;
    }
}




//默认所有格子都是4条边 一旦碰上有相邻的格子 就减去一条边同一条边会减去2次
    public int getRes(int[][] M, int[] axis) {
        if (M == null || M.length == 0) {
            return 0;
        }
        int[] deltaX = {0, 0, 1, -1};
        int[] deltaY = {1, -1, 0, 0};
        int m = M.length;
        int n = M[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[axis[0]][axis[1]] = true;
        Queue<int[]> que = new LinkedList();
        que.offer(axis);
        int ct = 0;
        while (!que.isEmpty()) { 
            
            int[] cur = que.poll();
            System.out.println(cur[0] + " : " + cur[1]);
            
            for (int i = 0; i < 4; i++) {
                ct++;
                int x = cur[0] + deltaX[i];
                int y = cur[1] + deltaY[i];
                if (outBound(x, y, m, n)) {
                    continue;
                }
                if(M[x][y] != 1){
                    continue;
                }
                //if it has neighbors！！ ct --;
                ct--;
                if (visited[x][y]) {
                    continue;
                }
                visited[x][y] = true;
                que.offer(new int[]{x , y});
                
            }
            
            
            System.out.println(ct);
        }
        return ct;

    }

    private boolean outBound(int x, int y, int m, int n) {
        if (x < 0 || y < 0 || x >= m || y >= n) {
            return true;
        }
        return false;
    }