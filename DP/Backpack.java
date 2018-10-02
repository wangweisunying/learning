// Backpack
// Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?

// Example
// If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], so that the max size we can fill this backpack is 10. If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.

// You function should return the max size we can fill in the given backpack.

// Challenge
// O(n x m) time and O(m) memory.

// O(n x m) memory is also acceptable if you do not know how to optimize memory.

// Notice
// You can not divide any item into small pieces.

public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    int res =  - 1;
    public int backPack(int m, int[] A) {
        
        Arrays.sort(A);
        dfs( m , A , 0 , 0);
        return res;
    }
    private void dfs( int target , int[] A , int tmp , int startIndex){
        if(tmp <= target){
            res = Math.max(res , tmp);
        }
        
        for(int i = startIndex ; i < A.length ; i++){
            if(tmp + A[i] > target){
                return;
            }
            dfs( target , A , tmp + A[i], i + 1);
        }
    }
}

public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    //  N -1 能否拼出 N [w] 
    //  f[i][j] = f[i-1][j] || f[i - 1][ j - A[i - 1]]| j - A[i - 1]>=0
    public int backPack(int m, int[] A) {
        int n = A.length;
        boolean[][] f = new boolean[n + 1 ][m + 1];
        f[0][0] = true;
        int i , j;
        for( i = 1 ; i <= m ; i ++){
            f[0][i] = false;
        }
        
        for(i = 1 ; i <= n ; i ++){
            for( j = 0 ; j <= m ; j ++){
                f[i][j] = f[i - 1][j] ;
                if(j - A[i - 1] >= 0){
                    f[i][j] |= f[i - 1][j - A[i - 1]];
                }
            } 
        }
        
        for(i = m ; i >= 0 ; i --){
            if(f[n][i]){
                break;
            }
        }
        return i;
    }
}