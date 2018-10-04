// RangeSumQuery - Mutable
// Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

// The update(i, val) function modifies nums by updating the element at index i to val.

// Example
// Given nums = [1, 3, 5]

// sumRange(0, 2) -> 9
// update(1, 2)
// sumRange(0, 2) -> 8


class NumArray {
    int[] preSum;
    int[] nums;
    public NumArray(int[] nums) {
        this.nums = nums;
        preSum = new int[nums.length + 1];
        for(int i = 1 ; i < preSum.length ; i++){
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }    
    }
    
    public void update(int i, int val) {
        int dif = val - nums[i];
        nums[i] = val; // dont forget to update val
        for( ; i < preSum.length - 1 ; i++){
            preSum[i + 1] += dif;
        }
    }
    
    public int sumRange(int i, int j) {
        return preSum[j + 1] - preSum[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */




























class NumArray {
    int[] nums;
    public NumArray(int[] nums) {
        this.nums = nums;
    }
    
    public void update(int i, int val) {
        nums[i] = val;
    }
    
    public int sumRange(int i, int j) {
        int sum = 0;
        for(int x = i ; x <= j ; x++ ){
            sum += nums[x]; 
        }
        return sum;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
 