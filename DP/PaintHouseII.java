// Paint House II
// There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. 
// You have to paint all the houses such that no two adjacent houses have the same color.

// The cost of painting each house with a certain color is represented by a n x k cost matrix.
//  For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on...
//  Find the minimum cost to paint all houses.

// Example
// Given n = 3, k = 3, costs = [[14,2,11],[11,14,5],[14,3,10]] return 10

// house 0 is color 2, house 1 is color 3, house 2 is color 2, 2 + 5 + 3 = 10

public class Solution {
    /**
     * @param costs: n x k cost matrix
     * @return: an integer, the minimum cost to paint all houses
     */
    public int minCostII(int[][] costs) {
        if(costs == null || costs.length == 0 || costs[0].length == 0){
            return 0;
        }
        int n  = costs.length;
        int K = costs[0].length;
        int[][] f = new int[n + 1][K];
        
        int i , j, min , min2nd;
        for(i = 0 ; i < K ; i ++){
            f[0][i] = 0;
        }
        for(i = 1 ; i <= n ; i ++){
            //find min and 2ndMin
            min = min2nd = -1;
            for( j = 0 ; j < K ; j ++){
                if( min == -1 || f[i - 1][j] < f[i - 1][min] ){
                    min2nd = min ;
                    min = j ;
                }
                else{
                    if(min2nd == -1 || f[ i - 1][j] < f[i - 1][min2nd] ){
                        min2nd = j;
                    }
                }
            }
            
            for( j = 0 ; j < K ; j++){
                if(j != min){
                    f[i][j] = f[i - 1][min] + costs[i - 1][j]; // costs[ i - 1] 对应 f[i]
                }   
                else{
                    f[i][j] = f[i - 1][min2nd] + costs[i - 1][j];
                }
            }
            
        }
        int res = Integer.MAX_VALUE;
        for(int k = 0 ; k < K ; k ++){
            res = Math.min(res ,f[n][k]);
        }
        return res;
    }
}

public class Solution {
    /**
     * @param costs: n x k cost matrix
     * @return: an integer, the minimum cost to paint all houses
     */

    public int minCostII(int[][] C) {
        int n = C.length;
        if (n == 0) {
            return 0;
        }
        
        int K = C[0].length;
        if (K == 0) {
            return 0;
        }
        
        int[][] f = new int[n + 1][K];
        // int[][] pi = new int[n + 1][K];
        // pi[i][j] means f[i][j] chooses which f[i-1][?]
        
        int i, j, k, a, b;
        // initialization
        for (i = 0; i < K; ++i) {
            f[0][i] = 0;
        }
        
        for (i = 1; i <= n; ++i) {
            // find minimum and 2nd minimum among
            // f[i-1][0], ..., f[i - 1][K - 1]
            a = b = -1;
            for (k = 0; k < K; ++k) {
                if (a == -1 || f[i - 1][k] < f[i - 1][a]) {
                    b = a; // old minimum is now 2nd minimum
                    a = k; // new minimum is f[i-1][k]
                }
                else {
                    if (b == -1 || f[i - 1][k] < f[i - 1][b]) {
                        b = k;
                    }
                }
            }
            
            for (j = 0; j < K; ++j) {
                if (j != a) {
                    f[i][j] = f[i - 1][a] + C[i - 1][j];
                    // pi[i][j] = a;
                }
                else {
                    f[i][j] = f[i - 1][b] + C[i - 1][j];
                    // pi[i][j] = b;
                }
            }
        }
        
        int res = Integer.MAX_VALUE;
        k = 0;
        for (j = 0; j < K; ++j) {
            res = Math.min(res, f[n][j]);
            if (res == f[n][j]) {
                k = j;
            }
        }
        
        /*int[] color = new int[n];
        for (i = n; i >= 1; --i) {
            color[i - 1] = k;
            k = pi[i][k];
        }
        
        for (i = 0; i < n; ++i) {
            System.out.println("House " + i + " is painted to color " + color[i]);
        }*/
        
        return res;
    }
}