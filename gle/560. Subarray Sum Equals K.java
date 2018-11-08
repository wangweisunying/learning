// 560. Subarray Sum Equals K
// DescriptionHintsSubmissionsDiscussSolution
// Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

// Example 1:
// Input:nums = [1,1,1], k = 2
// Output: 2
// Note:
// The length of the array is in range [1, 20,000].
// The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].


// preSum + 2sum 坐
//如果nums 只含有整数 可以用slide window 坐  sum 单调增
class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer , Integer> map = new HashMap();
        int[] preSum= new int[nums.length + 1];
        for(int i = 0 ; i < nums.length ; i++){
            preSum[i + 1] = preSum[i] + nums[i];
        }
        int res = 0;
        for(int i : preSum){
            if(map.containsKey(i - k)){
                res += map.get(i - k);
            }
            if(map.containsKey(i)){
                map.put(i , map.get(i) + 1);
            }
            else{
                map.put(i, 1);
            }
        return res;

    }
}




public class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap < Integer, Integer > map = new HashMap < > ();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}