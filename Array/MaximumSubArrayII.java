
// Maximum Subarray II
// Given an array of integers, find two non-overlapping subarrays which have the largest sum.
// The number in each subarray should be contiguous.
// Return the largest sum.

// Example
// For given [1, 3, -1, 2, -1, 2], the two subarrays are [1, 3] and [2, -1, 2] or [1, 3, -1, 2] and [2], they both have the largest sum 7.

// Challenge
// Can you do it in time complexity O(n) ?

// Notice
// The subarray should contain at least one number
// 所以必定存在一条分割线

// 分开这两个 subarrays

// 所以 最后的部分里：

//   max = Integer.MIN_VALUE;

//         for(int i = 0; i < size - 1; i++){

//             max = Math.max(max, left[i] + right[i + 1]);

//         }

//         return max;

// 这里是在枚举 这条分割线的位置

// 然后 left[] 和 right[] 里分别存的是，某个位置往左的 maximum subarray 和往右的 maximum subarray


public class Solution {
    /*
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */
    public int maxTwoSubArrays(List<Integer> nums) {
        int[] left = new int[nums.size()];
        int[] right = new int[nums.size()];
        
        int curleft = nums.get(0);;
        left[0] = nums.get(0);
        for(int i = 1 ; i < nums.size() ; i++){
            curleft = Math.max(curleft + nums.get(i) , nums.get(i)));
            left[i] = Math.max(left[i - 1] , curleft);
        }
        int curright = nums.get(nums.size() - 1);
        right[nums.size() - 1] = nums.get(nums.size() - 1);
        for(int i = nums.size() - 2 ; i >=  0 ; i --){
            curright = Math.max(curright + nums.get(i) , nums.get(i)));
            right[i] = Math.max(right[i + 1] , curright));
        }


        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < nums.size() - 1 ; i++){
            max = Math.max(max , left[i] + right[i + 1]);
        }
        return max;
    }
}