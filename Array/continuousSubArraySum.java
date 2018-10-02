// Continuous Subarray Sum
// Given an integer array, find a continuous subarray where the sum of numbers is the biggest
// . Your code should return the index of the first number and the index of the last number. (If their are duplicate answer, return the firstone you find)

// Example
// Give [-3, 1, 3, -3, 4], return [1,4].

public class Solution {
    /*
     * @param A: An integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> continuousSubarraySum(int[] nums) {
        List<Integer> res = new ArrayList();

        int max = Integer.MIN_VALUE;
        int curMax = 0;
        int s = 0 ;
        int e = 0 ;
        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i] > curMax + nums[i]){
                s = i;
            }
            curMax = Math.max(nums[i] , curMax + nums[i]);
            if(max < curMax){
                res.clear();
                e = i;
                res.add(s);
                res.add(e);
            }
            max = Math.max(max , curMax);
        }
        return res;

    }
}