// Backpack
// Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?

// Example
// If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], 
// so that the max size we can fill this backpack is 10. If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.

// You function should return the max size we can fill in the given backpack.

// Challenge
// O(n x m) time and O(m) memory.

// O(n x m) memory is also acceptable if you do not know how to optimize memory.

// Notice
// You can not divide any item into small pieces.

//  N -1 能否拼出 N [w] 
//  f[i][j] = f[i-1][j] || f[i - 1][ j - A[i - 1]]| j - A[i - 1]>=0
public int backPack(int m, int[] nums) {
    int n = nums.length;
    boolean[][] f = new boolean[n + 1][m + 1]; //whether til index i can combine to j weigh of item which is not full
    f[0][0] = true;
    for(int i = 1 ; i <= m ; i ++){
        f[0][i] = false;
    }
    
    for(int i = 1 ; i <= n ; i++){
        for(int j = 0 ; j <= m ; j++ ){
            //do not choose
            f[i][j] = f[i - 1][j];
            //choose 
            if(j - nums[i - 1] >= 0){
                f[i][j] |= f[i - 1][j - nums[i - 1]];
            }
        }
    }
    //只需要判断 i = n的情况    
    for(int j = m ; j >= 0 ; j--){
        if(f[n][j]){
            return j;
        }
    }
    return -1;
}

































// memorization !!!! pass !
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    int max =  Integer.MAX_VALUE;
    public int backPack(int m, int[] A) {
        
        // Arrays.sort(A);
        boolean[] visited = new boolean[m + 1];
        int[] memo = new int[m + 1];
        dfs( m , A , 0 ,visited , memo );
        return m - max ;
    
    }
    private int dfs( int target , int[] A , int startIndex , boolean[] visited , int[] memo){
        if(visited[target]){
            return memo[target];
        }
        int res = target;
        for(int i = startIndex ; i < A.length ; i++){
            if(target - A[i] >=  0){
                res = dfs( target - A[i] , A , i + 1 ,visited ,memo);
            }
            else{
                break;
            }
        }
        visited[target] = true;
        memo[target] = res;
        max = Math.min(max ,res);
        return res; 
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


public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        boolean f[][] = new boolean[A.length + 1][m + 1];
        for (int i = 0; i <= A.length; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = false;
            }
        }
        f[0][0] = true;
        for (int i = 1; i <= A.length; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= A[i-1] && f[i-1][j - A[i-1]]) {
                    f[i][j] = true;
                }
            } // for j
        } // for i
        
        for (int i = m; i >= 0; i--) {
            if (f[A.length][i]) {
                return i;
            }
        }
        
        return 0;
    }
}