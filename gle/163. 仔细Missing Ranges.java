// 163. Missing Ranges
// DescriptionHintsSubmissionsDiscussSolution
// Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

// Example:

// Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
// Output: ["2", "4->49", "51->74", "76->99"]
// [2]
// 0
// 9
// Output:
// ["0","1","3->9"]
// Expected:
// ["0->1","3->9"]

// [-2147483648,2147483647]
// -2147483648
// 2147483647
// ["-2147483647->2147483646"]


// [2147483647]
// -2147483648
// 2147483647

public List<String> findMissingRanges(int[] a, int lo, int hi) {
  List<String> res = new ArrayList<String>();
  
  // the next number we need to find
  int next = lo;
  
  for (int i = 0; i < a.length; i++) {
    // not within the range yet
    if (a[i] < next) continue;
    
    // continue to find the next one
    if (a[i] == next) {
      next++;
      continue;
    }
    
    // get the missing range string format
    res.add(getRange(next, a[i] - 1));
    
    // now we need to find the next number
    next = a[i] + 1;
  }
  
  // do a final check
  if (next <= hi) res.add(getRange(next, hi));
  return res;
}

String getRange(int n1, int n2) {
  return (n1 == n2) ? String.valueOf(n1) : String.format("%d->%d", n1, n2);
}










class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList();
        if(nums == null || nums.length == 0){
            if(upper - lower == 0){
                res.add("" + upper);
            }
            else{
                res.add(lower + "->" + upper);
            }
            return res;
        }
        int index = 0;
        while(nums[index] < lower){
            index++;
        }
        if((long)nums[index] > (long)lower && index == 0 || (index - 1>=0 && (long)nums[index - 1] < (long)lower)){
            if((long)nums[index] - (long)lower > 1){
                res.add(String.valueOf(lower) + "->" + (nums[index] - 1));  
            }
            else{
                res.add(String.valueOf(lower));
            }
            lower = nums[index];
        }
        for( ; index < nums.length && nums[index] <= upper ; index++){
            
            if((long)nums[index] == (long)lower){
                continue;
            }
            else{
                if((long)nums[index] - (long)lower > 2){
                    res.add(String.valueOf(lower + 1) + "->" + String.valueOf(nums[index] - 1));
                }
                else if((long)nums[index] - (long)lower == 2){
                    res.add(String.valueOf(lower + 1));
                }
                lower = nums[index];
            }
        }
        if((long)lower <= (long)upper){
            if((long)upper - (long)lower > 1){
                res.add(String.valueOf(lower + 1) + "->" + String.valueOf(upper));
            }
            else if((long)upper - (long)lower == 1){
                res.add(String.valueOf(upper));
            }
        }
        return res;
    }
}