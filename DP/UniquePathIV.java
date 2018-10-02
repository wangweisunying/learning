// Unique Path IV
// Give two integers to represent the height and width of the grid. The starting point is in the upper left corner and the ending point is in the upper right corner. 
// You can go to the top right, right or bottom right. Find out the number of paths you can reach the end. And the result needs to mod 1000000007.

// Example
// input:
// height = 3
// width = 3
// output:
// 2
// Notice
// width > 1, height > 1


public class Solution {
    /**
     * @param height: the given height
     * @param width: the given width
     * @return: the number of paths you can reach the end
     */
    public int uniquePath(int m, int n) {
          long[][] f = new long[m][n];
        for(int j = 0 ; j < n ; j ++ ){
            for(int i = 0 ; i < m  ; i++){
                if(i == j){
                    f[i][j] = 1;
                    break;
                }
                if(j == 0){
                    continue;
                }
                if(i == 0){
                    if( j == n - 1){
                        System.out.println(f[i][j - 1] + "  " + f[i + 1][j - 1]);
                     }
                    f[i][j] = (f[i][j - 1] + f[i + 1][j - 1])% 1000000007;
                    continue;
                }
                if(i == m - 1){
                 
                    f[i][j] = (f[i][j - 1] + f[i - 1][j - 1])% 1000000007;
                    continue;
                }
                f[i][j] = (f[i][j - 1] + f[i - 1][j - 1] + f[i + 1][j - 1])% 1000000007;
            }
            
        }
        return (int)f[0][n - 1] ;
        
    }
}