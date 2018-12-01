// 228. Summary Ranges
// DescriptionHintsSubmissionsDiscussSolution
// Given a sorted integer array without duplicates, return the summary of its ranges.

// Example 1:

// Input:  [0,1,2,4,5,7]
// Output: ["0->2","4->5","7"]
// Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
// Example 2:

// Input:  [0,2,3,4,6,8,9]
// Output: ["0","2->4","6","8->9"]
// Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.

// like merge interval;
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList();
        if(nums == null || nums.length == 0){
            return list;
        }
        int s = 0  , e = 1;
        for(; e < nums.length ;e++){
            if(nums[e] == nums[e - 1] + 1){
                continue;
            }
            if(s + 1 == e){
                list.add(String.valueOf(nums[s]));
            }
            else{
                list.add(nums[s] + "->" + nums[e - 1]);
            }
            s = e;
        }
        if(s + 1 == e){
            list.add(String.valueOf(nums[s]));
        }
        else{
            list.add(nums[s] + "->" + nums[e - 1]);
        }
        return list;
    }
}