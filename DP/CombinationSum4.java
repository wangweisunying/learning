class Solution {
    public int combinationSum4(int[] nums, int target) {
        if(nums == null){
            return 0 ; 
        }
        int[] res = new int[target + 1]; //result ct of target
        res[0] = 1;
        for(int i = 0 ; i < res.length ; i ++){
            for(int cur : nums){
                if(i + cur <= target){
                    res[i + cur] += res[i]; // res of target  = any result < target + sth 
                }
            }
        }
        return res[target];
    }
}