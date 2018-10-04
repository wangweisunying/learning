// SparseMatrixMultiplication
// Given two Sparse Matrix A and B, return the result of AB.

// You may assume that A's column number is equal to B's row number.

// Example
// A = [
//   [ 1, 0, 0],
//   [-1, 0, 3]
// ]

// B = [
//   [ 7, 0, 0 ],
//   [ 0, 0, 0 ],
//   [ 0, 0, 1 ]
// ]


//      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
// AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
//                   | 0 0 1 |



public class Solution {
    /**
     * @param A: a sparse matrix
     * @param B: a sparse matrix
     * @return: the result of A * B
     */
    public int[][] multiply(int[][] A, int[][] B) {
        int row = A.length;
        int col = B[0].length;
        int[][] res = new int[row][col];
        
        List<List<Integer>> listb = new ArrayList();
        for(int i = 0 ; i < B[0].length ; i ++){
            listb.add(new ArrayList());
            for(int j = 0 ; j < B.length ; j++){
                if(B[j][i] != 0){
                    listb.get(i).add(j);
                }
            }
        }

        for(int i = 0 ; i < row ; i++){
            for(int j = 0 ; j < col ; j++){
                for(int k = 0 ; k < listb.get(j).size() ; k++){
                    res[i][j] += A[i][listb.get(j).get(k)] * B[listb.get(j).get(k)][j];//搞清楚行列关系！~
                }
            }
        }
        return res;

    }
}


public class Solution {
    /**
     * @param A: a sparse matrix
     * @param B: a sparse matrix
     * @return: the result of A * B
     */

    public int[][] multiply(int[][] A, int[][] B) {
        int m = A[0].length;
        int[][] res = new int[A.length][B[0].length];
        for(int i = 0 ; i < res.length ; i++){
            for(int j = 0 ; j < res[0].length ; j ++){
                for(int k = 0 ; k < m ; k ++){
                    if(A[i][k] == 0 || B[k][j] == 0){
                        continue;
                    }
                    res[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return res;
    }
}







public class Solution {
    /**
     * @param A: a sparse matrix
     * @param B: a sparse matrix
     * @return: the result of A * B
     */

 
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A[0].length;
        int[][] res = new int[A.length][B[0].length];
        for(int i = 0 ; i < res.length ; i++){
            for(int j = 0 ; j < res[0].length ; j ++){
                for(int k = 0 ; k < m ; k ++){
                    res[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return res;
    }
}


    //优化


public class Solution {
    /**
     * @param A a sparse matrix
     * @param B a sparse matrix
     * @return the result of A * B
     */
    public int[][] multiply(int[][] A, int[][] B) {
        // Write your code here
        int n = A.length;
        int m = B[0].length;
        int t = A[0].length;
        int[][] C = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < t; k++) {
                if (A[i][k] == 0) {
                    continue;
                }
                for (int j = 0; j < m; j++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
}

public class Solution {
    /**
     * @param A a sparse matrix
     * @param B a sparse matrix
     * @return the result of A * B
     */
    public int[][] multiply(int[][] A, int[][] B) {
        // Write your code here
        int n = A.length;
        int m = B[0].length;
        int t = A[0].length;
        int[][] C = new int[n][m];

        List<List<Integer>> col = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            col.add(new ArrayList<>());
            for (int j = 0; j < m; j++) {
                if (B[i][j] != 0) {
                    col.get(i).add(j);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < t; k++) {
                if (A[i][k] == 0) {
                    continue;
                }
                for (int p = 0; p < col.get(k).size(); p++) {
                    int j = col.get(k).get(p);
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
}