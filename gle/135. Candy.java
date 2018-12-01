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



// Hi guys!

// This solution picks each element from the input array only once. First, we give a candy to the first child. Then for each child we have three cases:

// His/her rating is equal to the previous one -> give 1 candy.
// His/her rating is greater than the previous one -> give him (previous + 1) candies.
// His/her rating is less than the previous one -> don't know what to do yet, let's just count the number of such consequent cases.
// When we enter 1 or 2 condition we can check our count from 3. If it's not zero then we know that we were descending before and we have everything to
//  update our total candies amount: number of children in descending sequence of raitings - coundDown, number of candies given at peak - prev 
// (we don't update prev when descending). Total number of candies for "descending" children can be found through arithmetic progression formula (1+2+...+countDown).
//  Plus we need to update our peak child if his number of candies is less then or equal to countDown.

// Here's a pretty concise code below.

public class Solution {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        int total = 1, prev = 1, countDown = 0;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] >= ratings[i-1]) {
                if (countDown > 0) {
                    total += countDown*(countDown+1)/2; // arithmetic progression
                    if (countDown >= prev) total += countDown - prev + 1;
                    countDown = 0;
                    prev = 1;
                }
                prev = ratings[i] == ratings[i-1] ? 1 : prev+1;
                total += prev;
            } else countDown++;
        }
        if (countDown > 0) { // if we were descending at the end
            total += countDown*(countDown+1)/2;
            if (countDown >= prev) total += countDown - prev + 1;
        }
        return total;
    }
}


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