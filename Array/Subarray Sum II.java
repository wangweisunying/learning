
// Subarray Sum II
// Given an integer array, find a subarray where the sum of numbers is in a given interval. Your code should return the number of possible answers. (The element in the array should be positive)

// Example
// Given [1,2,3,4] and interval = [1,3], return 4. The possible answers are:

// [0, 0]
// [0, 1]
// [1, 1]
// [2, 2]
public class Solution {
    /**
     * @param A: An integer array
     * @param start: An integer
     * @param end: An integer
     * @return: the number of possible answer
     */
    public int subarraySumII(int[] nums, int start, int end) {
        int[] preSum = new int[nums.length + 1];
        for(int i = 0 ; i < nums.length ; i++){
            preSum[i + 1] = preSum[i] + nums[i];
        }
        
        int ct = 0;
        for(int i = 0 ; i < nums.length ; i++){
            if(preSum[i + 1] >= start && preSum[i + 1] <= end){
                ct++;
            }
            int l = preSum[i + 1] - end;
            int r = preSum[i + 1] - start;
            ct += findmoreFirst(preSum ,i + 1 , r) - findmoreFirst(preSum , i + 1 , l - 1);
        }
        return ct;
    }

    private int findmoreFirst(int[] nums ,int end, int target){
        int s = 0 , e = end;
        while(s + 1 < e){
            int mid = (s + e) / 2;
            if(nums[mid] <= target){
                s = mid;
            }
            else{
                e = mid;
            }
        }
        return e;
    }
}


public class Solution {
    /**
     * @param A: An integer array
     * @param start: An integer
     * @param end: An integer
     * @return: the number of possible answer
     */
    public int subarraySumII(int[] A, int start, int end) {
        // write your code here
        int n = A.length;
        int[] preSum = new int[n + 1];
        int ans = 0;
        for(int i = 1; i <= n; i++){
            preSum[i] = preSum[i - 1] + A[i - 1];
            if (start <= preSum[i] && preSum[i] <= end)
                ans++;
            int left = preSum[i] - end;
            int right = preSum[i] - start;
            ans += getCount(preSum,i,right) - getCount(preSum,i,left - 1);
        }
        return ans;
    }
   
    // find the first index which is bigger than the target
    private int getCount(int[] A, int end, int target){
        int left = 0;
        int right = end;
        while(left + 1 < right){
            int mid = left + (right - left) / 2;
            if(A[mid] > target){
                right = mid;
            }else{
                left = mid;
            }
        }
        return right;
    }
    
}

public class Solution {
    /**
     * @param A an integer array
     * @param start an integer
     * @param end an integer
     * @return the number of possible answer
     */
    int find(int[] A, int len, int value) {
        if (len-1 >= 0 && A[len-1] < value )
            return len;
        
        int l = 0, r = len-1, ans = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (value <= A[mid]) {
                ans = mid;
                r = mid - 1;
            }  else
                l = mid + 1;
        }
        return ans;
    }

    public int subarraySumII(int[] A, int start, int end) {
        // Write your code here
        int len = A.length;
        for (int i = 1; i <len; ++i)
            A[i] += A[i-1];

        int cnt = 0;
        for (int i = 0; i < len; ++i) {
            if (A[i] >= start && A[i] <= end)
                cnt ++;
            int l = A[i] - end;
            int r = A[i] - start;
            cnt += find(A, i, r+1) - find(A, i, l); 
        }
        return cnt;
    }
}