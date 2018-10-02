
// Maximum Average Subarray II
// Given an array with positive and negative numbers, find the maximum average subarray which length should be greater or equal to given length k.

// Example
// Given nums = [1, 12, -5, -6, 50, 3], k = 3

// Return 15.667 // (-6 + 50 + 3) / 3 = 15.667

// Notice
// It's guaranteed that the size of the array is greater or equal to k.


public class Solution {
    /*
     * @param nums: an array with positive and negative numbers
     * @param k: an integer
     * @return: the maximum average
     */
    public double maxAverage(int[] nums, int k) {
        int n = nums.length;
        long [] sum = new long[n + 1];
        int i;
        sum[0] = 0;
        for(i = 1; i <= n ; i++){
            
            sum[i] += sum[i - 1] + (long)nums[i - 1]; 
            System.out.println(sum[i]);
        }
        double max = Integer.MIN_VALUE;
        for( i = k ; i <= n ; i++){
            double curmax = Integer.MIN_VALUE;
            for(int j = 0 ; j <= n - i ; j++){
                curmax = Math.max(curmax , (sum[j + i] - sum[j])/ (double)i) ;
            }
            max = Math.max(max , curmax);
        }
        return max;
    }
}


public class Solution {
    /**
     * @param nums an array with positive and negative numbers
     * @param k    an integer
     * @return the maximum average
     */
    public double maxAverage(int[] nums, int k) {
        // Write your code here
        double l = -1e12;
        double r = 1e12;
        double eps = 1e-6;

        while (l + eps < r) {
            double mid = l + (r - l) / 2;

            if (check(nums, mid, k)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l;
    }

    boolean check(int nums[], double avg, int k) {
        double[] sum = new double[nums.length + 1];
        double[] min_pre = new double[nums.length + 1];

        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + (nums[i - 1] - avg);
            min_pre[i] = Math.min(min_pre[i - 1], sum[i]);

            if (i >= k && sum[i] - min_pre[i - k] >= 0) {
                return true;
            }
        }
        return false;
    }
}