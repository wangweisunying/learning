// MaximumAverageSubarrayII
// Given an array with positive and negative numbers, find the maximum average subarray which length should be greater or equal to given length k.

// Example
// Given nums = [1, 12, -5, -6, 50, 3], k = 3

// Return 15.667 // (-6 + 50 + 3) / 3 = 15.667

// Notice
// It's guaranteed that the size of the array is greater or equal to k.

public class Solution {
    /**
     * @param nums: an array with positive and negative numbers
     * @param k: an integer
     * @return: the maximum average
     */
    public double maxAverage(int[] nums, int k) {
        double s = Double.MAX_VALUE;
        double e = Double.MIN_VALUE;
        for(int x : nums){ // 是找平均数 所以 max 就是max， min 就是min
            s = Math.min(x , s);
            e = Math.max(x , e);
        }
        
        while(s + 1e-6 < e){ // 精确到小数位 
            double mid = s + (e - s) / 2;
            if(check(mid , nums , k)){
                s = mid;
            }
            else{
                e = mid;
            }
        }
        if(check(e , nums , k)){
            return e;
        }
        return s;
    }


    boolean check( double avg,int nums[], int k){
        double[] sum = new double[nums.length + 1];
        double[] preSum = new double[nums.length + 1];
        for(int i = 1; i <= nums.length ; i++){
            sum[i] = sum[i - 1] + (nums[i - 1] - avg);
            preSum[i] = Math.min(preSum[i - 1 ] , sum[i]);
            if(i >= k && sum[i] - preSum[i - k] >=0){
                return true;
            }

        }
        return false;

    } 
}



// 二分出 average 之后，把数组中的每个数都减去 average，然后的任务就是去求这个数组中，是否有长度 >= k 的 subarray，他的和超过 0。
// 这一步用类似 Maximum Subarray 的解法来做就好了

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
                // 找一段长度 >= k 的子数组使它的平均值满足条件。
            // Sum 是 0 到 i 所有元素之和，而 prevSum 是 0 到 i - k 的所有元素之和，这样可以保证用 Sum - PreSum 的数组长度比 k 长
            // prevMin 来记录 prevSum 的最小值，通过 sum 减去 prevMin 来获得子数组可能的最大值
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