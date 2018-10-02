// Subarray Sum
// Given an integer array, find a subarray where the sum of numbers is zero. Your code should return the index of the first number and the index of the last number.

// Example
// Given [-3, 1, 2, -3, 4], return [0, 2] or [1, 3].



public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> subarraySum(int[] nums) {
        List<Integer> res = new ArrayList();
        if(nums == null || nums.length == 0){
            return res;
        }
        int[] preSum = new int[nums.length + 1];
        for(int i = 1; i < preSum.length ; i++){
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        
        HashMap<Integer , Integer> map = new HashMap();
        for(int i = 0 ; i < preSum.length ; i++){
            if(map.containsKey(preSum[i])){
                res.add(map.get(preSum[i]));
                res.add(i - 1);
                return res;
            }
            else{
                map.put(preSum[i] , i);
            }


        }
        return res;
    }
}









































public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> subarraySum(int[] nums) {
        List<Integer> res = new ArrayList();
        if(nums.length == 0){
            return res;
        }
        if(nums.length == 1 && nums[0] == 0){
            res.add(0);
        }
        boolean found = false;
        for(int i = 0 ; i < nums.length - 1 ; i ++){
            if(found){
                break;
            }
            int sum  = nums[i];
            if(sum == 0){
                res.add(i);
                res.add(i);
                break;
            }
            for(int j = i + 1 ; j < nums.length ; j ++){
                sum += nums[j];
                if(sum == 0){
                    res.add(i);
                    res.add(j);
                    found = true;
                    break;
                }
            }
        }
        return res;
    }
}


// two SUm 变形
// sum [ i , j] = sum[j]  -  sum[i]  = 0;
// sum[i] == sum[j]

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number
     *          and the index of the last number
     */
    public ArrayList<Integer> subarraySum(int[] nums) {
        // write your code here
       
        int len = nums.length;
       
        ArrayList<Integer> ans = new ArrayList<Integer>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
       
        map.put(0, -1);
       
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            if (map.containsKey(sum)) {
                ans.add(map.get(sum) + 1); //  4  1 2 -3 
                ans.add(i);
                return ans;
            } 
            map.put(sum, i);
        }
       
        return ans;
    }
}