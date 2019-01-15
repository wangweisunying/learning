// 256. Paint House

// Share
// There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. 
// You have to paint all the houses such that no two adjacent houses have the same color.

// The cost of painting each house with a certain color is represented by a n x 3 cost matrix. 
// For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

// Note:
// All costs are positive integers.

// Example:

// Input: [[17,2,17],[16,16,5],[14,3,19]]
// Output: 10
// Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue. 
//              Minimum cost: 2 + 5 + 3 = 10.

class Solution {
    public int minCost(int[][] costs) {
        int n = costs.length;

        // f[i][j] stand for the curhouse paint 0 , 1, 2 most min cost;
        int[][] f = new int[n + 1][3];
        for(int i = 1 ; i <= n ; i++){
            f[i][0] = Math.min(f[i - 1][1] , f[i - 1][2]) + costs[i - 1][0];
            f[i][1] = Math.min(f[i - 1][0] , f[i - 1][2]) + costs[i - 1][1];
            f[i][2] = Math.min(f[i - 1][1] , f[i - 1][0]) + costs[i - 1][2];
        }
        return Math.min(Math.min(f[n][0] , f[n][1]) , f[n][2]);
    }
}


class Solution {
    public int minCost(int[][] costs) {
        return minCostII(costs);
        
    }
    public int minCostII(int[][] costs) {
        if(costs == null || costs.length == 0) return 0;
        int m = costs.length , n = costs[0].length;
        int[][] f = new int[m + 1][n];
        int index1 = -1 , sindex1 = -1;
        for(int i = 1 ; i <= m ; i++){
            int index2 = -1 , sindex2 = -1;
            for(int j = 0 ; j < n ; j++){
                if(index1 == -1 && sindex1 == -1){
                    f[i][j] = costs[i - 1][j];
                }
                else{
                    f[i][j] = costs[i - 1][j] + (j == index1 ? f[i - 1][sindex1] : f[i - 1][index1]);  
                }

                // update the index now to pre     
                if(index2 == -1 || f[i][index2] >= f[i][j]){
                    sindex2 = index2;
                    index2 = j;
                }
                else if(sindex2 == -1 || f[i][sindex2] >= f[i][j]){
                    sindex2 = j;
                }
            }
            index1 = index2;
            sindex1 = sindex2;
        }
        return f[m][index1];
    }
}
