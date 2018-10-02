// Given n unique integers, number k (1<=k<=n) and target.

// Find all possible k integers where their sum is target.

// Example
// Given [1,2,3,4], k = 2, target = 5. Return:

// [
//   [1,4],
//   [2,3]
// ]


public class Solution {
    /*
     * @param A: an integer array
     * @param k: a postive integer <= length(A)
     * @param targer: an integer
     * @return: A list of lists of integer
     */
    public List<List<Integer>> kSumII(int[] nums, int k, int target ) {
        List<List<Integer>> res = new ArrayList();
        if(nums == null){
            return res;
        }
        
        Arrays.sort(nums);
        combination(res,nums,k,target,new ArrayList() ,0);
        return res;
    }
    private void combination(List<List<Integer>> res , int[] nums , int k ,int remain , List<Integer> curCombine , int startIndex){
        if(remain == 0 && k == 0){ 
            res.add(new ArrayList(curCombine));
        }


        for(int i = startIndex ; i < nums.length ; i++){
            if(nums[i] > remain || k < 0){
                break;
            }
            curCombine.add(nums[i]);
            combination(res,nums,k - 1,remain - nums[i] , curCombine , i + 1);
            curCombine.remove(curCombine.size() - 1);
        }
    }
}