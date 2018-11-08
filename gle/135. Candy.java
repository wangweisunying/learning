// 135. Candy
// DescriptionHintsSubmissionsDiscussSolution
// There are N children standing in a line. Each child is assigned a rating value.

// You are giving candies to these children subjected to the following requirements:

// Each child must have at least one candy.
// Children with a higher rating get more candies than their neighbors.
// What is the minimum candies you must give?

// Example 1:

// Input: [1,0,2]
// Output: 5
// Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
// Example 2:

// Input: [1,2,2]
// Output: 4
// Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
//              The third child gets 1 candy because it satisfies the above two conditions.


class Solution {
    public int candy(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return 1;
        }
        int res = 0;
        int[] f = new int[nums.length];
        boolean change = true;
        while(change){
            
            change = false;
            for(int i = 0 ; i < nums.length ; i++){
                if(f[i] != 0){
                    continue;
                }
                change = true;
                if(i == 0){
                    if(nums[i] <= nums[i + 1]){
                        f[i] = 1;
                    }
                    else if(f[i + 1] != 0){
                        f[i] = f[i + 1] + 1;
                    }
                    continue;
                }
                if(i == nums.length - 1){
                    if(nums[i] <= nums[i - 1]){
                        f[i] = 1;
                    }
                    else if(f[i - 1] != 0){
                        f[i] = f[i - 1] + 1;
                    }
                    continue;
                }

                if(nums[i]<= nums[i - 1] && nums[i] <= nums[i + 1]){
                    f[i] = 1;
                }
                else if(nums[i - 1] >= nums[i] && nums[i] >= nums[i + 1]){
                    if(f[i + 1] != 0){
                        f[i] = f[i + 1] + 1;
                    }
                }
                else if(nums[i - 1] <= nums[i] && nums[i] <= nums[i + 1]){
                    if(f[i - 1] != 0){
                        f[i] = f[i - 1] + 1;
                    }
                }
                else {
                    if(f[i - 1] != 0 && f[i + 1] != 0){
                        f[i] = Math.max(f[i - 1 ] , f[i + 1]) + 1;
                    }
                }
            }

        }
        
        for(int i : f){
            res+= i;
        }
        return res;
    }
}