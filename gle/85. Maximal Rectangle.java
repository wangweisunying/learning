// 85. Maximal Rectangle
// DescriptionHintsSubmissionsDiscussSolution
// Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

// Example:
 
// Input:
// [
//   ["1","0","1","0","0"],
//   ["1","0","1","1","1"],
//   ["1","1","1","1","1"],
//   ["1","0","0","1","0"]
// ]
// Output: 6


// 84 单调栈 掌握！

class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;
        int m = matrix.length; 
        int n = matrix[0].length;
        int[][] f = new int[m][n];
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                f[i][j] = (matrix[i][j] - '0') == 0 ? 0 : (i - 1 >=0 ? f[i - 1][j] : 0) + (matrix[i][j] - '0');
            }
        }
        
        int max = 0;
        for(int[] x : f){
            max = Math.max(max ,largestRectangleArea(x));
        }
        return max;        
    }
    public int largestRectangleArea(int[] height) {
        int len = height.length;
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        for(int i = 0; i <= len; i++){
            int h = (i == len ? 0 : height[i]);
            if(s.isEmpty() || h >= height[s.peek()]){
                s.push(i);
            }else{
                int tp = s.pop();
                maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;
            }
        }
        return maxArea;
    }
}