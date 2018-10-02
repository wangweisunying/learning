// Beautiful Arrangement
// Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by these N numbers successfully if one of the following is true for the ith position (1 <= i <= N) in this array:

// The number at the ith position is divisible by i.
// i is divisible by the number at the ith position.
// Now given N, how many beautiful arrangements can you construct?
// Example
// Input: 2
// Output: 2
// Explanation: 

// The first beautiful arrangement is [1, 2]:

// Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).

// Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).

// The second beautiful arrangement is [2, 1]:

// Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).

// Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.

public class Solution {
    /**
     * @param N: The number of integers
     * @return: The number of beautiful arrangements you can construct
     */

     //dfs
    int res = 0 ;
    public int countArrangement(int n) {
        
        int[] nums = new int[n];
        int i = 0;
        for( ; i < n  ;i ++ )
        nums[i] = i + 1 ;
        dfs(nums , new boolean[n] , n , 1);
        return res;
    }
    private void dfs(int[] nums , boolean[] visited , int ct , int index ){
        if(ct == 0){
            res ++;
        }
        for(int i = 0 ; i < nums.length ; i ++){
            if(visited[i]){
                continue;
            }
            if(nums[i] % (index) == 0 || (index) % nums[i] == 0){
                visited[i] = true;
                dfs(nums, visited , ct - 1 , index + 1);
                visited[i] = false;
            }   
        }
    }
    
}

public class Solution {
    /**
     * @param N: The number of integers
     * @return: The number of beautiful arrangements you can construct
     */
        int res = 0 ;
    public int countArrangement(int n) {
        
        int[] nums = new int[n];
        int i = 0;
        for( ; i < n  ;i ++ )
        nums[i] = i + 1 ;
        dfs(nums , new boolean[n] , 1);
        return res;
    }
    private void dfs(int[] nums , boolean[] visited , int index){
        if(index == nums.length + 1){
            res ++;
        }
        for(int i = 0 ; i < nums.length ; i ++){
            if(visited[i]){
                continue;
            }
            if(nums[i] % (index) == 0 || (index) % nums[i] == 0){
                visited[i] = true;
                dfs(nums, visited , index + 1);
                visited[i] = false;
            }   
        }
    }
}





public class Solution {
    /**
     * @param N: The number of integers
     * @return: The number of beautiful arrangements you can construct
     */

    //dp

    public int countArrangement(int n) {
        
        
    }
    
    
}
