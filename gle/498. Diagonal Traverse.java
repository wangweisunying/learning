// 498. Diagonal Traverse
// DescriptionHintsSubmissionsDiscussSolution
// Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.

 

// Example:

// Input:
// [
//  [ 1, 2, 3 ],
//  [ 4, 5, 6 ],
//  [ 7, 8, 9 ]
// ]

// Output:  [1,2,4,7,5,3,6,8,9]
public class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return new int[0];
        int m = matrix.length, n = matrix[0].length;
        
        int[] result = new int[m * n];
        int row = 0, col = 0, d = 0;
        int[][] dirs = {{-1, 1}, {1, -1}};
        
        for (int i = 0; i < m * n; i++) {
            result[i] = matrix[row][col];
            row += dirs[d][0];
            col += dirs[d][1];
            //分情况 修正 方向
            if (row >= m) { row = m - 1; col += 2; d = 1 - d;}
            if (col >= n) { col = n - 1; row += 2; d = 1 - d;}
            if (row < 0)  { row = 0; d = 1 - d;}
            if (col < 0)  { col = 0; d = 1 - d;}
        }
        return result;
    }
}







public class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return new int[0];
        int m = matrix.length, n = matrix[0].length;
        
        int[] result = new int[m * n];
        int row = 0, col = 0, d = 0;
        int[][] dirs = {{-1, 1}, {1, -1}};
        
        for (int i = 0; i < m * n; i++) {
            result[i] = matrix[row][col];
            row += dirs[d][0];
            col += dirs[d][1];
            //分情况 修正 方向
            if (row >= m) { row = m - 1; col += 2; d = 1 - d;}
            if (col >= n) { col = n - 1; row += 2; d = 1 - d;}
            if (row < 0)  { row = 0; d = 1 - d;}
            if (col < 0)  { col = 0; d = 1 - d;}
        }
        
        return result;
    }
}




//

class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return new int[]{};
        int m = matrix.length;
        int n = matrix[0].length;
        int[] res = new int[m * n];
        int index = 0;
        int dirIndex = 0;
        int[] deltaX = {0 ,  1 , 1 , -1};
        int[] deltaY = {1 , -1 , 0 , 1};
        int x = 0;
        int y = 0;
        if(matrix.length == 1){
            return matrix[0];
        }
        if(n == 1){
            for(int i = 0 ; i < matrix.length ; i++){
                res[i] = matrix[i][0];
            }
            return res;
        }



        res[index++] = matrix[x][y];
        while(index < m * n){
            x += deltaX[dirIndex];
            y += deltaY[dirIndex];
            while(x < 0 || y < 0 || x >= m || y >= n){
                x -= deltaX[dirIndex];
                y -= deltaY[dirIndex];
                dirIndex +=2;
                dirIndex %= 4;
                x += deltaX[dirIndex];
                y += deltaY[dirIndex];
            }
            res[index++] = matrix[x][y];
            if(dirIndex == 1 || dirIndex == 3){
                x += deltaX[dirIndex];
                y += deltaY[dirIndex];
                while(index < m * n && x >= 0 && y >= 0 && x < m && y < n){
                    res[index++] = matrix[x][y];
                    x += deltaX[dirIndex];
                    y += deltaY[dirIndex];
                }
                x -= deltaX[dirIndex];
                y -= deltaY[dirIndex];
            }
            ++dirIndex;
            dirIndex %= 4;
        }
        return res;
    }
}

// [[1,2,3],[4,5,6],[7,8,9]]
// Your answer
// [1,2,4,7,5,3,5,7,5]
// Expected answer
// [1,2,4,7,5,3,6,8,9]