// Jump Game
// Given an array of non-negative integers, you are initially positioned at the first index of the array.

// Each element in the array represents your maximum jump length at that position.

// Determine if you are able to reach the last index.

// Example
// A = [2,3,1,1,4], return true.

// A = [3,2,1,0,4], return false.

// Notice
// This problem have two method which is Greedy and Dynamic Programming.

// The time complexity of Greedy method is O(n).

// The time complexity of Dynamic Programming method is O(n^2).

// We manually set the small data set to allow you pass the test in both ways.
//  This is just to let you learn how to use this problem in dynamic programming ways. 
// If you finish it in dynamic programming ways, you can try greedy method to make it accept again.

public class Solution {
    /**
     * @param A: A list of integers
     * @return: A boolean
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        boolean[] f = new boolean[n];
        f[0] = true;
        
        for(int i = 0 ; i < nums.length ; i++){
            for(int j = i + 1 ; j < nums.length ; j++){
                if(!f[j]){
                    f[j] = f[i] && (i + nums[i] >= j); 
                }
            }
        }
        return f[n - 1];
    }
}


public class Solution {
    /**
     * @param A: A list of integers
     * @return: A boolean
     */
    public boolean canJump(int[] A) {
        int n = A.length;
        boolean[] f = new boolean[n];
        
        f[0] = true;
        
        //子问题
        int i = 1 , j = 0;
        for( ; i < n ; i ++){
            for( ; j < i ;j ++){
                if(f[j] && (j + A[j] >= i)){
                    f[i] = true;
                    break; //只要有一个 成功就行
                }   
            }
        }
        return f[n - 1];
        
    }
}