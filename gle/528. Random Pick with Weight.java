// 528. Random Pick with Weight
// DescriptionHintsSubmissionsDiscussSolution
// Given an array w of positive integers, where w[i] describes the weight of index i, 
// write a function pickIndex which randomly picks an index in proportion to its weight.

// Note:

// 1 <= w.length <= 10000
// 1 <= w[i] <= 10^5
// pickIndex will be called at most 10000 times.
// Example 1:

// Input: 
// ["Solution","pickIndex"]
// [[[1]],[]]
// Output: [null,0]
// Example 2:

// Input: 
// ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
// [[[1,3]],[],[],[],[],[]]
// Output: [null,0,1,1,1,0]

// Explanation of Input Syntax:

// The input is two lists: the subroutines called and their arguments.
//  Solution's constructor has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.



//题意 根据权重 来确定 输出的概率  
//想到用 consistant hash 的东西 ，建立 preSUm 数组确定 各个index 的范围， 对于每一个random target 二分查找对应的值对应的index
class Solution {
    
    int[] preSum;
    int len;
    public Solution(int[] w) {
        len = w.length;
        preSum = new int[w.length];
        preSum[0] = w[0];
        for(int i = 1 ; i < w.length ; i++){
            preSum[i] = preSum[i - 1] + w[i];
        }
    }
    
    public int pickIndex() {
        int target = (int)Math.ceil(preSum[preSum.length - 1] * Math.random());
        int s = 0 ; int e = len - 1;
        while(s + 1 < e){
            int mid = (e - s) / 2 + s;
            if(preSum[mid] == target){
                return mid;
            }
            else if(preSum[mid] < target){
                s = mid;
            }
            else{
                e = mid;
            }
        }
        if(preSum[s] >= target){
            return s;
        }
        return e;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */