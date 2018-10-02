// 3Sum
// Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

// Example
// For example, given array S = {-1 0 1 2 -1 -4}, A solution set is:

// (-1, 0, 1)
// (-1, -1, 2)
// Notice
// Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)

// The solution set must not contain duplicate triplets.

public class Solution {
    /**
     * @param numbers: Give an array numbers of n integer
     * @return: Find all unique triplets in the array which gives the sum of zero.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        if(nums == null || nums.length < 3){
            return res;
        }
        Arrays.sort(nums);
        for(int k = 2 ; k < nums.length ; k++ ){
            if(k + 1 < nums.length && nums[k] == nums[k + 1]){
                continue;
            }
            int s = 0 , e = k - 1 ;
            while(s < e){
                if(nums[s] + nums[e] == -nums[k]){
                    res.add(new ArrayList(Arrays.asList(nums[s] , nums[e] , nums[k])));
                    s++;
                    e--;
                    while(s < e && nums[s] == nums[s - 1]){
                        s++;
                    }
                    while(s < e && nums[e] == nums[e + 1]){
                        e--;
                    }
                }
                else if(nums[s] + nums[e] < -nums[k]){
                    s++;
                }
                else{
                    e--;
                }
            }
        }
        return res;
    }
}



public class Solution {
    /**
     * @param numbers: Give an array numbers of n integer
     * @return: Find all unique triplets in the array which gives the sum of zero.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        if(nums == null || nums.length < 3){
            return res;
        }
        Arrays.sort(nums);
        int i;
        for(i = 2 ; i < nums.length ; i++){
            if(i < nums.length - 1 && nums[i] == nums[i + 1]){
                continue;
            }
            
            int j = 0 , k = i - 1;
            while( j < k){
                if(nums[j] + nums[k]  == - nums[i]){
                    res.add(new ArrayList(Arrays.asList(nums[j] , nums[k] , nums[i])));
                    j++;
                    k--;
                    while(j < k && nums[j] == nums[j - 1]){
                        j++;
                    }
                    while(j < k && nums[k] == nums[k + 1]){
                        k--;
                    }
                }
                else if(nums[j] + nums[k]  < - nums[i]){
                    j++;
                    
                }
                else{
                    k--;
                }
                
                
            }
            
            
            
        }
        return res;
    }
}