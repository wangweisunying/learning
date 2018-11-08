
// 827. Making A Large Island
// DescriptionHintsSubmissionsDiscussSolution
// In a 2D grid of 0s and 1s, we change at most one 0 to a 1.

// After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).

// Example 1:

// Input: [[1, 0], [0, 1]]
// Output: 3
// Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
// Example 2:

// Input: [[1, 1], [1, 0]]
// Output: 4
// Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
// Example 3:

// Input: [[1, 1], [1, 1]]
// Output: 4
// Explanation: Can't change any 0 to 1, only one island with area = 4.
 

// Notes:

// 1 <= grid.length = grid[0].length <= 50.
// 0 <= grid[i][j] <= 1.



//用dfs 将所有岛屿面积求出 ,标记 并且建立map 存储，最后遍历0点 将不同标记的面积相加 并 + 1 本身的面积 
class Solution {
    int m , n ;
    int[] deltaX = {0 , 0 , 1 , -1};
    int[] deltaY = {1 , -1 , 0 , 0 };
    public int largestIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        HashMap<Integer , Integer> map = new HashMap();
        int label = 2;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(grid[i][j] == 1){
                    int area = dfs(grid , i , j, label);
                    map.put(label++ , area);
                }
            }
        }

        int max = 1;
        if(!map.isEmpty()){
            max = map.get(2);
        }
        
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(grid[i][j] == 0){
                    HashSet<Integer> set = new HashSet();
                    for(int k = 0 ; k < 4 ; k++){
                        int x = i + deltaX[k];
                        int y = j + deltaY[k];
                        if(x < 0 || y < 0 || x >=m || y >=n ){
                            continue;
                        }
                        if(grid[x][y] > 1){
                            set.add(grid[x][y]);
                        }
                    }
                    int tmp = 1;
                    for(int tmp1 : set){
                        tmp += map.get(tmp1);
                    }
                    max = Math.max(tmp , max);
                }
            }
        }
        return max;
    }
    private int dfs(int[][] grid , int x , int y, int label){
        grid[x][y] = label;
        int tmp = 1;
        for(int i = 0 ; i < 4 ; i++){
            int xx = x + deltaX[i];
            int yy = y + deltaY[i]; 
            if(xx < 0 || yy < 0 || xx >=m || yy >=n || grid[xx][yy] != 1){
                continue;
            }
            tmp += dfs(grid , xx ,yy , label);
        }
        return tmp;
    }
}