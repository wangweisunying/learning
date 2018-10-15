// 42. Trapping Rain Water
// DescriptionHintsSubmissionsDiscussSolution
// Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


// The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

// Example:

// Input: [0,1,0,2,1,0,1,3,2,1,2,1]
// Output: 6
// Input:
// [5,5,1,7,1,1,5,2,7,6]
// Output:
// 18
// Expected:
// 23

// two pointers  , set leftmax  rightmax  which one is lower move first , until they overlay,
// must use  s<=e incase s , e met in the valley 

class Solution {
    public int trap(int[] height) {
        if(height == null || height.length < 3){
            return 0;
        }
        int s = 0 , e = height.length - 1 , res = 0;
        int leftMax = height[s];
        int rightMax = height[e];
        
        while(s <= e){ 
            while(s <= e && leftMax <= rightMax){
                res += ((leftMax - height[s] > 0) ? leftMax - height[s] : 0 ) ;
                leftMax = Math.max(leftMax , height[s]);
                s++;
                System.out.println( "left : " + res);
            }
            while(s <= e && leftMax > rightMax){
                res += ((rightMax - height[e] > 0) ? rightMax - height[e] : 0 );
                rightMax = Math.max(rightMax , height[e]);
                e--;
                System.out.println( "right : " + res);
            }
        }
        return res;
    }
}