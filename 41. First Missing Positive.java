// 41. First Missing Positive
// DescriptionHintsSubmissionsDiscussSolution
// Given an unsorted integer array, find the smallest missing positive integer.

// Example 1:

// Input: [1,2,0]
// Output: 3
// Example 2:

// Input: [3,4,-1,1]
// Output: 2
// Example 3:

// Input: [7,8,9,11,12]
// Output: 1
// Note:

// Your algorithm should run in O(n) time and uses constant extra space.


// The key here is to use swapping to keep constant space and also make use of the length of the array, 
// which means there can be at most n positive integers. So each time we encounter an valid integer,
//  find its correct position and swap. Otherwise we continue.
// [0 , 1 , 2 , 3 , 4 ]
// [1 , 2 , 3,  -1 , 5 ]
// put the right postive number in the right position and judge

// [3,4,-1,1]
// Output:
// 1
// Expected:
// 2


//  buket sort //拓展到 1.。。 n 的排序 O(n);
    public int firstMissingPositive(int[] nums) {
        for(int i = 0 ; i < nums.length  ; i++){
            while(nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]){ //while ！
                swap(nums , i , nums[i] - 1);
            }    
        }
        System.out.println(Arrays.asString(nums));
        for(int i = 0 ; i < nums.length ; i++){
            if( nums[i] != i + 1){
                return i + 1;
            }
        }
        return nums.length + 1;
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }











   public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i]-1] != nums[i]) {
                swap(nums, i, nums[i]-1);
            }
        }
        
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] != i+1)    return i+1;
        }
        return nums.length+1;
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }