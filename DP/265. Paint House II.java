// // 265. Paint House II
// // DescriptionHintsSubmissionsDiscussSolution
// // There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. 
// You have to paint all the houses such that no two adjacent houses have the same color.

// // The cost of painting each house with a certain color is represented by a n x k cost matrix. For example,
//  costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... 
//  Find the minimum cost to paint all houses.

// // Note:
// // All costs are positive integers.

// // Example:
// // Input: [[1,5,3],[2,9,4]]
// // Output: 5
// // Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5; 
// //              Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5. 


// f[i][j] stand for the cur min value I chose j color
//如何找出除了 j color 外 的最小值？ 需要存前一次的 最小值和次小值的 index 
class Solution {
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








































class Solution {
    public int minCostII(int[][] costs) {
        if(costs == null || costs.length == 0){
            return 0;
        }


        int n = costs.length;
        int k = costs[0].length;
        int[][] f = new int[n][k];
        //维护两个数组记录 当前和前一个的最小值和次小值的位置
        int[] min = { -1 ,-1};
        int[] smin = { -1 , -1};
        for(int i = 0 ; i < n ; i++){
            
            for(int j = 0 ; j < k ;j++){
                if(i == 0){
                    f[i][j] = costs[i][j];
                }
                else{
                    f[i][j] = costs[i][j] +  (min[0] == j ? f[i - 1][smin[0]] : f[i - 1][min[0]]);
                }
                
                if(min[1] == -1 || f[i][j] <= f[i][min[1]]){
                    smin[1] = min[1];
                    min[1] = j;
                }
                else if(smin[1] == -1 || f[i][j] <= f[i][smin[1]]){
                    smin[1] = j;
                }
            }

            min[0] = min[1];
            smin[0] = smin[1];
            min[1] = -1;
            smin[1] = -1;
    
        }
        return f[n - 1][min[0]];
    }
}





// The idea is similar to the problem Paint House I, for each house and each color, 
// the minimum cost of painting the house with that color should be the minimum cost of painting previous houses, and make sure the previous house doesn't paint with the same color.

// We can use min1 and min2 to track the indices of the 1st and 2nd smallest cost till previous house, if the current color's index is same as min1, then we have to go with min2, otherwise we can safely go with min1.

// The code below modifies the value of costs[][] so we don't need extra space.

public int minCostII(int[][] costs) {
    if (costs == null || costs.length == 0) return 0;
        
    int n = costs.length, k = costs[0].length;
    // min1 is the index of the 1st-smallest cost till previous house
    // min2 is the index of the 2nd-smallest cost till previous house
    int min1 = -1, min2 = -1;
    
    for (int i = 0; i < n; i++) {
        int last1 = min1, last2 = min2;
        min1 = -1; min2 = -1;
        
        for (int j = 0; j < k; j++) {
            if (j != last1) {
                // current color j is different to last min1
                costs[i][j] += last1 < 0 ? 0 : costs[i - 1][last1];
            } else {
                costs[i][j] += last2 < 0 ? 0 : costs[i - 1][last2];
            }
            
            // find the indices of 1st and 2nd smallest cost of painting current house i
            if (min1 < 0 || costs[i][j] < costs[i][min1]) {
                min2 = min1; min1 = j;
            } else if (min2 < 0 || costs[i][j] < costs[i][min2]) {
                min2 = j;
            }
        }
    }
    
    return costs[n - 1][min1];
}